package cdd.zte.checktools.common;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ConfigFileReaderStub extends ConfigFileReader
{
    public ConfigFileReaderStub()
    {
    }
    
    public String getTempPath()
    {
        return "./temp";
    }
    
    public String getCheckstylePath()
    {
        return "E:/SVN_4X/ZXNM01V4/zxnm01/antools/checkstyle/";
    }
    
    public String getDebugResponsitory()
    {
        return "https://10.67.9.84:8443/svn/ZXNM01V4_DOC";
    }
}
