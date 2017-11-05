package cdd.zte.checktools.committool.csvchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;

public class CsvChecker
{
    private static Logger logger = Logger.getLogger(CsvChecker.class.getName());
    
    private static int sucess = 0;
    private static int fail = 0;
    private static boolean isLogSuccess = false;
    
    private CsvChecker()
    {
    }
    
    public static void check(ArrayList<File> csvFileList)
    {
        reset(csvFileList.size());
        
        LogPrint.printStep(logger, "开始检查CSV文件格式");

        BufferedReader buffReader = null;
        FileReader fileReader = null;
        
        try
        {
            if(csvFileList.size() == 0)
            {
                LogPrint.logInfo(logger, "该目录下没有找到csv文件。");
                return;
            }
            
            for(int i = 0; i < csvFileList.size(); i++)
            {
                boolean isSuccess = true;
                File csvFile = csvFileList.get(i);               
                isSuccess = checkCsvByLine(isSuccess, csvFile);
                
                logSingleCsvResult(isSuccess, csvFile);
            }
            
            LogPrint.logInfo(logger, "csv检查结果：成功数 " + sucess + "   失败数 " + fail + "   总数 " + csvFileList.size());
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
            CommonUtil.closeFileReader(fileReader);
            CommonUtil.closeBufferedReader(buffReader);
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

    private static void logSingleCsvResult(boolean isSuccess, File csvFile)
    {
        if(isSuccess)
        {
            if(isLogSuccess)
            {
                LogPrint.logInfo(logger, "文件：" + csvFile + "合法!");
            }

            sucess++;
        }
        else
        {
            fail++;
        }
    }

    private static boolean checkCsvByLine(boolean isSuccess, File csvFile) throws FileNotFoundException, IOException
    {
        BufferedReader buffReader;
        FileReader fileReader;
        fileReader = new FileReader(csvFile);
        buffReader = new BufferedReader(fileReader);
        
        String line;
        int lineNum = 1;
        while((line = buffReader.readLine()) != null)
        {
            if(!CsvLineRule.isLineValid(line))
            {
                LogPrint.logError(logger, "文件：" + csvFile + " 第 " + lineNum + "行不合法!!\n");
                isSuccess = false;
            }
            
            lineNum++;
        }
        return isSuccess;
    }
        
    
//    public static void main(String[] args)
//    {
//        ArrayList<File> csvFileList = new ArrayList<File>();
//        
//        csvFileList.add(new File("F:\\SVN_4X\\ZXNM01V4\\zxnm01\\map\\mirror\\ums-client\\procs\\ppus\\an.ppu\\an-map.pmu\\an-map-conf-wsf.par\\conf\\localize\\map-conf-ces-cescardprops-i18n.csv"));
//        csvFileList.add(new File("C:\\Users\\chenduoduo\\Desktop\\123.csv"));
//    
//        check(csvFileList);
//    }
}

