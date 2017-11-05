package cdd.zte.checktools.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigFileReader
{
    private static Logger logger = Logger.getLogger(ConfigFileReader.class.getName());
    
    private static ConfigFileReader instance = new ConfigFileReader();
    
    private static Properties prop = null;
    
    protected ConfigFileReader()
    {
    }

    public static ConfigFileReader getInstance()
    {
        if(prop == null)
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
    
    public String getTempPath()
    {
        return (new File(prop.getProperty("tempPath"))).getAbsolutePath();
    }
    
    public String getCheckstylePath()
    {
        return (new File(prop.getProperty("checkstyle"))).getAbsolutePath();
    }
    
    public String getDebugResponsitory()
    {
        return prop.getProperty("DEBUG_RESPONSITORY");
    }
    
    public String getCheckJava()
    {
        return prop.getProperty("checkJava");
    }
    
    public String getCheckXml()
    {
        return prop.getProperty("checkXml");
    }
    
    public String getCheckCsv()
    {
        return prop.getProperty("checkCsv");
    }
    
    public String getCheckSql()
    {
        return prop.getProperty("checkSql");
    }

    private static void readFile() throws Exception
    {     
        String MainPath = (new File(System.getProperty("user.dir"))).getAbsolutePath();
        String filePath = MainPath + "/conf/config.properties";
        
        File confFile = new File(filePath);
        
        prop = new Properties();
        prop.load(new FileInputStream(confFile));           
    }
}
