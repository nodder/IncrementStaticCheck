package cdd.zte.checktools.common.cmds.common;

import junit.framework.TestCase;

public class RunProcessTest extends TestCase
{
    public void testrunDosCommand() throws Exception
    {
        String cmd = "svn log -vr 1700:1700 https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map/src/com/zte/ums/an/map/conf/alarmcfg";
        
        CmdRunResult result = RunProcess.runDosCommand(format(cmd));
        assertEquals(13, result.outputResult.size());
        
        cmd = "svn --version --quiet";
        result = RunProcess.runDosCommand(format(cmd));
        assertEquals("1.7.6", result.outputResult.get(0));
        
        cmd = "svn log -vr 1700:1700 xxxx";
        result = RunProcess.runDosCommand(format(cmd));
        assertTrue(result.errorResult.get(0).startsWith("svn: E155007"));
        
        //耗时较长，先注释掉。
//        cmd = "ping 127.0.0.1";
//        result = RunProcess.runDosCommand(format(cmd));
//        assertEquals(11, result.outputResult.size());
    }
    
    private String[] format(String cmd)
    {
        return cmd.split(" ");
    }
}
