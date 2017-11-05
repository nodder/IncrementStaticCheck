package cdd.zte.checktools.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;


public class Ask
{
    private static Logger logger = Logger.getLogger(Ask.class.getName());
    
    private final static String ACTION_YES = "y";
    private final static String ACTION_NO = "n";
    
    private final static String[] allowInputList = new String[] {
                                            "" + CommonConst.MODULE_COMMON, CommonConst.STR_MODULE_COMMON, 
                                            "" + CommonConst.MODULE_COMMONSH, CommonConst.STR_MODULE_COMMONSH, 
                                            "" + CommonConst.MODULE_UNI, CommonConst.STR_MODULE_UNI, 
                                            "" + CommonConst.MODULE_MAP, CommonConst.STR_MODULE_MAP, 
                                            "" + CommonConst.MODULE_DSL, CommonConst.STR_MODULE_DSL, 
                                            "" + CommonConst.MODULE_IS, CommonConst.STR_MODULE_IS, 
                                            "" + CommonConst.MODULE_AG, CommonConst.STR_MODULE_AG, 
                                            "" + CommonConst.MODULE_A10, CommonConst.STR_MODULE_A10,
                                            "" + CommonConst.MODULE_EODN, CommonConst.STR_MODULE_EODN,
                                            "" + CommonConst.MODULE_EASYOPTICAL, CommonConst.STR_MODULE_EASYOPTICAL,
                                            CommonConst.STR_MODULE_DEBUG,"q"};

    private Ask()
    {
    }
    
    public static int InputModuleForCheck()
    {
        String action = "";
        InputStreamReader systemInReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(systemInReader);
        
        try
        {
            while(action == null || !isInList(allowInputList, action.trim()))
            {
                System.out.println(assemblePromptForCheck());
                action = reader.readLine();
            }

             if(action.trim().equalsIgnoreCase("q"))
             {
                 System.out.println("退出4X自动Local Check工具。");
                 return -1;
             }    
        }
        catch(IOException ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
//            CommonUtil.closeInputStreamReader(systemInReader);
//            CommonUtil.closeBufferedReader(reader);
        }
        
        int module = CommonUtil.getModuleFromStr(action.trim());
        
        LogPrint.logInfo(logger, "您选择的模块是:" + CommonUtil.getModuleName(module));
        
        if(CommonUtil.getModuleName(module).equalsIgnoreCase(CommonConst.STR_MODULE_DEBUG))
        {
            LogPrint.logInfo(logger, "\n您已经进入隐藏关卡！：)\n");
        }
        
        return module;
    }
    
    private static String assemblePromptForCheck()
    {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("请输入要检查的模块名称：\n")
              .append(CommonConst.MODULE_COMMON).append(".     ").append(CommonConst.STR_MODULE_COMMON).append("\n")
              .append(CommonConst.MODULE_COMMONSH).append(".     ").append(CommonConst.STR_MODULE_COMMONSH).append("\n")
              .append(CommonConst.MODULE_UNI).append(".     ").append(CommonConst.STR_MODULE_UNI).append("\n")
              .append(CommonConst.MODULE_MAP).append(".     ").append(CommonConst.STR_MODULE_MAP).append("\n")
              .append(CommonConst.MODULE_DSL).append(".     ").append(CommonConst.STR_MODULE_DSL).append("\n")
              .append(CommonConst.MODULE_IS).append(".     ").append(CommonConst.STR_MODULE_IS).append("\n")
              .append(CommonConst.MODULE_AG).append(".     ").append(CommonConst.STR_MODULE_AG).append("\n")
              .append(CommonConst.MODULE_A10).append(".     ").append(CommonConst.STR_MODULE_A10).append("\n")
              .append(CommonConst.MODULE_EODN).append(".     ").append(CommonConst.STR_MODULE_EODN).append("\n")
              .append(CommonConst.MODULE_EASYOPTICAL).append(".     ").append(CommonConst.STR_MODULE_EASYOPTICAL).append("\n")
              .append("q.    退出\n");
        
        return strBuf.toString();
    }
    
    public static boolean isContinueCheck()
    {      
        String action = "";
        InputStreamReader systemInReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(systemInReader);

        try
        {
            while(action == null || (!ACTION_YES.equalsIgnoreCase(action.trim()) && !ACTION_NO.equalsIgnoreCase(action.trim())))
            {
                System.out.println("是否继续检查入库信息?[Y/N]");
                action = reader.readLine();
            }

            if(ACTION_NO.equals(action))
            {
                return false;
            }
        }
        catch(IOException ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
//            CommonUtil.closeInputStreamReader(systemInReader);
//            CommonUtil.closeBufferedReader(reader);
        }
        
        return true;
    }
    
    private static boolean isInList(String[] allowInputList, String input)
    {
        for(int i = 0; i < allowInputList.length; i++)
        {
            if(allowInputList[i].equalsIgnoreCase(input))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static File inputPathOrFile()
    {      
        String path = "";
        InputStreamReader systemInReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(systemInReader);

        try
        {
            System.out.println("请输入需要检查的文件或路径：");
            path = removeQuotation(reader.readLine().trim());

            while(!(new File(path)).exists())
            {
                System.out.println("输入无效! 请重新输入：");
                path = removeQuotation(reader.readLine().trim());
            }
        }
        catch(IOException ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
//            CommonUtil.closeInputStreamReader(systemInReader);
//            CommonUtil.closeBufferedReader(reader);
        }
        
        return new File(path);
    }

    private static String removeQuotation(String action)
    {
        if(action.startsWith("\"") && action.endsWith("\""))
        {
            action = action.substring(1, action.length() - 1);
        }
        return action;
    }
}
