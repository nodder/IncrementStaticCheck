package cdd.zte.checktools.common.info;

import java.util.ArrayList;
import java.util.List;


public class FileSvnLogInfo
{
    private int revision;
    private String Author;
    private String modifyTime;
    private String noteStr;
    private ArrayList<ChangedPathInfo> changedPaths;
    
    public int getRevision()
    {
        return revision;
    }
    public void setRevision(int revision)
    {
        this.revision = revision;
    }
    public String getAuthor()
    {
        return Author;
    }
    public void setAuthor(String author)
    {
        Author = author;
    }

    public String getModifyTime()
    {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime)
    {
        this.modifyTime = modifyTime;
    }
    public ArrayList<ChangedPathInfo> getChangedPaths()
    {
        return changedPaths;
    }
    public void setChangedPaths(ArrayList<ChangedPathInfo> changedPaths)
    {
        this.changedPaths = changedPaths;
    }
    
    public String getNoteStr()
    {
        return noteStr;
    }
    
    public void setNoteStr(String noteStr)
    {
        this.noteStr = noteStr;
    }
    
    public String toString()
    {
        String str = "FileSvnLogInfo [\n" 
            + "revision      = " + revision + "\n" 
            + "Author        = " + Author +"\n" 
            + "modifyTime    = " + modifyTime + "\n" 
            + "noteStr       = " + noteStr + "\n"
            + "changedPaths  = ";
        
        if(changedPaths == null)
        {
            return str + "null]";
        }
      
        return str + changedPaths.toString() + "]";
    }
    
    public String toOperLogString()
    {
        return "版本号   = " + revision + "\n" 
             + "作者   = " + Author +"\n" 
             + "修改时间   = " + modifyTime + "]"; 
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Author == null) ? 0 : Author.hashCode());
        result = prime * result + ((changedPaths == null) ? 0 : changedPaths.hashCode());
        result = prime * result + ((modifyTime == null) ? 0 : modifyTime.hashCode());
        result = prime * result + ((noteStr == null) ? 0 : noteStr.hashCode());
        result = prime * result + revision;
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
        
        FileSvnLogInfo other = (FileSvnLogInfo)obj;
        
        return strEquals(other.getAuthor(), this.getAuthor())
            && strEquals(other.getModifyTime(), this.getModifyTime())
            && strEquals(other.getNoteStr(), this.getNoteStr())
            && intEquals(other.getRevision(), this.getRevision())
            && strEquals(other.getModifyTime(), this.getModifyTime())
            && listEquals(other.getChangedPaths(), this.getChangedPaths());
    }
    
    private static boolean intEquals(int i1, int i2)
    {
        return i1 == i2;
    }
    
    private static boolean strEquals(String s1, String s2)
    {
        if(s1 == null)
        {
            return s2 == null;
        }
        
        return s1.equals(s2);
    }
    
    private static boolean listEquals(List<ChangedPathInfo> list1, List<ChangedPathInfo> list2)
    {
        if(list1 == null)
        {
            return list2 == null;
        }
        
        return list1.equals(list2);
    }
    
    public static void main(String[] args)
    {
        junit.framework.Assert.assertTrue(strEquals(null, null));
        junit.framework.Assert.assertTrue(strEquals("test", "test"));
        
        junit.framework.Assert.assertFalse(strEquals("test", null));
        junit.framework.Assert.assertFalse(strEquals(null, "test"));
        junit.framework.Assert.assertFalse(strEquals("test111", "test222"));
        
        System.out.println("OK");
    }
}


