package cdd.zte.checktools.common;

import org.apache.log4j.Logger;

/**
 * 
 * Error级别，窗口和日志都记录；
 * Info级别，窗口和日志都记录；
 * Debug级别，仅日志记录；
 * printStep方法是Info级别的一种特殊实现。
 * 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        2011-4-17
 */
public class LogPrint
{
    private static final String LINE = "=======================";
    private static boolean isConsoleOut = true;
    
    public static void enableConsoleOut(boolean en)
    {
        isConsoleOut  = en;
    }
    
    public static void logError(Logger logger, Exception ex)
    {
        printStackTrace(ex);
        logger.error(ex);
        
    }

    public static void logError(Logger logger, String errorDesc, Exception ex)
    {
        printStackTrace(ex);
        logger.error(errorDesc + ":" + ex);
    }
    
    public static void logError(Logger logger, String info)
    {
        printInfo(info);
        logger.error(info);
    }

    public static void logDebug(Logger logger, String info)
    {       
        logger.debug(info);
    }
    
    public static void logInfo(Logger logger, String info)
    {
        printInfo(info);
        logger.info(info);
    }
    
    public static void printStep(Logger logger, String stepInfo)
    {
        String info = LINE + stepInfo + LINE;

        printInfo("\n" + info + "\n");
        logger.info(info);
    }
    
    private static void printStackTrace(Exception ex)
    {
        if(isConsoleOut)
        {
            ex.printStackTrace();
        }
    }
    
    private static void printInfo(String info)
    {
        if(isConsoleOut)
        {
            System.out.println(info);
        }
    }

    private LogPrint()
    {
    }
}
