package cdd.zte.checktools.common.cmds;

import java.util.List;

import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;

public class SvnVersionQuery
{
    public static boolean isSvnEnglishVersion() throws Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnVersion());
        return isSvnEnglishVersion(result.outputResult);
    }
    
    public static String getSvnVersionNo() throws Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnVersionQuiet());
        return result.outputResult.get(0);
    }
    
    public static boolean isVersionLessThan(String strVersion, double version) throws NumberFormatException
    {
        if(strVersion.indexOf("-") > 0)
        {
            strVersion = strVersion.substring(0, strVersion.indexOf("-"));
        }
        
        double baseVersionNo = Double.parseDouble(strVersion.substring(0, strVersion.lastIndexOf(".")));//1.7.6 --> 1.7
        
        return baseVersionNo < version;
    }
    
    static boolean isSvnEnglishVersion(List<String> outputResult)
    {
        String versionLine = outputResult.get(0);
        return isEnglishVersion(versionLine);
    }

    private static boolean isEnglishVersion(String versionLine)
    {
        return !versionLine.contains("°æ±¾");
    }   
}
