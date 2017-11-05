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
        results.add("r1700 | menghui | 2012-11-05 11:25:15 +0800 (星期一, 05 十一月 2012) | 6 lines");
        results.add("Changed paths:");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/svr/AlarmGlobalSvr.java");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/ui/AlarmGlobalPanel.java");
        results.add("");
        results.add("[CQ单号]:");
        results.add("[解决方法]:");
        results.add("[变更原因]:9836-V2.0版本-增加告警网管应答开关---新老版本map网元是否支持此功能需判断");
        results.add("[影响分析]:");
        results.add("[Checkstyle30+8检查]:Y");
        results.add("[走查人]:lanjianyi");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(1700);
        expectedInfo.setAuthor("menghui");
        expectedInfo.setModifyTime("2012-11-05 11:25:15");
        expectedInfo.setNoteStr("\n[CQ单号]:\n[解决方法]:\n[变更原因]:9836-V2.0版本-增加告警网管应答开关---新老版本map网元是否支持此功能需判断\n[影响分析]:\n[Checkstyle30+8检查]:Y\n");
        
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
        results.add("r1700 | menghui | 2012-11-05 11:25:15 +0800 (周一, 2012-11-05) | 6 行");
        results.add("改变的路径:");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/svr/AlarmGlobalSvr.java");
        results.add("   M /trunk/map/src/com/zte/ums/an/map/conf/alarmcfg/alarmglobal/ui/AlarmGlobalPanel.java");
        results.add("");
        results.add("[CQ单号]:");
        results.add("[解决方法]:");
        results.add("[变更原因]:9836-V2.0版本-增加告警网管应答开关---新老版本map网元是否支持此功能需判断");
        results.add("[影响分析]:");
        results.add("[Checkstyle30+8检查]:Y");
        results.add("[走查人]:lanjianyi");
        results.add("------------------------------------------------------------------------");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(1700);
        expectedInfo.setAuthor("menghui");
        expectedInfo.setModifyTime("2012-11-05 11:25:15");
        expectedInfo.setNoteStr("\n[CQ单号]:\n[解决方法]:\n[变更原因]:9836-V2.0版本-增加告警网管应答开关---新老版本map网元是否支持此功能需判断\n[影响分析]:\n[Checkstyle30+8检查]:Y\n[走查人]:lanjianyi\n");
        
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
        results.add("r18490 | chenduoduo | 2012-07-25 20:21:44 +0800 (周三, 25 七月 2012) | 5 lines");
        results.add("Changed paths:");
        results.add("   D /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java");
        results.add("   A /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/newCC.java (from /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java:18488)");
        results.add("");
        results.add("[CQ单号]:");
        results.add("[解决方法]:");
        results.add("[变更原因]:");
        results.add("[影响分析]:");
        results.add("[走查人]:");
        results.add("------------------------------------------------------------------------");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(18490);
        expectedInfo.setAuthor("chenduoduo");
        expectedInfo.setModifyTime("2012-07-25 20:21:44");
        expectedInfo.setNoteStr("\n[CQ单号]:\n[解决方法]:\n[变更原因]:\n[影响分析]:\n[走查人]:\n");
        
        ArrayList<ChangedPathInfo> changedPaths = new ArrayList<ChangedPathInfo>();
        expectedInfo.setChangedPaths(changedPaths);
        
        ChangedPathInfo pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_DELTE);
        pathInfo.setPath("/trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java");
        changedPaths.add(pathInfo);
        
        pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_ADD);
        pathInfo.setPath("/trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/newCC.java");
        pathInfo.setCopyFromPath("/trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java");
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
        results.add("r18490 | chenduoduo | 2012-07-25 20:21:44 +0800 (周三, 2012-07-25) | 5 行");
        results.add("改变的路径:");
        results.add("   D /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java");
        results.add("   A /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/newCC.java (从 /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java:18488)");
        results.add("");
        results.add("[CQ单号]:");
        results.add("[解决方法]:");
        results.add("[变更原因]:");
        results.add("[影响分析]:");
        results.add("[走查人]:");
        results.add("------------------------------------------------------------------------");
        
        FileSvnLogInfo expectedInfo = new FileSvnLogInfo();
        expectedInfo.setRevision(18490);
        expectedInfo.setAuthor("chenduoduo");
        expectedInfo.setModifyTime("2012-07-25 20:21:44");
        expectedInfo.setNoteStr("\n[CQ单号]:\n[解决方法]:\n[变更原因]:\n[影响分析]:\n[走查人]:\n");
        
        ArrayList<ChangedPathInfo> changedPaths = new ArrayList<ChangedPathInfo>();
        expectedInfo.setChangedPaths(changedPaths);
        
        ChangedPathInfo pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_DELTE);
        pathInfo.setPath("/trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java");
        changedPaths.add(pathInfo);
        
        pathInfo = new ChangedPathInfo();
        pathInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_ADD);
        pathInfo.setPath("/trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/newCC.java");
        pathInfo.setCopyFromPath("/trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF/cc.java");
        pathInfo.setCopyFromRevision("18488");
        changedPaths.add(pathInfo);
        
        FileSvnLogInfo actualInfo = SvnLogQuery.assembleFileLogInfo(results);
        assertEquals(expectedInfo, actualInfo);
    }
}
