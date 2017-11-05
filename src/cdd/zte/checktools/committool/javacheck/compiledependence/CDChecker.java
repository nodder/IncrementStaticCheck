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
            LogPrint.logInfo(logger, "�ļ�" +  javaFile.getName() + "δ��������������򣬲����б���������顣");
            return true;
        }
        
        for (int i = 0; i < importPackets.size(); i++)
        {
            if(isNeedCheckCDRule(importPackets.get(i), fileClass))
            {
                if(isViolateCD(fileLevel, importPackets.get(i)))
                {     
                    result = false;
                    LogPrint.logError(logger, "\n������: " + importPackets.get(i) + "���Ϸ���");
                    LogPrint.logError(logger, "�ļ�: " + javaFile + "\n���ڱ����������ϣ���\n");
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
