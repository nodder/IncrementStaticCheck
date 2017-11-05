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
    
    //�������ļ���aaa������aa.java,ab.java,ac.java�����
    public void testAddFolder()
    {
        createAndRunMainProcess(3, 18517);
    }
    
    //�޸�aa.java������һ���������
    public void testModifyAndReduce()
    {
        createAndRunMainProcess(-1, 18520);
    }
    
    //�޸�ab.java������һ���������
    public void testModifyAndAdd()
    {
        createAndRunMainProcess(1, 18522);
    }
    
    //���������ļ���bbb������ba.java,bb.java,bc.java�����ļ���subbbb���������subba.java,subbb.java,subbc.java�����
    public void testAddCompFolder()
    {
        createAndRunMainProcess(6, 18523);
    }
    
    //�޸�ba.java,subba.java������һ���������
    public void testAddCompFolderAndModify1()
    {
        createAndRunMainProcess(2, 18524);
    }
    
    //�޸�bb.java,subb.java������һ������subbbb�´���subbd.java  ���
    public void testAddCompFolderAndModify2()
    {
        createAndRunMainProcess(3, 18525);
    }
    
    /*********************** ����Ϊ����ɾ���������������Ĳ���  *********************************/
    
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
    
    /*********************** ����˲���  *********************************/
    
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
        System.out.println("��ʼ���汾�� (" + revision +")�������Ϣ��");
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
