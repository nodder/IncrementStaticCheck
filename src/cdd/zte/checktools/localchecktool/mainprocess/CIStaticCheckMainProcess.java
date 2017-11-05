package cdd.zte.checktools.localchecktool.mainprocess;

import java.util.ArrayList;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.info.FileSvnLogInfo;
import cdd.zte.checktools.localchecktool.SingleCheckResult;
import cdd.zte.checktools.localchecktool.cireport.ReportForStaticCheck;
import cdd.zte.checktools.localchecktool.localcheck.LocalCheckStatic;
import cdd.zte.checktools.localchecktool.revisonlist.StartEndPeriodRevisions;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class CIStaticCheckMainProcess extends AbsMainProcess
{
    private ArrayList<FileSvnLogInfo> noNeedCheckList = new ArrayList<FileSvnLogInfo>();

    public CIStaticCheckMainProcess(int module, int startRevision, int endRevision)
    {
        super(module);
        setRevisonListMaint(new StartEndPeriodRevisions(module, startRevision, endRevision));
        setLocalCheckBat(new LocalCheckStatic());
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
        LogPrint.printStep(logger, "开始执行静态检查版本号 (" + revision +")的入库信息。");
    }

    @Override
    public boolean isThisRevisionNeedCheck(int revision) throws Exception
    {
        return true;
    }

    @Override
    public void doSomethingForNoNeedCheckVersion(int revision) throws Exception
    {
    }

    @Override
    public void afterAllCheck(ArrayList<SingleCheckResult> checkResults) throws Exception
    {
        int sumDiff = sumDiff(checkResults);
        LogPrint.printStep(logger, "静态检查执行完毕，（错误变化：" + sumDiff + ")");
        
        new ReportForStaticCheck(module, CommonConst.configFileReader).doReport(checkResults, noNeedCheckList);
        
        if(sumDiff > 0)
        {
            LogPrint.printStep(logger, "静态检查不通过，不执行30+8,直接退出");
            System.exit(CommonConst.TOOL_RESULT_STATICCHECK_NOPASS);
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

