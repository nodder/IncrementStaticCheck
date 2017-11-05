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
public class ReportFor30Plus8
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private int module;
    private ConfigFileReader configReader;
    
    private static final String[] CSV_HEADER_30PLUS8 = new String[] {"revision", "committer", "committedTime", "errors"};
    private static final String[] CSV_HEADER_MANUAL_CHECK = new String[] {"revision", "committer", "committedTime"};

    public ReportFor30Plus8(int module, ConfigFileReader configReader)
    {
        this.module = module;
        this.configReader = configReader;
    }

    public void doReport(ArrayList<SingleCheckResult> checkResults, ArrayList<FileSvnLogInfo> noNeedCheckList) throws Exception
    {
        generateCSVFor30Plus8(checkResults);
        generateZipFor30Plus8(checkResults);
        
        generateCSVForManualCheck(noNeedCheckList);
    }

    private void generateZipFor30Plus8(ArrayList<SingleCheckResult> checkResults) throws Exception
    {
        File tempLoackCheckCopyPath = getTempLocalCheckPath(new File(CIReportUtil.getZipReportFor30Plus8(module)));
        for(SingleCheckResult result : checkResults)
        {
            CommonUtil.copyFiles(new File(CommonUtil.getReportBackPath(configReader, module, result.revision)), new File(tempLoackCheckCopyPath, "revision" + result.revision));
        }
        
        File zipReportFor30Plus8 = new File(CIReportUtil.getZipReportFor30Plus8(module));
        
        new FileZipper().doZip(zipReportFor30Plus8, tempLoackCheckCopyPath);
        CommonUtil.deleteDir(tempLoackCheckCopyPath.getAbsoluteFile());
        LogPrint.logInfo(logger, "Zipped 30plus 8 as:" + zipReportFor30Plus8.getAbsolutePath());
    }

    private File getTempLocalCheckPath(File zipReportFor30Plus8)
    {
        return new File(zipReportFor30Plus8.getParent(), "temp");
    }

    private void generateCSVFor30Plus8(ArrayList<SingleCheckResult> checkResults) throws Exception, IOException
    {
        File csvReportFor30Plus8 = new File(CIReportUtil.getCsvReportFor30Plus8(module));
        
        CsvParser p = new CsvParser(csvReportFor30Plus8);
        p.appendHeader(CSV_HEADER_30PLUS8);
        for(SingleCheckResult result : checkResults)
        {
            FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(CommonUtil.getUrl(module), result.revision);
            p.appendLine(result.revision + "", svnLogInfo.getAuthor(), svnLogInfo.getModifyTime(), result.diff + "");
        }
        
        p.saveAndFinish();
        LogPrint.logInfo(logger, "Reported 30plus 8 as:" + csvReportFor30Plus8.getAbsolutePath());
    }

    private void generateCSVForManualCheck(ArrayList<FileSvnLogInfo> noNeedCheckList) throws Exception, IOException
    {
        File csvReport = new File(CIReportUtil.getCsvReportForManualCheck(module));
        
        CsvParser p = new CsvParser(csvReport);
        p.appendHeader(CSV_HEADER_MANUAL_CHECK);
        for(FileSvnLogInfo svnLog : noNeedCheckList)
        {
            p.appendLine(svnLog.getRevision() + "", svnLog.getAuthor(), svnLog.getModifyTime());
        }
        
        p.saveAndFinish();
        LogPrint.logInfo(logger, "Reported manual check as:" + csvReport.getAbsolutePath());
    }
}
