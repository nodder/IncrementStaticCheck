package cdd.zte.checktools.committool.common;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.cmds.FileSearchExecutor;

public class FileSearch
{
    private static Logger logger = Logger.getLogger(FileSearch.class.getName());
    
    private ArrayList<File> xmlFiles = new ArrayList<File>();
    private ArrayList<File> csvFiles = new ArrayList<File>();
    private ArrayList<File> javaFiles = new ArrayList<File>();
    private ArrayList<File> sqlFiles = new ArrayList<File>();
    
    public FileSearch(String searchPath)
    {
        try
        {
            this.xmlFiles = FileSearchExecutor.query(searchPath, "*.xml");
            this.csvFiles = FileSearchExecutor.query(searchPath, "*.csv");
            this.javaFiles = FileSearchExecutor.query(searchPath, "*.java"); 
            this.sqlFiles = FileSearchExecutor.query(searchPath, "*.sql"); 
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
    }
    
    public ArrayList<File> getXmlFiles()
    {
        return xmlFiles;
    }

    public ArrayList<File> getCsvFiles()
    {
        return csvFiles;
    }

    public ArrayList<File> getJavaFiles()
    {
        return javaFiles;
    }
    
    public ArrayList<File> getSqlFiles()
    {
        return sqlFiles;
    }
}
