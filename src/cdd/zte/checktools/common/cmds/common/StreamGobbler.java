package cdd.zte.checktools.common.cmds.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;

public class StreamGobbler extends Thread
{
    private static Logger logger = Logger.getLogger(StreamGobbler.class.getName());
    
    private InputStream is;
    private List<String> result;

    public StreamGobbler(InputStream is)
    {
        this.is = is;
    }

    public void run()
    {
        result = getResultFromStream(is);
    }
    
    public List<String> getResult()
    {
        return result;
    }
    
    private List<String> getResultFromStream(InputStream inputStream)
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
}
