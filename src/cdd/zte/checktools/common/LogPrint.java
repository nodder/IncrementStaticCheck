package cdd.zte.checktools.common;

import org.apache.log4j.Logger;

/**
 * 
 * Error���𣬴��ں���־����¼��
 * Info���𣬴��ں���־����¼��
 * Debug���𣬽���־��¼��
 * printStep������Info�����һ������ʵ�֡�
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
