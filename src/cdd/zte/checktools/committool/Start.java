package cdd.zte.checktools.committool;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cdd.zte.checktools.committool.common.CommitToolVersionConst;
import cdd.zte.checktools.common.Ask;
import cdd.zte.checktools.common.ClassExclusionFileReader;
import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.ConfigFileReader;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.cmds.EnvLanguage;
import cdd.zte.checktools.common.cmds.SvnVersionQuery;

public class Start
{
    private static Logger logger = Logger.getLogger(Start.class.getName());
    public static String userdir;
    
    private static void init(String userDir) throws Exception
    {
        String propFile = (new File(System.getProperty("user.dir"))).getAbsolutePath() + "/conf/log4j.properties";
        PropertyConfigurator.configure(propFile);
        
        LogPrint.logDebug(logger, "userDir == " + userDir);
        
        CommonConst.configFileReader = ConfigFileReader.getInstance();
        CommonConst.classExclusionFileReader = ClassExclusionFileReader.getInstance();
        
        boolean isSvnEnglishVersion = SvnVersionQuery.isSvnEnglishVersion();
        EnvLanguage.setLanguage(isSvnEnglishVersion ? EnvLanguage.LANG_ENG : EnvLanguage.LANG_CHI);
        
        String strVersionNo = SvnVersionQuery.getSvnVersionNo();
        logger.info("当前SVN的版本为" + strVersionNo + "; 语言为" + (isSvnEnglishVersion ? "英文" : "中文"));
        
        try
        {
            if(SvnVersionQuery.isVersionLessThan(strVersionNo, 1.6))
            {
                LogPrint.logError(logger, "SVN版本号必须为1.6以上， 当前版本：" + strVersionNo);
                System.exit(8);
            }
        }
        catch(NumberFormatException e)
        {
            LogPrint.logError(logger, "isVersionLessThan ex:", e);
        }
    }
    
    private static void welcome()
    {
        String welcome = "***************  欢迎使用本地代码静态检查工具(" + CommitToolVersionConst.getVersion() + ")  ***************\n";
        LogPrint.logInfo(logger, welcome);
    }
    
    private static boolean isFromDirRightClick(String[] args)
    {
        return args.length == 2;
    }
    
    private static void printInstruction()
    {
        System.out.println("本程序支持CSV/XML文件格式检查，支持JAVA代码编译依赖和Local Check入库前检查。");
        System.out.println("拷贝文件或路径到本窗口，即可自动识别文件类型并检查。\n");
    }
    
    public static void main(String[] args)
    {
        try
        {
            init(args[0]);
            welcome();

            if(isFromDirRightClick(args))
            {
                LogPrint.logInfo(logger, "当前需要执行入库检查的目录:\n" + args[1]);
                File path = new File(args[1]);
                
                MainProcess.doCheck(path);
            }
            else
            {
                printInstruction();

                while(true)
                {
                    File path = Ask.inputPathOrFile();

                    MainProcess.doCheck(path);
                }

            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
    }
}
