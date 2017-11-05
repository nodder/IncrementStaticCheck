package cdd.zte.checktools.localchecktool.mainprocess;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.localchecktool.SingleCheckResult;
import cdd.zte.checktools.localchecktool.SingleCheckRunner;
import cdd.zte.checktools.localchecktool.cireport.ReportForException;
import cdd.zte.checktools.localchecktool.localcheck.LocalCheckBat;
import cdd.zte.checktools.localchecktool.revisonlist.IRevisionListMaint;

public abstract class AbsMainProcess
{
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    
    protected final String url;
    protected final int module;
    
    private IRevisionListMaint revMain;
    private LocalCheckBat localCheckBat;
    private ArrayList<SingleCheckResult> checkResults = new ArrayList<SingleCheckResult>();
    
    public AbsMainProcess(int module)
    {    
        this.module = module;
        this.url = CommonUtil.getUrl(module);
    }
    
    protected void setRevisonListMaint(IRevisionListMaint revMain)
    {
        this.revMain = revMain;
    }
    
    protected void setLocalCheckBat(LocalCheckBat localCheckBat)
    {
        this.localCheckBat = localCheckBat;
    }
    
    public void runProcess()
    {
        SingleCheckRunner singleCheck = new SingleCheckRunner(module, CommonConst.configFileReader, CommonConst.classExclusionFileReader);
        singleCheck.setLocalCheckBat(this.localCheckBat);
        SingleCheckResult result;
        int revision = -1;
        
        try
        {
            beforeDoCheck();
            
            do
            {
                revision = this.revMain.nextRevision();
                if(revision == -1)
                {
                    break;
                }
                
                if(isThisRevisionNeedCheck(revision))
                {
                    beforeRunSingleCheck(revision);
                    result = singleCheck.doRunSingleCheck(revision);
                    addResultToList(result);
                    afterRunSingleCheck(result);
                }
                else
                {
                    result = null;
                    doSomethingForNoNeedCheckVersion(revision);
                }
            }
            while(isContinueNextCheck(result));
            
            afterAllCheck(checkResults);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "Exception happens, about to report exception and exit", ex);
            new ReportForException(this.module).doReport(revision, ex.getMessage());
            System.exit(CommonConst.TOOL_RESULT_EXCEPTION_OCCURRED_IN_TOOL);
        }
    }

    private void addResultToList(SingleCheckResult result)
    {
        checkResults.add(result);
    }

    abstract public void beforeDoCheck() throws Exception;
    abstract public void afterAllCheck(ArrayList<SingleCheckResult> checkResults) throws Exception;
    abstract public void doSomethingForNoNeedCheckVersion(int revision) throws Exception;
    abstract public boolean isThisRevisionNeedCheck(int revision) throws Exception;
    abstract public boolean isContinueNextCheck(SingleCheckResult result);
    abstract public void afterRunSingleCheck(SingleCheckResult result) throws Exception;
    abstract public void beforeRunSingleCheck(int revision);
}
