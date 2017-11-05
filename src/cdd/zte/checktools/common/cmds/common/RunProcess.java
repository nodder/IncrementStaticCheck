package cdd.zte.checktools.common.cmds.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;

public class RunProcess
{    
    private static Logger logger = Logger.getLogger(RunProcess.class.getName());
    
    public static CmdRunResult runBatFile(String[] as, String directory) throws IOException, InterruptedException
    {
        ProcessBuilder pb = new ProcessBuilder(as);
        
        if(directory != null)
        {
            pb.directory(new File(directory));
        }

        Process p = pb.start();
        
        return getProcessResult(p);
    }
    
    public static CmdRunResult runDosCommand(String[] as) throws IOException, InterruptedException
    {
        String cmd = format(as);
        Process p = Runtime.getRuntime().exec(cmd);

        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
        errorGobbler.start();
        StreamGobbler outGobbler = new StreamGobbler(p.getInputStream());
        outGobbler.start();
        
        p.waitFor();
        errorGobbler.join();  
        outGobbler.join();  

        return new CmdRunResult(outGobbler.getResult(), errorGobbler.getResult());
    }
    
    private static String format(String[] as)
    {
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < as.length - 1; i++)
        {
            buf.append(as[i]).append(" ");
        }
        
        return buf.append(as[as.length - 1]).toString();
    }

    private static CmdRunResult getProcessResult(Process proc)
    {
        CmdRunResult result = new CmdRunResult();
        result.outputResult = getResultFromStream(proc.getInputStream());
        result.errorResult = getResultFromStream(proc.getErrorStream());
        
        return result;
    }

    private static List<String> getResultFromStream(InputStream inputStream)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        
        List<String> result = new ArrayList<String>();
        try
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                result.add(line);
            }
        }
        catch (Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
            CommonUtil.closeInputStream(inputStream);            
            CommonUtil.closeBufferedReader(bufferedReader);
        }
        
        return result;
    } 
    
    private RunProcess()
    {
    }
}
