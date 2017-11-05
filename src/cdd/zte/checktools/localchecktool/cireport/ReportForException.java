package cdd.zte.checktools.localchecktool.cireport;

import java.io.File;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.fileParser.CsvParser;
import cdd.zte.checktools.common.info.FileSvnLogInfo;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnLogQueryProxy;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ReportForException
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private int module;
    
    private static final String[] CSV_HEADER_STATIC_CHECK = new String[] {"revision", "committer", "committedTime", "exception"};

    public ReportForException(int module)
    {
        this.module = module;
    }

    public void doReport(int revision, String errorStr)
    {
        generateCSV(revision, errorStr);
    }

    private void generateCSV(int revision, String errorStr)
    {
        File csvReport = new File(CIReportUtil.getCsvReportForException(module));

        CsvParser p = new CsvParser(csvReport);
        p.appendHeader(CSV_HEADER_STATIC_CHECK);

        try
        {
            if(revision == -1)
            {
                p.appendLine(revision + "", "N/A", "N/A", errorStr);
            }
            else
            {
                FileSvnLogInfo svnLogInfo = SvnLogQueryProxy.query(CommonUtil.getUrl(module), revision);
                p.appendLine(revision + "", svnLogInfo.getAuthor(), svnLogInfo.getModifyTime(), errorStr);
            }
            
            p.saveAndFinish();
           
            LogPrint.logInfo(logger, "Reported Exception as:" + csvReport.getAbsolutePath());
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, e);
        }
    }
}
