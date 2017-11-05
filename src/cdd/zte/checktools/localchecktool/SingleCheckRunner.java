package cdd.zte.checktools.localchecktool;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.ClassExclusionFileReader;
import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.ConfigFileReader;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.ResultComparator;
import cdd.zte.checktools.localchecktool.export.ExportCenter;
import cdd.zte.checktools.localchecktool.localcheck.LocalCheckBat;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SingleCheckRunner
{
    private ConfigFileReader configReader;
    private ClassExclusionFileReader classExclusionFileReader;
    private final String exportBasePath;
    
    private static Logger logger = Logger.getLogger(SingleCheckRunner.class.getName());
    private final int module;
    
    private SingleCheckResult sResult;
    private LocalCheckBat localCheckBat;
    
    public SingleCheckRunner(int module, ConfigFileReader configReader, ClassExclusionFileReader classExclusionFileReader)
    {
        this.module = module;
        this.configReader = configReader;
        this.classExclusionFileReader = classExclusionFileReader;
        this.exportBasePath = configReader.getTempPath();
    }
    
    public void setLocalCheckBat(LocalCheckBat localCheckBat)
    {
        this.localCheckBat = localCheckBat;
    }
    
    public SingleCheckResult doRunSingleCheck(int revision)
    {
        initSingleResult(revision);
         
        try
        {
            runExport(revision);
            runLocalCheckAndBackupReports();
            compareResult(revision);
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, e);
        }        
        
        return sResult;
    }

    private void compareResult(int revision) throws IOException
    {
        this.sResult.diff = ResultComparator.compare(
            new File(getBakReportOfXml(CommonConst.TEMPDIR_REPORT_PREFIX_OLD,
                CommonUtil.getReportBackPath(configReader, this.module, sResult.revision))),
            new File(getBakReportOfXml(CommonConst.TEMPDIR_REPORT_PREFIX_CURR,
                CommonUtil.getReportBackPath(configReader, this.module, sResult.revision))));
    }

    private void runLocalCheckAndBackupReports() throws Exception
    {
        CommonUtil.emptyPath(CommonUtil.getReportBackPath(configReader, this.module, sResult.revision));
        
        localCheckBat.doLocalCheck(new File(exportBasePath + CommonConst.TEMPDIR_EXPORT_CURR).getAbsolutePath());
        bakLocalCheckReport(sResult.revision, CommonConst.TEMPDIR_REPORT_PREFIX_CURR);
        
        localCheckBat.doLocalCheck(new File(exportBasePath + CommonConst.TEMPDIR_EXPORT_OLD).getAbsolutePath());
        bakLocalCheckReport(sResult.revision, CommonConst.TEMPDIR_REPORT_PREFIX_OLD);
    }

    private void runExport(int revision) throws Exception
    {
        ExportCenter procCurr = new ExportCenter(module, exportBasePath, revision, classExclusionFileReader.getExcludedClassPattern());
        procCurr.runExportProcess();
    }

    private void initSingleResult(int revision)
    {
        sResult = new SingleCheckResult(revision);
    }

    private void bakLocalCheckReport(int revision, String prefix)
    {
        String bakReportPath = CommonUtil.getReportBackPath(configReader, this.module, sResult.revision);
        
        String reportOfHtml = configReader.getCheckstylePath() + "/" + CommonConst.LOCALCHECK_REPORT_FILE_HTML;
        String reportOfXml = configReader.getCheckstylePath() + "/" + CommonConst.LOCALCHECK_REPORT_FILE_XML;
        
        String bakReportOfHtml = bakReportPath + "/" + prefix + CommonConst.LOCALCHECK_REPORT_FILE_HTML;
        String bakReportOfXml = getBakReportOfXml(prefix, bakReportPath);

        CommonUtil.copyFiles(new File(reportOfHtml).getAbsoluteFile(), new File(bakReportOfHtml).getAbsoluteFile());
        CommonUtil.copyFiles(new File(reportOfXml).getAbsoluteFile(), new File(bakReportOfXml).getAbsoluteFile());
    }

    private String getBakReportOfXml(String prefix, String bakReportPath)
    {
        return bakReportPath + "/" + prefix + CommonConst.LOCALCHECK_REPORT_FILE_XML;
    }
}
