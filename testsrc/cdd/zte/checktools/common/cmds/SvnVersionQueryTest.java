package cdd.zte.checktools.common.cmds;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

public class SvnVersionQueryTest extends TestCase
{
    public void testSvnVersionLanguage() throws IOException
    {
        ArrayList<String> results = new ArrayList<String>();
        results.add("svn, version 1.7.6 (r1370777)");
        results.add("   compiled Aug 10 2012, 14:48:30");
        results.add("");

        
        assertTrue(SvnVersionQuery.isSvnEnglishVersion(results));
        
        results = new ArrayList<String>();
        results.add("svn，版本 1.7.6 (r1370777)");
        results.add("   编译于 Aug 10 2012，14:48:30");
        results.add("");
        
        assertFalse(SvnVersionQuery.isSvnEnglishVersion(results));
        
        results = new ArrayList<String>();
        results.add("svn，版本 1.7.6-SlikSvn-1.7.6-X64 (SlikSvn/1.7.6) X64");
        results.add("   编译于 Aug 20 2012，22:18:27");
        results.add("");

        
        assertFalse(SvnVersionQuery.isSvnEnglishVersion(results));
    }
    
    public void testIsVersionLessThan() throws IOException
    {
        assertFalse(SvnVersionQuery.isVersionLessThan("1.7.6", 1.5));
        assertTrue(SvnVersionQuery.isVersionLessThan("1.4.0", 1.5));
        assertFalse(SvnVersionQuery.isVersionLessThan("1.7.6-SlikSvn-1.7.6-X64", 1.5));
    }
}
