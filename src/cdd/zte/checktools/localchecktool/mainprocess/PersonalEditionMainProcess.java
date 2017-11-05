package cdd.zte.checktools.localchecktool.mainprocess;

import java.io.File;
import java.util.ArrayList;

import cdd.zte.checktools.common.Ask;
import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.info.FileSvnLogInfo;
import cdd.zte.checktools.localchecktool.SingleCheckResult;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnLogQueryProxy;
import cdd.zte.checktools.localchecktool.localcheck.LocalCheck30Plus8;
import cdd.zte.checktools.localchecktool.revisonlist.RealtimeRevisions;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class PersonalEditionMainProcess extends AbsMainProcess
{
    public PersonalEditionMainProcess(int module)
    {
        super(module);
        setRevisonListMaint(new RealtimeRevisions(module));
        setLocalCheckBat(new LocalCheck30Plus8());
    }

    @Override
    public boolean isContinueNextCheck(SingleCheckResult result)
    {
        return null == result || isSingleCheckSuccess(result) || Ask.isContinueCheck();
    }

    @Override
    public void afterRunSingleCheck(SingleCheckResult result) throws Exception
    {
        if(isSingleCheckSuccess(result))
        {    
            logLocalCheckOK(result.revision, result.diff);     
        }                    
        else
        {
            logLocalCheckError(result.revision, result.diff);
        }
    }
    
    private boolean isSingleCheckSuccess(SingleCheckResult result)
    {
        return result.isNoErrorOccurred &&  result.diff <= 0;
    }
    
    private void logLocalCheckOK(int revision, int diff) throws Exception
    {
        String printDesc = "--";
        if(diff < 0)
        {
            FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(this.url, revision);
            printDesc = "版本号(" + revision +")无故障。减少缺陷数: " + (-diff) + "。\n" + svnLogInfo.toOperLogString();
        }
        else if(diff == 0)
        {
            printDesc = "版本号(" + revision +")无故障。";
        }
        
        LogPrint.logInfo(logger, printDesc);
    }
    
    private void logLocalCheckError(int revision, int diff) throws Exception
    {
        FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(this.url, revision);
        String errDesc = "版本号(" + revision +")存在LocalCheck故障。新增缺陷数: " + diff + "。\n" + svnLogInfo.toOperLogString();
        LogPrint.logError(logger, errDesc);
    }

    @Override
    public void beforeRunSingleCheck(int revision)
    {
        LogPrint.printStep(logger, "开始检查版本号 (" + revision +")的入库信息。");
    }

    @Override
    public boolean isThisRevisionNeedCheck(int revision)
    {
        return true;
    }

    @Override
    public void doSomethingForNoNeedCheckVersion(int revision)
    {
    }

    @Override
    public void afterAllCheck(ArrayList<SingleCheckResult> checkResults)
    {
    }

    @Override
    public void beforeDoCheck() throws Exception
    {
        CommonUtil.deleteFiles(new File(CommonConst.configFileReader.getTempPath() + CommonConst.TEMPDIR_REPORT));
    }
}
