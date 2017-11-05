package cdd.zte.checktools.common.info;

public class SvnStatusInfo
{       
    private String path = "";
    private String changedType = "";
    private String extension = "";
    private boolean isSelected = true;
    
    public boolean isSelected()
    {
        return isSelected;
    }
    public void setSelected(boolean isSelected)
    {
        this.isSelected = isSelected;
    }
    public String getPath()
    {
        return path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }
    public String getChangedType()
    {
        return changedType;
    }
    public void setChangedType(String changedType)
    {
        this.changedType = changedType;
    }
    public String getExtension()
    {
        return extension;
    }
    public void setExtension(String extension)
    {
        this.extension = extension;
    }
    
    public String toString()
    {
        String str = "SvnStatusInfo [\n" 
            + "path            = " + path + "\n" 
            + "changedType     = " + changedType +"\n" 
            + "extension       = " + extension + "]\n";
        
        return str;
    }
}
