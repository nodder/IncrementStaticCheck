package cdd.zte.checktools.common.cmds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;

public class FileSearchExecutor
{
    private static Logger logger = Logger.getLogger(FileSearchExecutor.class.getName());
    
    public static  ArrayList<File> query(String searchPath, String target) throws Exception
    {
        return runFileSearth(searchPath, target);
    }
    
    private static ArrayList<File> runFileSearth(String searchPath, String target) throws Exception
    {        
        CmdRunResult result = RunProcess.runBatFile(CmdGenerator.fileSearthCmd(searchPath, target), null);

        CommonUtil.throwExceptionIfError(result);
        
        return assembleFiles(result.outputResult);
    }

    private static ArrayList<File> assembleFiles(List<String> outputResult) throws Exception
    {
        LogPrint.logDebug(logger, outputResult.toString());
        
        ArrayList<File> listFiles = new ArrayList<File>();
        for(int i = 0; i < outputResult.size(); i++)
        {
            listFiles.add(new File(outputResult.get(i)));
        }

        return listFiles;
    }
}
