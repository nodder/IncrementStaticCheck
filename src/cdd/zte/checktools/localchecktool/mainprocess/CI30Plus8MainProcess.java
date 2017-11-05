package cdd.zte.checktools.localchecktool.mainprocess;

import java.util.ArrayList;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.info.FileSvnLogInfo;
import cdd.zte.checktools.localchecktool.SingleCheckResult;
import cdd.zte.checktools.localchecktool.cireport.ReportFor30Plus8;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnLogQueryProxy;
import cdd.zte.checktools.localchecktool.localcheck.LocalCheck30Plus8;
import cdd.zte.checktools.localchecktool.revisonlist.StartEndPeriodRevisions;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class CI30Plus8MainProcess extends AbsMainProcess
{
    private ArrayList<FileSvnLogInfo> noNeedCheckList = new ArrayList<FileSvnLogInfo>();

    public CI30Plus8MainProcess(int module, int startRevision, int endRevision)
    {
        super(module);
        setRevisonListMaint(new StartEndPeriodRevisions(module, startRevision, endRevision));
        setLocalCheckBat(new LocalCheck30Plus8());
    }

    @Override
    public boolean isContinueNextCheck(SingleCheckResult result)
    {
        return true;
    }

    @Override
    public void afterRunSingleCheck(SingleCheckResult result) throws Exception
    {
        String logStr = "Check " + result.revision + " finished. Detail:" + result.toString();
        LogPrint.logInfo(logger, logStr);
    }
    
    @Override
    public void beforeRunSingleCheck(int revision)
    {
        LogPrint.printStep(logger, "开始执行30+8检查版本号 (" + revision +")的入库信息。");
    }

    @Override
    public boolean isThisRevisionNeedCheck(int revision) throws Exception
    {
        FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(CommonUtil.getUrl(module), revision);
        boolean isNeedCheck = !svnLogInfo.getNoteStr().contains(CommonConst.CI_NO_NEED_CHECK_STR);
        
        if(!isNeedCheck)
        {
            LogPrint.printStep(logger, "版本号 (" + revision +")不需要执行30+8.");
        }
        return isNeedCheck;
    }

    @Override
    public void doSomethingForNoNeedCheckVersion(int revision) throws Exception
    {
        FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(CommonUtil.getUrl(module), revision);
        this.noNeedCheckList .add(svnLogInfo);
    }

    @Override
    public void afterAllCheck(ArrayList<SingleCheckResult> checkResults) throws Exception
    {
        int sumDiff = sumDiff(checkResults);
        LogPrint.printStep(logger, "30+8检查执行完毕，（错误变化：" + sumDiff + ")");
        
        new ReportFor30Plus8(module, CommonConst.configFileReader).doReport(checkResults, noNeedCheckList);
        
        exit(sumDiff);
    }

    private void exit(int sumDiff)
    {
        if(sumDiff <= 0)
        {
            if(this.noNeedCheckList.isEmpty())
            {
                
                System.exit(CommonConst.TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_PASS_AND_NO_NEED_MANUAL_CHECK_30PLUS8);
            }
            else
            {
                System.exit(CommonConst.TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_PASS_AND_NEED_MANUAL_CHECK_30PLUS8);
            }
        }
        else
        {
            if(this.noNeedCheckList.isEmpty())
            {
                System.exit(CommonConst.TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_NOPASS_AND_NO_NEED_MANUAL_CHECK_30PLUS8);
            }
            else
            {
                System.exit(CommonConst.TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_NOPASS_AND_NEED_MANUAL_CHECK_30PLUS8);
            }
        }
    }
    
    private int sumDiff(ArrayList<SingleCheckResult> checkResults)
    {
        int sum = 0;
        
        for(SingleCheckResult result : checkResults)
        {
            sum += result.diff;
        }
        
        return sum;
    }

    @Override
    public void beforeDoCheck() throws Exception
    {
    }
}

