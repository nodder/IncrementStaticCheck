package cdd.zte.checktools.committool;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.committool.common.FileSearch;
import cdd.zte.checktools.committool.csvchecker.CsvChecker;
import cdd.zte.checktools.committool.javacheck.JavaChecker;
import cdd.zte.checktools.committool.sqlcheck.SqlChecker;
import cdd.zte.checktools.committool.xmlcheck.XmlChecker;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.ConfigFileReader;
import cdd.zte.checktools.common.LogPrint;

public class MainProcess
{
    private static Logger logger = Logger.getLogger(MainProcess.class.getName());
    
    private MainProcess()
    {
    }
    
    public static void doCheck(File path) throws Exception
    {
        if(path.isFile())
        {
            if(CommonUtil.isJava(path))
            {
                LogPrint.logInfo(logger, "要检查java文件必须指定为所在路径。\n\n");
                return;
            }
            else if(CommonUtil.isXml(path))
            {
                ArrayList<File> xmlFile = new ArrayList<File>();
                xmlFile.add(path);
                XmlChecker.check(xmlFile);
            }
            else if(CommonUtil.isCsv(path))
            {
                ArrayList<File> csvFile = new ArrayList<File>();
                csvFile.add(path);
                CsvChecker.check(csvFile);
            }
            else if(CommonUtil.isSql(path))
            {
                ArrayList<File> sqlFile = new ArrayList<File>();
                sqlFile.add(path);
                SqlChecker.check(sqlFile);
            }
        }
        else
        {
            FileSearch fileSearch = new FileSearch(path.getAbsolutePath());

            if(isCheckJava())
            {
                ArrayList<File> javaFiles = fileSearch.getJavaFiles();
                //                ArrayList<SvnStatusInfo> listSvnStatus = FileConvertor.fileToSvnStatusInfo(path.getAbsolutePath(), javaFiles);
                JavaChecker.check(path.getAbsolutePath(), javaFiles);
            }

            if(isCheckXml())
            {
                ArrayList<File> xmlFiles = fileSearch.getXmlFiles();
                XmlChecker.check(xmlFiles);
            }

            if(isCheckCsv())
            {
                ArrayList<File> csvFiles = fileSearch.getCsvFiles();
                CsvChecker.check(csvFiles);
            }
            
            if(isCheckSql())
            {
                ArrayList<File> sqlFiles = fileSearch.getSqlFiles();
                SqlChecker.check(sqlFiles);
            }
        }

        LogPrint.printStep(logger, "本次检查完成");

    }
    
    private static boolean isCheckJava()
    {
        return ConfigFileReader.getInstance().getCheckJava().equalsIgnoreCase("true");
    }
    
    private static boolean isCheckCsv()
    {
        return ConfigFileReader.getInstance().getCheckCsv().equalsIgnoreCase("true");
    }
    
    private static boolean isCheckXml()
    {
        return ConfigFileReader.getInstance().getCheckXml().equalsIgnoreCase("true");
    }
    
    private static boolean isCheckSql()
    {
        return ConfigFileReader.getInstance().getCheckSql().equalsIgnoreCase("true");
    }
}
