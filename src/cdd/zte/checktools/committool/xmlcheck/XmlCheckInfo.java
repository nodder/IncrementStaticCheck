package cdd.zte.checktools.committool.xmlcheck;

import org.xml.sax.SAXParseException;

public class XmlCheckInfo
{
    private String file;
    private int errorLine;
    private int errorColumn;
    private String detail;
    
    public XmlCheckInfo()
    {
    }
    
    public XmlCheckInfo(String file, int errorLine, int errorColumn, String detail)
    {
        this.file = file;
        this.errorLine = errorLine;
        this.errorColumn = errorColumn;
        this.detail = detail;
    }
    
    public XmlCheckInfo(SAXParseException e)
    {
        this.file =  e.getSystemId();
        this.errorLine =  e.getLineNumber();
        this.errorColumn = e.getColumnNumber();
        this.detail = e.getMessage();
    }
    
    public String getFile()
    {
        return file;
    }
    public void setFile(String file)
    {
        this.file = file;
    }
    public int getErrorLine()
    {
        return errorLine;
    }
    public void setErrorLine(int errorLine)
    {
        this.errorLine = errorLine;
    }
    public int getErrorColumn()
    {
        return errorColumn;
    }
    public void setErrorColumn(int errorColumn)
    {
        this.errorColumn = errorColumn;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
    public String toString()
    {
        return this.file + "\nline:" + this.errorLine + "  column:" + this.errorColumn
                + "\nDetail:" + this.detail;
    }
    
}
