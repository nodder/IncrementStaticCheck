package cdd.zte.checktools.localchecktool.cireport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.ConfigFileReader;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.fileParser.CsvParser;
import cdd.zte.checktools.common.fileParser.FileZipper;
import cdd.zte.checktools.common.info.FileSvnLogInfo;
import cdd.zte.checktools.localchecktool.SingleCheckResult;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnLogQueryProxy;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ReportForStaticCheck
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private int module;
    private ConfigFileReader configReader;
    
    private static final String[] CSV_HEADER_STATIC_CHECK = new String[] {"revision", "committer", "committedTime", "errors"};

    public ReportForStaticCheck(int module, ConfigFileReader configReader)
    {
        this.module = module;
        this.configReader = configReader;
    }

    public void doReport(ArrayList<SingleCheckResult> checkResults, ArrayList<FileSvnLogInfo> noNeedCheckList) throws Exception
    {
        generateCSVForStatic(checkResults);
        generateZipForStatic(checkResults);
    }

    private void generateZipForStatic(ArrayList<SingleCheckResult> checkResults) throws Exception
    {
        File tempLoackCheckCopyPath = getTempLocalCheckPath(new File(CIReportUtil.getZipReportForStatic(module)));
        for(SingleCheckResult result : checkResults)
        {
            CommonUtil.copyFiles(new File(CommonUtil.getReportBackPath(configReader, module, result.revision)), new File(tempLoackCheckCopyPath, "revision" + result.revision));
        }
        
        File zipReport = new File(CIReportUtil.getZipReportForStatic(module));
        
        new FileZipper().doZip(zipReport, tempLoackCheckCopyPath);
        CommonUtil.deleteDir(tempLoackCheckCopyPath.getAbsoluteFile());
        LogPrint.logInfo(logger, "Zipped static check as:" + zipReport.getAbsolutePath());
    }

    private File getTempLocalCheckPath(File zipReportFor30Plus8)
    {
        return new File(zipReportFor30Plus8.getParent(), "temp");
    }

    private void generateCSVForStatic(ArrayList<SingleCheckResult> checkResults) throws Exception, IOException
    {
        File csvReport = new File(CIReportUtil.getCsvReportForStatic(module));
        
        CsvParser p = new CsvParser(csvReport);
        p.appendHeader(CSV_HEADER_STATIC_CHECK);
        for(SingleCheckResult result : checkResults)
        {
            FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(CommonUtil.getUrl(module), result.revision);
            p.appendLine(result.revision + "", svnLogInfo.getAuthor(), svnLogInfo.getModifyTime(), result.diff + "");
        }
        
        p.saveAndFinish();
        LogPrint.logInfo(logger, "Reported static check as:" + csvReport.getAbsolutePath());
    }
}
