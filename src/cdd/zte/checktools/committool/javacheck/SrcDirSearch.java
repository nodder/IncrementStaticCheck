package cdd.zte.checktools.committool.javacheck;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SrcDirSearch
{
    private SrcDirSearch()
    {
    }
    
    public static ArrayList<File> search(String path)
    {
        if(isSrcDir(path))
        {
            ArrayList<File> srcDirList = new ArrayList<File>();
            srcDirList.add(new File(path));
            
            return srcDirList;
        }
        
        return new ArrayList<File>();
//        return searchSrcDir(path);
    }
    
    /**
     * 通过字符串中有没有包含“src”，来判断是否给定代码是否为源代码路径。
     * @param path
     * @return
     */
    private static boolean isSrcDir(String path)
    {
        String formatedPath = path.replace("\\", "/");
        
        StringTokenizer st = new StringTokenizer(formatedPath, "/");
        
        while(st.hasMoreTokens())
        {
            String temp = st.nextToken();
            if("src".equals(temp) || "testsrc".equals(temp))
            {
                return true;
            }
        }
        
        return false;
    }
}
