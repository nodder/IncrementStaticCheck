package cdd.zte.checktools.localchecktool;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cdd.zte.checktools.common.Ask;
import cdd.zte.checktools.common.ClassExclusionFileReader;
import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.ConfigFileReader;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.VersionConst;
import cdd.zte.checktools.common.cmds.EnvLanguage;
import cdd.zte.checktools.common.cmds.SvnVersionQuery;
import cdd.zte.checktools.localchecktool.cireport.CIReportUtil;
import cdd.zte.checktools.localchecktool.cireport.ReportForException;
import cdd.zte.checktools.localchecktool.mainprocess.AbsMainProcess;
import cdd.zte.checktools.localchecktool.mainprocess.CI30Plus8MainProcess;
import cdd.zte.checktools.localchecktool.mainprocess.CIStaticCheckMainProcess;
import cdd.zte.checktools.localchecktool.mainprocess.PersonalEditionMainProcess;

public class Start
{    
    static
    {
        String propFile = (new File(System.getProperty("user.dir"))).getAbsolutePath() + "/conf/log4j.properties";
        PropertyConfigurator.configure(propFile);
    }
    
    private static Logger logger = Logger.getLogger(Start.class.getName());
    
    private static String transModuleFrmCi(String moduleFromCI)
    {
        if(moduleFromCI.equalsIgnoreCase("common_sh"))
        {
            return "COMMONSH";
        }
        else
        {
            return moduleFromCI.toUpperCase();
        }
    }
    
    private static void init() throws Exception
    {
        CommonConst.URL_DEBUG = ConfigFileReader.getInstance().getDebugResponsitory();
        CommonConst.RESPONSITORY_DEBUG = CommonConst.URL_DEBUG;
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

    private static void emptyReportBaseDir() throws IOException
    {
        File reportBaseDir = new File(CIReportUtil.getReportBaseDir()).getAbsoluteFile();
        CommonUtil.createdDirIfNoExist(reportBaseDir.getAbsolutePath());
        CommonUtil.deleteFilesWithIgnore(reportBaseDir, ".svn");
    }
    
    private static void welcome()
    {
        String welcome = "***************  欢迎使用静态缺陷增量检查工具(" + VersionConst.getVersion() + ")  ***************\n";
        LogPrint.logInfo(logger, welcome);
    }
    
    public static void main(String[] args) throws Exception
    {
        welcome();
        init();
        
        if(args.length == 3)
        {
            startForCIUsage(args);
        }
        else
        {
            startForPersonalUsage();
        }
    }

    private static void startForCIUsage(String[] args)
    {
        LogPrint.enableConsoleOut(false);
        
        int module = CommonUtil.getModuleFromStr(transModuleFrmCi(args[0]));
        if(module == -1)
        {
            String errorStr = "无法识别的module:" + args[0];
            LogPrint.logInfo(logger, errorStr);
            System.exit(CommonConst.TOOL_RESULT_EXCEPTION_OCCURRED_IN_TOOL);
        }
        
        int startRevision = Integer.parseInt(args[1]);
        int endRevision = Integer.parseInt(args[2]);
        
        try
        {
            emptyReportBaseDir();
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "IOException happens when emptyReportBaseDir, about to exit", e);
            new ReportForException(module).doReport(-1, e.getMessage());
            System.exit(CommonConst.TOOL_RESULT_EXCEPTION_OCCURRED_IN_TOOL);
        }
        
        if(startRevision >= endRevision)
        {
            try
            {
                new File("report/no_need_to_check.txt").createNewFile();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            LogPrint.logInfo(logger, "无须检查。 起始版本号为" + startRevision + "，终止版本号为" + endRevision);
            System.exit(CommonConst.TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_PASS_AND_NO_NEED_MANUAL_CHECK_30PLUS8);
        }
        
        int actualStartRevision = startRevision + 1;
        new CIStaticCheckMainProcess(module, actualStartRevision, endRevision).runProcess();
        new CI30Plus8MainProcess(module, actualStartRevision, endRevision).runProcess();
    }

    private static void startForPersonalUsage()
    {
        int module = Ask.InputModuleForCheck();   
        
        if(module != -1)
        {
            AbsMainProcess mainProc = new PersonalEditionMainProcess(module);
            mainProc.runProcess();
        }
    }
}
