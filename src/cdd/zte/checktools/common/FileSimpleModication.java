package cdd.zte.checktools.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;



public class FileSimpleModication
{
    private static Logger logger = Logger.getLogger(FileSimpleModication.class.getName());
    
    private File file = null;
    
    private String oldStr;
    private String newStr;
    
    public FileSimpleModication(String fileToOper, String oldStr, String newStr)
    {
        this.file = new File(fileToOper);
        this.oldStr = oldStr;
        this.newStr = newStr;
    }
        
    public boolean save()
    {
        String bakFile = this.file.getAbsolutePath() + "_bak";
        
        CommonUtil.copyFiles(new File(this.file.getAbsolutePath()), new File(bakFile));
        
        FileWriter out = null;
        BufferedReader bufferReader = null;
        
        try
        {
            out = new FileWriter(this.file);
            bufferReader = new BufferedReader(new FileReader(bakFile));

            String line;
            while ((line = bufferReader.readLine()) != null)
            {
//                out.write(line.replace(oldStr, newStr) + CommonUtil.getLineSeperator());
            	out.write(line.replace(oldStr, newStr) + "\n");
            }
            
            return true;
        }
        catch (Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
            CommonUtil.closeFileWriter(out);           
            CommonUtil.closeBufferedReader(bufferReader);
        }

        return false;
    }
}
