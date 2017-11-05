package cdd.zte.checktools.common.cmds;

import java.util.ArrayList;

import junit.framework.TestCase;
import cdd.zte.checktools.common.info.FileSvnInfo;

public class SvnInfoQureyTest extends TestCase
{
    //svn info g:\svntest
    public void testSvnInfoQuery_1dot6_En() throws Exception
    {
        EnvLanguage.setLanguage(EnvLanguage.LANG_ENG);
        
        ArrayList<String> results = new ArrayList<String>();
        results.add("Path: G:\\svntest");
        results.add("URL: https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg");
        results.add("Repository Root: https://10.67.9.84:8443/svn/NETNUMENN31_MAP");
        results.add("Repository UUID: 3c449629-a34d-f645-82e2-9fe792e1c782");
        results.add("Revision: 1864");
        results.add("Node Kind: directory");
        results.add("Schedule: normal");
        results.add("Last Changed Author: lanjianyi");
        results.add("Last Changed Rev: 1813");
        results.add("Last Changed Date: 2012-12-07 17:16:19 +0800 (������, 07 ʮ���� 2012)");
        
        FileSvnInfo expectInfo = new FileSvnInfo();
        expectInfo.setPath("G:\\svntest");
        expectInfo.setUrl("https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg");
        expectInfo.setRepositoryRoot("https://10.67.9.84:8443/svn/NETNUMENN31_MAP");
        expectInfo.setRevision(1864);
        expectInfo.setNodeKind("directory");
        expectInfo.setLastChangedAuthor("lanjianyi");
        expectInfo.setLastChangedRev(1813);
        expectInfo.setLastChangedDate("2012-12-07 17:16:19");
        
        FileSvnInfo actualInfo = SvnInfoQuery.assembleFileSvnInfo(results);
        
        assertEquals(expectInfo, actualInfo);
    }
    
    //svn info E:\\SVN_4X\\ZXNM01V4\\zxnm01\\map\\src\\com\\zte\\ums\\an\\map\\conf\\alarmcfg
    public void testSvnInfoQuery_1dot7_Chi() throws Exception
    {
        EnvLanguage.setLanguage(EnvLanguage.LANG_CHI);
        
        ArrayList<String> results = new ArrayList<String>();
        results.add("·��: E:\\SVN_4X\\ZXNM01V4\\zxnm01\\map\\src\\com\\zte\\ums\\an\\map\\conf\\alarmcfg");
        results.add("����������Ŀ¼: E:\\SVN_4X\\ZXNM01V4\\zxnm01\\map");
        results.add("URL: https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg");
        results.add("�汾���: https://10.67.9.84:8443/svn/NETNUMENN31_MAP");
        results.add("�汾�� UUID: 3c449629-a34d-f645-82e2-9fe792e1c782");
        results.add("�汾: 1806");
        results.add("�ڵ�����: Ŀ¼");
        results.add("����: ����");
        results.add("����޸ĵ�����: chenduoduo");
        results.add("����޸ĵİ汾: 1727");
        results.add("����޸ĵ�ʱ��: 2012-11-12 09:30:00 +0800 (��һ, 2012-11-12)");
        
        FileSvnInfo expectInfo = new FileSvnInfo();
        expectInfo.setPath("E:\\SVN_4X\\ZXNM01V4\\zxnm01\\map\\src\\com\\zte\\ums\\an\\map\\conf\\alarmcfg");
        expectInfo.setUrl("https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg");
        expectInfo.setRepositoryRoot("https://10.67.9.84:8443/svn/NETNUMENN31_MAP");
        expectInfo.setRevision(1806);
        expectInfo.setNodeKind("Ŀ¼");
        expectInfo.setLastChangedAuthor("chenduoduo");
        expectInfo.setLastChangedRev(1727);
        expectInfo.setLastChangedDate("2012-11-12 09:30:00");
        
        FileSvnInfo actualInfo = SvnInfoQuery.assembleFileSvnInfo(results);
        
        assertEquals(expectInfo, actualInfo);
    }
}
