package cdd.zte.checktools.localchecktool;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import cdd.zte.checktools.common.ClassExclusionFileReaderStub;
import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.ConfigFileReaderStub;
import cdd.zte.checktools.common.cmds.EnvLanguage;
import cdd.zte.checktools.common.cmds.SvnVersionQuery;
import cdd.zte.checktools.localchecktool.localcheck.LocalCheck30Plus8;
import cdd.zte.checktools.localchecktool.mainprocess.AbsMainProcess;
import cdd.zte.checktools.localchecktool.revisonlist.SpcefiedRevision;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SingleCheckRunnerTest extends TestCase
{
    private static ClassExclusionFileReaderStub classExclusionFileReaderStub = new ClassExclusionFileReaderStub();
    
    static
    {
        Logger.getRootLogger().setLevel(Level.DEBUG);
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(new ConsoleAppender(new PatternLayout("%m%n"), "System.out"));
        
        CommonConst.URL_DEBUG = "https://10.67.9.84:8443/svn/ZXNM01V4_DOC";
        CommonConst.RESPONSITORY_DEBUG = CommonConst.URL_DEBUG;
        CommonConst.configFileReader = new ConfigFileReaderStub();
        CommonConst.classExclusionFileReader = classExclusionFileReaderStub;
        CommonConst.SOURCE_FOLDERS = new String[] {"test"};
        
        try
        {
            EnvLanguage.setLanguage(SvnVersionQuery.isSvnEnglishVersion() ? EnvLanguage.LANG_ENG : EnvLanguage.LANG_CHI);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //创建单文件夹aaa，包含aa.java,ab.java,ac.java，入库
    public void testAddFolder()
    {
        createAndRunMainProcess(3, 18517);
    }
    
    //修改aa.java，减少一个错误，入库
    public void testModifyAndReduce()
    {
        createAndRunMainProcess(-1, 18520);
    }
    
    //修改ab.java，增加一个错误，入库
    public void testModifyAndAdd()
    {
        createAndRunMainProcess(1, 18522);
    }
    
    //创建复合文件夹bbb，包含ba.java,bb.java,bc.java，子文件夹subbbb，里面包含subba.java,subbb.java,subbc.java，入库
    public void testAddCompFolder()
    {
        createAndRunMainProcess(6, 18523);
    }
    
    //修改ba.java,subba.java都增加一个错误，入库
    public void testAddCompFolderAndModify1()
    {
        createAndRunMainProcess(2, 18524);
    }
    
    //修改bb.java,subb.java都增加一个错误；subbbb下创建subbd.java  入库
    public void testAddCompFolderAndModify2()
    {
        createAndRunMainProcess(3, 18525);
    }
    
    /*********************** 后面为包含删除、重命名操作的测试  *********************************/
    
    public void test18485()
    {
        createAndRunMainProcess(-1, 18485);
    }
    
    public void test18486()
    {
        createAndRunMainProcess(3, 18486);
    }
    
    public void test18487()
    {
        createAndRunMainProcess(-3, 18487);
    }
    
    public void test18489()
    {
        createAndRunMainProcess(-5, 18489);
    }
    
    public void test18490()
    {
        createAndRunMainProcess(0, 18490);
    }
    
    public void test18491()
    {
        createAndRunMainProcess(6, 18491);
    }
    
    public void test18492()
    {
        createAndRunMainProcess(-6, 18492);
    }
    
    public void test18494()
    {
        createAndRunMainProcess(0, 18494);
    }
    
    public void test18600()
    {
        createAndRunMainProcess(6, 18600);
    }
    
    public void test18601()
    {
        createAndRunMainProcess(6, 18601);
    }
    
    public void test18602()
    {
        createAndRunMainProcess(2, 18602);
    }
    
    public void test18604()
    {
        createAndRunMainProcess(2, 18604);
    }
    
    public void test18606()
    {
        createAndRunMainProcess(0, 18606);
    }
    
    public void test18613()
    {
        createAndRunMainProcess(2, 18613);
    }
    
    public void test18616()
    {
        createAndRunMainProcess(-1, 18616);
    }
    
    /*********************** 类过滤测试  *********************************/
    
    public void test18489_with_exclude()
    {
        classExclusionFileReaderStub.setExcludePattern("comF.inComF.**");
        
        createAndRunMainProcess(-2, 18489);
        
        classExclusionFileReaderStub.clearPattern();
    }
    
    private void createAndRunMainProcess(final int expectedDiff, int revisionsToCheck)
    {
        AbsMainProcess mainProc = new MainProcessStub(revisionsToCheck) {

            @Override
            public void afterRunSingleCheck(SingleCheckResult result)
            {
                assertEquals(expectedDiff, result.diff);
            }
        };
        
        mainProc.runProcess();
    }
}

abstract class MainProcessStub extends AbsMainProcess
{
    public MainProcessStub(int revisionsToCheck)
    {
        super(CommonConst.MODULE_DEBUG);
        setRevisonListMaint(new SpcefiedRevision(revisionsToCheck));
        setLocalCheckBat(new LocalCheck30Plus8());
    }

    @Override
    public boolean isContinueNextCheck(SingleCheckResult result)
    {
        return true;
    }

    @Override
    public void beforeRunSingleCheck(int revision)
    {
        System.out.println("开始检查版本号 (" + revision +")的入库信息。");
    }
    
    @Override
    public boolean isThisRevisionNeedCheck(int revision)
    {
        return true;
    }
    
    @Override
    public void doSomethingForNoNeedCheckVersion(int revision)
    {
    }

    @Override
    public void afterAllCheck(ArrayList<SingleCheckResult> checkResults)
    {
    }
    
    @Override
    public void beforeDoCheck() throws Exception
    {
    }
}
