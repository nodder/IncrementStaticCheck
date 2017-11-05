package cdd.zte.checktools.common.fileParser;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cdd.zte.checktools.common.CommonUtil;

/**
 * @description 本程序没有考虑大文件占用内存的问题
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class CsvParser
{
    private File toFile;
    
    private ArrayList<String> lines = new ArrayList<String>();
    
    private static final String SEPERATOR = ",";

    public CsvParser(File file)
    {
        this.toFile = file;
    }
    
    public void appendHeader(String... lineFields)
    {
        lines.add(0, toLine(lineFields));
    }
    
    public void appendLine(String... lineFields)
    {
        lines.add(toLine(lineFields));
    }

    private String toLine(String[] lineFields)
    {
        if (lineFields.length == 0)
        {
            return "";
        }
        
        StringBuffer lineBuf = new StringBuffer();
        for(String field : lineFields)
        {
            lineBuf.append(field).append(SEPERATOR);
        }
        
        return lineBuf.delete(lineBuf.length() - 1, lineBuf.length()).append("\n").toString();
    }

    public void saveAndFinish() throws IOException
    {
        BufferedOutputStream buff = null;
        FileOutputStream outSTr = null;

        CommonUtil.createdDirIfNoExist(toFile.getParent());
        try
        {
            outSTr = new FileOutputStream(toFile, false);
            buff = new BufferedOutputStream(outSTr);
            
            for(String line : lines)
            {
                buff.write((line).getBytes());
            }
        }
        finally
        {
            CommonUtil.closeOutputStream(buff);
            CommonUtil.closeOutputStream(outSTr);
        }
    }
}