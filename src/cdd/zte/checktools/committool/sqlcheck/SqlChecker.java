package cdd.zte.checktools.committool.sqlcheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.LogPrint;

public class SqlChecker
{
    private static Logger logger = Logger.getLogger(SqlChecker.class.getName());

    private static String CHINESECHARREGEX = ".*([\u2E80-\uFFFD]).*"; // 用于检查中文及中文字符的正则表达式,实际包含了整个双字节区
 
    private static int sucess = 0;
    private static int fail = 0;
    private static boolean isLogSuccess = false;
    
    public static void check(ArrayList<File> sqlFileList)
    {
        reset(sqlFileList.size());
        
        LogPrint.printStep(logger, "开始检查SQL文件格式");
        
        try
        {
            if(sqlFileList.size() == 0)
            {
                LogPrint.logInfo(logger, "该目录下没有找到sql文件。");
                return;
            }
            
            for(int i = 0; i < sqlFileList.size(); i++)
            {
                boolean isSuccess = true;
                File csvFile = sqlFileList.get(i);               
                isSuccess = checkSql(csvFile);
                
                logSingleCsvResult(isSuccess, csvFile);
            }
            
            LogPrint.logInfo(logger, "sql检查结果：成功数 " + sucess + "   失败数 " + fail + "   总数 " + sqlFileList.size());
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
    }
    
    private static void reset(int fileCount)
    {
        sucess = 0;
        fail = 0;
        
        if(fileCount == 1)
        {
            isLogSuccess = true;
        }
        else
        {
            isLogSuccess = false;
        }
    }

    private static void logSingleCsvResult(boolean isSuccess, File sqlFile)
    {
        if(isSuccess)
        {
            if(isLogSuccess)
            {
                LogPrint.logInfo(logger, "文件：" + sqlFile + "合法!");
            }

            sucess++;
        }
        else
        {
            fail++;
        }
    }

    private static boolean checkSql(File csvFile) throws FileNotFoundException, IOException
    {
        boolean chineseChkPass = true;

        try
        {
            String line = "";
            FileReader singleFileReader = new FileReader(csvFile);
            LineNumberReader lineReader = new LineNumberReader(singleFileReader);

            while((line = lineReader.readLine()) != null)
            {
                if(line.matches(CHINESECHARREGEX))
                {
                    chineseChkPass = false;
                    LogPrint.logError(logger, "File " + csvFile.getName() + ": Line " + lineReader.getLineNumber() + ": Chinese char found!");
                    LogPrint.logError(logger, "Line " + lineReader.getLineNumber() + ": " + line + "\n");
                }

            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
        return chineseChkPass;
    }
}
