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
        logger.info("��ǰSVN�İ汾Ϊ" + strVersionNo + "; ����Ϊ" + (isSvnEnglishVersion ? "Ӣ��" : "����"));
        
        try
        {
            if(SvnVersionQuery.isVersionLessThan(strVersionNo, 1.6))
            {
                LogPrint.logError(logger, "SVN�汾�ű���Ϊ1.6���ϣ� ��ǰ�汾��" + strVersionNo);
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
        String welcome = "***************  ��ӭʹ�ñ��ش��뾲̬��鹤��(" + CommitToolVersionConst.getVersion() + ")  ***************\n";
        LogPrint.logInfo(logger, welcome);
    }
    
    private static boolean isFromDirRightClick(String[] args)
    {
        return args.length == 2;
    }
    
    private static void printInstruction()
    {
        System.out.println("������֧��CSV/XML�ļ���ʽ��飬֧��JAVA�������������Local Check���ǰ��顣");
        System.out.println("�����ļ���·���������ڣ������Զ�ʶ���ļ����Ͳ���顣\n");
    }
    
    public static void main(String[] args)
    {
        try
        {
            init(args[0]);
            welcome();

            if(isFromDirRightClick(args))
            {
                LogPrint.logInfo(logger, "��ǰ��Ҫִ��������Ŀ¼:\n" + args[1]);
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
