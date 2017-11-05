package cdd.zte.checktools.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

public class ResultComparator
{    
    private static Logger logger = Logger.getLogger(ResultComparator.class.getName());
    
    public static int compare(File resultFile1, File resultFile2) throws IOException
    {
        int errorNum1 = getErrorNum(resultFile1);
        int errorNum2 = getErrorNum(resultFile2);
        
        logger.debug("ResultComparetor: \n" 
                + resultFile1 + "(num " + errorNum1 + ") \n to \n" 
                + resultFile2 + "(num " + errorNum2 + ")");
        
        return errorNum2 - errorNum1;
    }

    private static int getErrorNum(File resultFile) throws IOException
    {
        if(resultFile == null || !resultFile.exists())
        {
            return 0;
        }
        
        int errorNum = 0;
        BufferedReader reader = null;
        
        try
        {
            reader = new BufferedReader(new FileReader(resultFile));
            String line;
            while((line = reader.readLine()) != null)
            {
                if(line.startsWith("<error"))
                {
                    errorNum++;
                }
            }
        }
        finally
        {
            reader.close();
        }
        
        return errorNum;
    }
}
