package cdd.zte.checktools.common.cmds;

import java.util.ArrayList;

import junit.framework.TestCase;
import cdd.zte.checktools.common.info.ChangedPathInfo;
import cdd.zte.checktools.common.info.FileSvnLogInfo;


/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SvnLogQueryTest extends TestCase
{
    //svn log -vr 1700:1700 g:\svntest
    public void testAssembleFileLogInfo_1dot6_En()
    {
        EnvLanguage.setLanguage(EnvLanguage.LANG_ENG);
        
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r1700 | menghui | 2012-11-05 11:25:15 +0800 (����һ, 05 ʮһ�� 2012) | 6 lines");
        results.add("Changed paths:");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/svr/AlarmGlobalSvr.java");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/ui/AlarmGlobalPanel.java");
        results.add("");
        results.add("[CQ����]:");
        results.add("[�������]:");
        results.add("[���ԭ��]:9836-V2.0�汾-���Ӹ澯����Ӧ�𿪹�---���ϰ汾map��Ԫ�Ƿ�֧�ִ˹������ж�");
        results.add("[Ӱ�����]:");
        results.add("[Checkstyle30+8���]:Y");
        results.add("[�߲���]:lanjianyi");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(1700);
        expectedInfo.setAuthor("menghui");
        expectedInfo.setModifyTime("2012-11-05 11:25:15");
        expectedInfo.setNoteStr("\n[CQ����]:\n[�������]:\n[���ԭ��]:9836-V2.0�汾-���Ӹ澯����Ӧ�𿪹�---���ϰ汾map��Ԫ�Ƿ�֧�ִ˹������ж�\n[Ӱ�����]:\n[Checkstyle30+8���]:Y\n");
        
        ArrayList<ChangedPathInfo> changedPaths = new ArrayList<ChangedPathInfo>();
        expectedInfo.setChangedPaths(changedPaths);
        
        ChangedPathInfo pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_MODIFY);
        pathInfo.setPath("/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/svr/AlarmGlobalSvr.java");
        changedPaths.add(pathInfo);
        
        pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_MODIFY);
        pathInfo.setPath("/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/ui/AlarmGlobalPanel.java");
        changedPaths.add(pathInfo);
        
        FileSvnLogInfo actualInfo = SvnLogQuery.assembleFileLogInfo(results);
        assertEquals(expectedInfo, actualInfo);
    }
    
    //svn log -vr 1700:1700 https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg
    public void testAssembleFileLogInfo_1dot7_Chi()
    {
        EnvLanguage.setLanguage(EnvLanguage.LANG_CHI);
        
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r1700 | menghui | 2012-11-05 11:25:15 +0800 (��һ, 2012-11-05) | 6 ��");
        results.add("�ı��·��:");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/svr/AlarmGlobalSvr.java");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/ui/AlarmGlobalPanel.java");
        results.add("");
        results.add("[CQ����]:");
        results.add("[�������]:");
        results.add("[���ԭ��]:9836-V2.0�汾-���Ӹ澯����Ӧ�𿪹�---���ϰ汾map��Ԫ�Ƿ�֧�ִ˹������ж�");
        results.add("[Ӱ�����]:");
        results.add("[Checkstyle30+8���]:Y");
        results.add("[�߲���]:lanjianyi");
        results.add("------------------------------------------------------------------------");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(1700);
        expectedInfo.setAuthor("menghui");
        expectedInfo.setModifyTime("2012-11-05 11:25:15");
        expectedInfo.setNoteStr("\n[CQ����]:\n[�������]:\n[���ԭ��]:9836-V2.0�汾-���Ӹ澯����Ӧ�𿪹�---���ϰ汾map��Ԫ�Ƿ�֧�ִ˹������ж�\n[Ӱ�����]:\n[Checkstyle30+8���]:Y\n[�߲���]:lanjianyi\n");
        
        ArrayList<ChangedPathInfo> changedPaths = new ArrayList<ChangedPathInfo>();
        expectedInfo.setChangedPaths(changedPaths);
        
        ChangedPathInfo pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_MODIFY);
        pathInfo.setPath("/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/svr/AlarmGlobalSvr.java");
        changedPaths.add(pathInfo);
        
        pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_MODIFY);
        pathInfo.setPath("/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/ui/AlarmGlobalPanel.java");
        changedPaths.add(pathInfo);
        
        FileSvnLogInfo actualInfo = SvnLogQuery.assembleFileLogInfo(results);
        assertEquals(expectedInfo, actualInfo);
    }
    

    //svn log -vr 1700:1700 https://10.67.9.84:8443/svn/ZXNM01V4_DOC
    public void testAssembleFileLogInfo_withCopyFromField_1dot7_En()
    {
        EnvLanguage.setLanguage(EnvLanguage.LANG_ENG);
        
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r18490 | chenduoduo | 2012-07-25 20:21:44 +0800 (����, 25 ���� 2012) | 5 lines");
        results.add("Changed paths:");
        results.add("   D /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java");
        results.add("   A /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/newCC.java (from /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java:18488)");
        results.add("");
        results.add("[CQ����]:");
        results.add("[�������]:");
        results.add("[���ԭ��]:");
        results.add("[Ӱ�����]:");
        results.add("[�߲���]:");
        results.add("------------------------------------------------------------------------");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(18490);
        expectedInfo.setAuthor("chenduoduo");
        expectedInfo.setModifyTime("2012-07-25 20:21:44");
        expectedInfo.setNoteStr("\n[CQ����]:\n[�������]:\n[���ԭ��]:\n[Ӱ�����]:\n[�߲���]:\n");
        
        ArrayList<ChangedPathInfo> changedPaths = new ArrayList<ChangedPathInfo>();
        expectedInfo.setChangedPaths(changedPaths);
        
        ChangedPathInfo pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_DELTE);
        pathInfo.setPath("/trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java");
        changedPaths.add(pathInfo);
        
        pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_ADD);
        pathInfo.setPath("/trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/newCC.java");
        pathInfo.setCopyFromPath("/trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java");
        pathInfo.setCopyFromRevision("18488");
        changedPaths.add(pathInfo);
        
        FileSvnLogInfo actualInfo = SvnLogQuery.assembleFileLogInfo(results);
        assertEquals(expectedInfo, actualInfo);
    }
    
    //svn log -vr 1700:1700 https://10.67.9.84:8443/svn/ZXNM01V4_DOC
    public void testAssembleFileLogInfo_withCopyFromField_1dot7_CHI()
    {
        EnvLanguage.setLanguage(EnvLanguage.LANG_CHI);
        
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r18490 | chenduoduo | 2012-07-25 20:21:44 +0800 (����, 2012-07-25) | 5 ��");
        results.add("�ı��·��:");
        results.add("   D /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java");
        results.add("   A /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/newCC.java (�� /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java:18488)");
        results.add("");
        results.add("[CQ����]:");
        results.add("[�������]:");
        results.add("[���ԭ��]:");
        results.add("[Ӱ�����]:");
        results.add("[�߲���]:");
        results.add("------------------------------------------------------------------------");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(18490);
        expectedInfo.setAuthor("chenduoduo");
        expectedInfo.setModifyTime("2012-07-25 20:21:44");
        expectedInfo.setNoteStr("\n[CQ����]:\n[�������]:\n[���ԭ��]:\n[Ӱ�����]:\n[�߲���]:\n");
        
        ArrayList<ChangedPathInfo> changedPaths = new ArrayList<ChangedPathInfo>();
        expectedInfo.setChangedPaths(changedPaths);
        
        ChangedPathInfo pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_DELTE);
        pathInfo.setPath("/trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java");
        changedPaths.add(pathInfo);
        
        pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_ADD);
        pathInfo.setPath("/trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/newCC.java");
        pathInfo.setCopyFromPath("/trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF/cc.java");
        pathInfo.setCopyFromRevision("18488");
        changedPaths.add(pathInfo);
        
        FileSvnLogInfo actualInfo = SvnLogQuery.assembleFileLogInfo(results);
        assertEquals(expectedInfo, actualInfo);
    }
}
