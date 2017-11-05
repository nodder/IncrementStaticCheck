package cdd.zte.checktools.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ClassExclusionFileReader
{
    private static Logger logger = Logger.getLogger(ConfigFileReader.class.getName());
    
    private static ClassExclusionFileReader instance = new ClassExclusionFileReader();
    
    private static String[] excludedClassPatterns = null;
    
    protected ClassExclusionFileReader()
    {
    }

    public static ClassExclusionFileReader getInstance()
    {
        if(excludedClassPatterns == null)
        {
            try
            {
                readFile();
            }
            catch (Exception ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
        
        return instance;
    }
    
    public String[] getExcludedClassPattern()
    {
        return excludedClassPatterns;
    }
    
    private static void readFile() throws Exception
    {
        BufferedReader br = null;
        
        try
        {
            List<String> pattern = new ArrayList<String>();
            
            br = new BufferedReader(new FileReader(new File(new File(System.getProperty("user.dir")).getAbsoluteFile(), "conf/classExclusion.cfg")));
            
            String line;
            while((line = br.readLine()) != null)
            {
                line = line.trim();
                if(line.startsWith("#") || line.isEmpty())
                {
                    continue;
                }
                
                pattern.add(line);
            }
            
            excludedClassPatterns = pattern.toArray(new String[0]);
        }
        finally
        {
            if(br != null)
            {
                br.close();
            }
        }
    }
}
