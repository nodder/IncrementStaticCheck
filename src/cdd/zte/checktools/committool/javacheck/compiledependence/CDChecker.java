package cdd.zte.checktools.committool.javacheck.compiledependence;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.LogPrint;

public class CDChecker
{
    private static Logger logger = Logger.getLogger(CDChecker.class.getName());
 
    private CDChecker()
    {
    }
    
    public static boolean check(File javaFile)
    {
        boolean result = true;
        ArrayList<String> importPackets = JavaFileReader.getImportedPackets(javaFile);
        
        String fileClass = JavaFileReader.getClass(javaFile);
        int fileLevel = CDRule.findLevel(fileClass);
        
        if(fileLevel == CDRule.RULE_LEVEL_UNDEFINED)
        {
            LogPrint.logInfo(logger, "文件" +  javaFile.getName() + "未列入编译依赖规则，不进行编译依赖检查。");
            return true;
        }
        
        for (int i = 0; i < importPackets.size(); i++)
        {
            if(isNeedCheckCDRule(importPackets.get(i), fileClass))
            {
                if(isViolateCD(fileLevel, importPackets.get(i)))
                {     
                    result = false;
                    LogPrint.logError(logger, "\n依赖包: " + importPackets.get(i) + "不合法。");
                    LogPrint.logError(logger, "文件: " + javaFile + "\n存在编译依赖故障！！\n");
                }
            }
        }
        
        return result;
    }

    private static boolean isNeedCheckCDRule(String importPacket, String fileClass)
    {
        return !(CDRule.isISModule(fileClass) || CDRule.isSameModule(fileClass, importPacket));
    }
    
    private static boolean isViolateCD(int fileLevel, String importPacket)
    {
        return fileLevel <= CDRule.findLevel(importPacket);
    }
}
