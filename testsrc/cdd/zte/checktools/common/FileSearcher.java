package cdd.zte.checktools.common;

import java.io.File;
import java.util.ArrayList;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class FileSearcher
{
    private ArrayList<File> searchedFiles = new ArrayList<File>();
    
    public File[] search(File dir, String match)
    {
        doSearchFile(dir, match);
        
        return searchedFiles.toArray(new File[0]);
    }
    
    private void doSearchFile(File dir, String match)
    {
        if(dir.isDirectory())
        {
            File alFiles[] = dir.listFiles();

            for(File tmpFile : alFiles)
            {
                if(tmpFile.isDirectory())
                {
                    doSearchFile(tmpFile, match);
                }
                else if(tmpFile.isFile() && tmpFile.getName().contains(match))
                {
                    searchedFiles.add(tmpFile);
                }
            }
        }
    }
}
