package cdd.zte.checktools.committool.javacheck.compiledependence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;

public class JavaFileReader
{
    private static Logger logger = Logger.getLogger(JavaFileReader.class.getName());
    
    private JavaFileReader()
    {
    }
    
    protected static ArrayList<String> getImportedPackets(File javaFile)
    {
        BufferedReader buffReader = null;
        FileReader fileReader = null;

        ArrayList<String> importedPacksInfo = new ArrayList<String>();
        try
        {
            fileReader = new FileReader(javaFile);
            buffReader = new BufferedReader(fileReader);

            String line;
            int lineNum = 0;
            while((line = buffReader.readLine()) != null)
            {
                lineNum++;
                if(line.startsWith("package") || line.trim().equalsIgnoreCase(""))
                {
                    continue;
                }
                else if(line.trim().startsWith("import"))
                {
                    importedPacksInfo.add(line);
                }
                else if(line.contains("public") && line.contains("class"))
                {
                    break;
                }
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
            CommonUtil.closeFileReader(fileReader);
            CommonUtil.closeBufferedReader(buffReader);
        }
        
        return importedPacksInfo;
    }
    
    protected static String getClass(File javaFile)
    {
        BufferedReader buffReader = null;
        FileReader fileReader = null;

        try
        {
            fileReader = new FileReader(javaFile);
            buffReader = new BufferedReader(fileReader);

            String line;
            while((line = buffReader.readLine()) != null)
            {
                if(line.trim().startsWith("package"))
                {
                    return line.trim().substring(line.trim().indexOf(" ")).trim();
                }
                else
                {
                    continue;
                }
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
        finally
        {
            CommonUtil.closeFileReader(fileReader);
            CommonUtil.closeBufferedReader(buffReader);
        }
        
        return "";
    }
    
}
