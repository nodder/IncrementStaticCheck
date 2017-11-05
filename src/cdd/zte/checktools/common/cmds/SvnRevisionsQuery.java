package cdd.zte.checktools.common.cmds;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;

public class SvnRevisionsQuery
{
    public static LinkedList<Integer> query(String filePath, int logEntryNumber) throws Exception
    {
        if(logEntryNumber < 0 )
        {
            throw new IllegalArgumentException("Invalid number : logEntryNumber==" + logEntryNumber);
        }
        
        return queryRevisions(filePath, logEntryNumber);
    }
    
    public static boolean isRevisionExist(String filePath, int revision) throws IOException, Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnLogSpecRevisionByLenthCmd(filePath, revision));
        
        if(!result.isSuccess())
        {
            return false;
        }
        
        return isRevisionExist(result.outputResult);
    }

    static boolean isRevisionExist(List<String> outputResult)
    {
        if(containsRevisionInfo(outputResult))
        {
            return true;
        }
        
        return false;
    }

    private static boolean containsRevisionInfo(List<String> outputResult)
    {
        return outputResult.toString().indexOf('r') > 0;
    }
    

    private static LinkedList<Integer> queryRevisions(String filePath, int logEntryNumber)
            throws IOException, Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnLogByLenthCmd(filePath, logEntryNumber));
        CommonUtil.throwExceptionIfError(result);
        
        return assembleRevisions(result.outputResult);
    }

    static LinkedList<Integer> assembleRevisions(List<String> resultStr)
    {      
        LinkedList<Integer> listRevisions = new LinkedList<Integer>();         
        for(int i = 0; i < resultStr.size(); i++)
        {            
            String line = resultStr.get(i);

            if(line.startsWith("r"))
            {
                listRevisions.add(Integer.parseInt(line.substring(1, line.indexOf(" "))));
                
            }
        }
        
        return listRevisions;
    }
   
    private SvnRevisionsQuery()
    {
    }
}
