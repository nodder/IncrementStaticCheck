package cdd.zte.checktools.localchecktool.task;

import java.io.File;
import java.util.Arrays;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.ConfigFileReaderStub;
import cdd.zte.checktools.common.FileSearcher;
import cdd.zte.checktools.common.cmds.EnvLanguage;
import cdd.zte.checktools.common.cmds.SvnVersionQuery;
import cdd.zte.checktools.localchecktool.export.ExportCenter;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ExportProcessTest extends TestCase
{
    static
    {
        Logger.getRootLogger().setLevel(Level.DEBUG);
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(new ConsoleAppender(new PatternLayout("%m%n"), "System.out"));
        
        CommonConst.URL_DEBUG = "https://10.67.9.84:8443/svn/ZXNM01V4_DOC";
        CommonConst.RESPONSITORY_DEBUG = CommonConst.URL_DEBUG;
        CommonConst.configFileReader = new ConfigFileReaderStub();
//        CommonConst.module = CommonConst.MODULE_COMMON;
        
        
        try
        {
            CommonConst.SOURCE_FOLDERS = new String[]{"test"};
            EnvLanguage.setLanguage(SvnVersionQuery.isSvnEnglishVersion() ? EnvLanguage.LANG_ENG : EnvLanguage.LANG_CHI);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String exportPath = "./test/checkTool";
    
    /**********************  简单测试用例，仅包含创建、修改的测试用例 ********************************/
    
    //创建单文件夹aaa，包含aa.java,ab.java,ac.java，入库
    public void test18517() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18517);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "aa.java", "ab.java", "ac.java"));
        checkDirForRevison(currFiles, 18517);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
        checkDirForRevison(OldFiles, 18516);
    }
    
    //修改aa.java，减少一个错误，入库
    public void test18520() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18520);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "aa.java"));
        checkDirForRevison(currFiles, 18520);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "aa.java"));
        checkDirForRevison(OldFiles, 18519);
    }
    
    //修改ab.java，增加一个错误，入库
    public void test18522() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18522);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ab.java"));
        checkDirForRevison(currFiles, 18522);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ab.java"));
        checkDirForRevison(OldFiles, 18521);
    }
    
    //创建复合文件夹bbb，包含ba.java,bb.java,bc.java，子文件夹subbbb，里面包含subba.java,subbb.java,subbc.java，入库
    public void test18523() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18523);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ba.java", "bb.java", "bc.java", "subba.java", "subbb.java", "subbc.java"));
        checkDirForRevison(currFiles, 18523);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    //修改ba.java,subba.java都增加一个错误，入库
    public void test18524() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18524);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ba.java", "subba.java"));
        checkDirForRevison(currFiles, 18524);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ba.java", "subba.java"));
        checkDirForRevison(OldFiles, 18523);
    }
    
    //修改bb.java,subb.java都增加一个错误；subbbb下创建subbd.java  入库
    public void test18525() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18525);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "bb.java", "subbb.java", "subbd.java"));
        checkDirForRevison(currFiles, 18525);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "bb.java", "subbb.java"));
        checkDirForRevison(OldFiles, 18524);
    }
    
    /**********************  包含删除、重命名的测试用例 ********************************/
    
    //删除sa.java，入库
    public void test18485() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18485);
        procCurr.runExportProcess();
        
        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(currFiles.length == 0);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "sa.java"));
        checkDirForRevison(OldFiles, 18484);
    }
    
    //删除simF2，入库
    public void test18487() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18487);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(currFiles.length == 0);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "sa2.java", "sb2.java", "sc2.java"));
        checkDirForRevison(OldFiles, 18486);
    }
    
    //删除comF下的ca.java,cb.java，删除inComF，入库
    public void test18489() throws Exception
    {
        String exportPath = "./test/checkTool";
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18489);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(currFiles.length == 0);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(OldFiles, 18488);
    }
    
    //重命名cc.java为newCC.java，入库
    public void test18490() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18490);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "newCC.java"));
        checkDirForRevison(currFiles, 18490);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "cc.java"));
        checkDirForRevison(OldFiles, 18489);
    }
    
    //删除comF2，入库
    public void test18492() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18492);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(currFiles.length == 0);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(OldFiles, 18491);
    }
    
    //重命名comF3为newComF3，入库
    public void test18494() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18494);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
    }
    
    //修改所有6个java文件，各增加一个错误，重命名comF4为newComF4，入库
    public void test18600() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18600);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(currFiles, 18600);
        
        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(OldFiles, 18599);
    }
    
    //修改ca.java、inCA.java，各增加一个错误；  删除cb.java、inCB.java  创建cd.java、inCD.java 重命名comF5为newComF5，入库
    public void test18602() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18602);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cc.java", "inCA.java", "inCC.java", "cd.java", "inCD.java"));
        checkDirForRevison(currFiles, 18602);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(OldFiles, 18601);
    }
    
    //修改ca.java、inCA.java，各增加一个错误；   删除cb.java、inCB.java  创建cd.java、inCD.java  重命名inComF为newInComF 重命名comF6为newComF6，入库
    public void test18604() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18604);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cc.java", "inCA.java", "inCC.java", "cd.java", "inCD.java"));
        checkDirForRevison(currFiles, 18604);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(OldFiles, 18603);
    }
    
    //修改ca.java、inCA.java，各增加一个错误；   删除cb.java、inCB.java  创建cd.java、inCD.java  重命名inComF为newInComF 重命名comF7为newComF7，删除sa.java、sb.java入库
    public void test18606() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18606);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cc.java", "inCA.java", "inCC.java", "cd.java", "inCD.java"));
        checkDirForRevison(currFiles, 18606);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java", "sa.java", "sb.java"));
        checkDirForRevison(OldFiles, 18605);
    }
    
    /**********************  创建、修改的文件已经被后面某次入库时删除掉了 ********************************/
    
    //创建单文件夹simF，包含sa.java,sb.java,sc.java，入库
    //sa.java已被删除
    public void test18484() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18484);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "sa.java", "sb.java", "sc.java"));
        checkDirForRevison(currFiles, 18484);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
   
    //创建单文件夹smnF2，包含sa2.java,sb2.java,sc2.java，入库
    //文件夹simF2已被删除
    public void test18486() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18486);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "sa2.java", "sb2.java", "sc2.java"));
        checkDirForRevison(currFiles, 18486);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    //创建复合文件夹comF，包含ca.java,cb.java,cc.java，一个单文件夹inComF，inComF包含inCA.java,inCB.java,inCC.java，入库
    //ca.java,cb.java，删除inComF  已被删除
    public void test18488() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18488);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(currFiles, 18488);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    //创建复合文件夹comF2，内容和comF一样，入库
    //后续comF3为newComF3
    public void test18493() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18493);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(currFiles, 18493);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    //创建复合文件夹comF2，内容和comF一样，入库
    //后续有复杂修改
    public void test18603() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18603);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java"));
        checkDirForRevison(currFiles, 18603);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    //创建复合文件夹comF2，内容和comF一样，入库
    //后续有复杂修改
    public void test18605() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18605);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java", "inCA.java", "inCB.java", "inCC.java", "sa.java", "sb.java", "sc.java"));
        checkDirForRevison(currFiles, 18605);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    //修改inD2.java、inD1.java，各增加一个错误，入库
    public void test18613() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18613);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "inD1.java", "inD2.java"));
        checkDirForRevison(currFiles, 18613);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "inD1.java", "inD2.java"));
        checkDirForRevison(OldFiles, 18612);
    }
    
    //删除inD2.java，入库
    public void test18616() throws Exception
    {
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18616);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(currFiles.length == 0);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "inD2.java"));
        checkDirForRevison(OldFiles, 18615);
    }
    
    //18488 创建复合文件夹comF，包含ca.java,cb.java,cc.java，一个单文件夹inComF，inComF包含inCA.java,inCB.java,inCC.java，入库
    //18489 删除ca.java,cb.java，inComF
    public void test18488_exclude() throws Exception
    {
        String[] toBeExcludedClassPattern = new String[] {"comF.inComF.**"};
        
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18488, toBeExcludedClassPattern);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(fileNameEquals(currFiles, "ca.java", "cb.java", "cc.java"));
        checkDirForRevison(currFiles, 18488);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(OldFiles.length == 0);
    }
    
    public void test18489_exclude() throws Exception
    {
        String[] toBeExcludedClassPattern = new String[] {"comF.inComF.**"};
        
        String exportPath = "./test/checkTool";
        ExportCenter procCurr = new ExportCenter(CommonConst.MODULE_DEBUG, exportPath, 18489, toBeExcludedClassPattern);
        procCurr.runExportProcess();

        File[] currFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_CURR), "java");
        assertTrue(currFiles.length == 0);

        File[] OldFiles = new FileSearcher().search(new File(exportPath + CommonConst.TEMPDIR_EXPORT_OLD), "java");
        assertTrue(fileNameEquals(OldFiles, "ca.java", "cb.java"));
        checkDirForRevison(OldFiles, 18488);
    }
    
    private static void checkDirForRevison(File[] files, int revision)
    {
        for(File file : files)
        {
            String[] singleDirNames = file.getAbsolutePath().split("\\\\");
            assertTrue(arrContains(singleDirNames, Integer.toString(revision)));
        }
    }
    
    private static boolean fileNameEquals(File[] files, String... names)
    {
        String[] fileNames = new String[files.length];
        for(int i = 0; i < fileNames.length; i++)
        {
            fileNames[i] = files[i].getName();
        }
        
        for(String tmpName : names)
        {
            if(!arrContains(fileNames, tmpName))
            {
                System.out.println("fileNames:" + Arrays.toString(fileNames) + ",tmpName:" + tmpName);
                return false;
            }
        }
        
        for(String tmpFileName : fileNames)
        {
            if(!arrContains(names, tmpFileName))
            {
                System.out.println("names:" + Arrays.toString(names) + ",tmpFileName:" + tmpFileName);
                return false;
            }
        }
        
        assertEquals(names.length, files.length);
        
        return true;
    }
    
    private static boolean arrContains(String[] arr, String key)
    {
        for(String tmp : arr)
        {
            if(key.equals(tmp))
            {
                return true;
            }
        }
        
        return false;
    }
}
