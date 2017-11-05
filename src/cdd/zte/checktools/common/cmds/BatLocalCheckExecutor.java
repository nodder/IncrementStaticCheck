package cdd.zte.checktools.common.cmds;

import java.io.File;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;

public class BatLocalCheckExecutor
{    
    private static String batDir;
    
    private static String batFileFor30Plus8;
    private static String batFileForStaticCheck;
    
    static
    {
        batDir = CommonConst.configFileReader.getCheckstylePath();  
       
        createBatFileFor30Plus8WithNoPause();
        createBatFileForStaticCheckWithNoPause();
    }

    private static void createBatFileFor30Plus8WithNoPause()
    {
//        String oriLocalCheckFile =  (new File(batDir + "/" + CommonConst.LOCALCHECK_BAT_FILENAME)).getAbsolutePath(); 
        batFileFor30Plus8 = (new File(batDir + "/" + CommonConst.BAT_30PLUS8)).getAbsolutePath();
        
//        createNoPauseFile(oriLocalCheckFile, batFileFor30Plus8);
    }

//    private static void createNoPauseFile(String oriLocalCheckFile, String targetLoackCheckFile)
//    {
//        CommonUtil.copyFiles(new File(oriLocalCheckFile), new File(targetLoackCheckFile));    
//        FileSimpleModication fileModify = new FileSimpleModication(targetLoackCheckFile, "pause", "");
//        fileModify.save();
//    }
    
    private static void createBatFileForStaticCheckWithNoPause()
    {
//        String oriLocalCheckFile =  (new File(batDir + "/" + CommonConst.STATIC_LOCALCHECK_BAT_FILENAME)).getAbsolutePath(); 
        batFileForStaticCheck = (new File(batDir + "/" + CommonConst.BAT_STATIC)).getAbsolutePath();
        
//        createNoPauseFile(oriLocalCheckFile, batFileForStaticCheck);
    }

    public static void execute30Plus8Check(String dirToCheck) throws Exception
    {
        runLocalCheck(dirToCheck, batFileFor30Plus8);
    }
    
    public static void executeStaticCheck(String dirToCheck) throws Exception
    {
        runLocalCheck(dirToCheck, batFileForStaticCheck);
    }
    
    private static void runLocalCheck(String dirToCheck, String batFile) throws Exception
    {
        String[] args = new String[] {dirToCheck, CommonConst.LOCALCHECK_CC_PRJ_NAME};
        
        CmdRunResult result = RunProcess.runBatFile(CmdGenerator.callBat(batFile, args), batDir);
        CommonUtil.throwExceptionIfError(result);
    }
}
