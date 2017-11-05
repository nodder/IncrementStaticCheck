package cdd.zte.checktools.common.info;

public class ChangedPathInfo
{
    public static final String CHANGE_TYPE_DELTE = "D";
    public static final String CHANGE_TYPE_MODIFY = "M";
    public static final String CHANGE_TYPE_ADD = "A";
    public static final String CHANGE_TYPE_REPLACING = "R";
    public static final String CHANGE_TYPE_UNCONTROL = "?";
   
    private String changedType;
    private String path;
    
    //20120801ÐÂÔö
    private String copyFromPath;
    private String copyFromRevision;
    
    public String getChangedType()
    {
        return changedType;
    }
    public void setChangedType(String changedType)
    {
        this.changedType = changedType;
    }
    public String getPath()
    {
        return path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }
    
    public String getCopyFromPath()
    {
        return copyFromPath;
    }
    
    public void setCopyFromPath(String copyFromPath)
    {
        this.copyFromPath = copyFromPath;
    }
    
    public String getCopyFromRevision()
    {
        return copyFromRevision;
    }
    
    public void setCopyFromRevision(String copyFromRevision)
    {
        this.copyFromRevision = copyFromRevision;
    }
    
    public String toString()
    {
        return "changedType = " + changedType + "; path = " + path
                     + "; copyFromPath = " + copyFromPath + "; copyFromRevision = " + copyFromRevision;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((changedType == null) ? 0 : changedType.hashCode());
        result = prime * result + ((copyFromPath == null) ? 0 : copyFromPath.hashCode());
        result = prime * result + ((copyFromRevision == null) ? 0 : copyFromRevision.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        
        if(this == obj)
        {
            return true;
        }
        
        if(getClass() != obj.getClass())
        {
            return false;
        }
        
        ChangedPathInfo other = (ChangedPathInfo)obj;
        
        return strEquals(other.getChangedType(), this.getChangedType())
            && strEquals(other.getCopyFromPath(), this.getCopyFromPath())
            && strEquals(other.getCopyFromRevision(), this.getCopyFromRevision())
            && strEquals(other.getPath(), this.getPath());
    }
    
    private static boolean strEquals(String s1, String s2)
    {
        if(s1 == null)
        {
            return s2 == null;
        }
        
        return s1.equals(s2);
    }
}