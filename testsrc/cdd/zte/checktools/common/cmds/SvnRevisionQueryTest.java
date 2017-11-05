package cdd.zte.checktools.common.cmds;

import java.util.ArrayList;
import java.util.LinkedList;

import junit.framework.TestCase;

public class SvnRevisionQueryTest extends TestCase
{
    //svn log -qr 1700 https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg
    public void testRevisionExist_1dot6En_1dot7Chi()
    {
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r1700 | menghui | 2012-11-05 11:25:15 +0800 (星期一, 05 十一月 2012)");
        results.add("------------------------------------------------------------------------");
        
        assertTrue(SvnRevisionsQuery.isRevisionExist(results));
    }
    
    //svn log -qr 1701 https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg
    public void testRevisionNotExist_1dot6En_1dot7Chi()
    {
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        
        assertFalse(SvnRevisionsQuery.isRevisionExist(results));
    }
    
    //svn log -ql 1 g:\svntest
    public void testQueryRevisons_Length_1_1dot6En_1dot7Chi()
    {
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r1813 | lanjianyi | 2012-12-07 17:16:19 +0800 (星期五, 07 十二月 2012)");
        results.add("------------------------------------------------------------------------");
        
        LinkedList<Integer> expectedRevisions = new LinkedList<Integer>();
        expectedRevisions.add(1813);
        
        assertEquals(expectedRevisions, SvnRevisionsQuery.assembleRevisions(results));
    }
    
    //svn log -ql 3 g:\svntest
    public void testQueryRevisons_Length_3_1dot6En_1dot7Chi()
    {
        ArrayList<String> results = new ArrayList<String>();
        results.add("------------------------------------------------------------------------");
        results.add("r1813 | lanjianyi | 2012-12-07 17:16:19 +0800 (星期五, 07 十二月 2012)");
        results.add("------------------------------------------------------------------------");
        results.add("r1727 | chenduoduo | 2012-11-12 09:30:00 +0800 (星期一, 12 十一月 2012)");
        results.add("------------------------------------------------------------------------");
        results.add("r1713 | jingxueshi | 2012-11-07 16:20:37 +0800 (星期三, 07 十一月 2012)");
        results.add("------------------------------------------------------------------------");
        
        LinkedList<Integer> expectedRevisions = new LinkedList<Integer>();
        expectedRevisions.add(1813);
        expectedRevisions.add(1727);
        expectedRevisions.add(1713);
        
        assertEquals(expectedRevisions, SvnRevisionsQuery.assembleRevisions(results));
    }
}
