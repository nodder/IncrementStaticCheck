package cdd.zte.checktools.common.info;

public class FileSvnInfo
{
    private String path;
    private String name;
    private String url;
    private String repositoryRoot;
    private int Revision;
    private String nodeKind;
    private String lastChangedAuthor;
    private int lastChangedRev;
    private String lastChangedDate;
    
    public String getPath()
    {
        return path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getRepositoryRoot()
    {
        return repositoryRoot;
    }
    public void setRepositoryRoot(String repositoryRoot)
    {
        this.repositoryRoot = repositoryRoot;
    }
    public int getRevision()
    {
        return Revision;
    }
    public void setRevision(int revision)
    {
        Revision = revision;
    }
    public String getNodeKind()
    {
        return nodeKind;
    }
    public void setNodeKind(String nodeKind)
    {
        this.nodeKind = nodeKind;
    }
    public String getLastChangedAuthor()
    {
        return lastChangedAuthor;
    }
    public void setLastChangedAuthor(String lastChangedAuthor)
    {
        this.lastChangedAuthor = lastChangedAuthor;
    }
    public int getLastChangedRev()
    {
        return lastChangedRev;
    }
    public void setLastChangedRev(int lastChangedRev)
    {
        this.lastChangedRev = lastChangedRev;
    }
    public String getLastChangedDate()
    {
        return lastChangedDate;
    }
    public void setLastChangedDate(String lastChangedDate)
    {
        this.lastChangedDate = lastChangedDate;
    }
    
    @Override
    public String toString()
    {
        return "FileSvnInfo [\n" 
                    + "path               = " + path + "\n" 
                    + "name               = " + name +"\n" 
                    + "url                = " + url + "\n" 
                    + "repositoryRoot     = " + repositoryRoot + "\n" 
                    + "Revision           = " + Revision + "\n" 
                    + "nodeKind           = " + nodeKind + "\n" 
                    + "lastChangedAuthor  = " + lastChangedAuthor + "\n" 
                    + "lastChangedRev     = " + lastChangedRev + "\n" 
                    + "lastChangedDate    = " + lastChangedDate + "]";
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Revision;
        result = prime * result + ((lastChangedAuthor == null) ? 0 : lastChangedAuthor.hashCode());
        result = prime * result + ((lastChangedDate == null) ? 0 : lastChangedDate.hashCode());
        result = prime * result + lastChangedRev;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nodeKind == null) ? 0 : nodeKind.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + ((repositoryRoot == null) ? 0 : repositoryRoot.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        
        FileSvnInfo other = (FileSvnInfo)obj;
        
        return strEquals(other.getPath(), this.getPath())
            && strEquals(other.getName(), this.getName())
            && strEquals(other.getRepositoryRoot(), this.getRepositoryRoot())
            && intEquals(other.getRevision(), this.getRevision())
            && strEquals(other.getNodeKind(), this.getNodeKind())
            && strEquals(other.getLastChangedAuthor(), this.getLastChangedAuthor())
            && intEquals(other.getLastChangedRev(), this.getLastChangedRev())
            && strEquals(other.getLastChangedDate(), this.getLastChangedDate());
    }
    
    private static boolean strEquals(String s1, String s2)
    {
        if(s1 == null)
        {
            return s2 == null;
        }
        
        return s1.equals(s2);
    }
    
    private static boolean intEquals(int i1, int i2)
    {
        return i1 == i2;
    }
}
