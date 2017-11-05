package cdd.zte.checktools.common.fileParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cdd.zte.checktools.common.CommonUtil;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class FileZipper
{
    private byte[] buf = new byte[1024];
    private File baseDir;
    
    /**
     * srcFile�������ļ���Ҳ�������ļ���
     * ������ȱ�ݣ��޷�ѹ�����ļ��У���
     *
     * @param baseDir ��Ҫѹ�����ļ���
     * @param objFileName ѹ������ļ���
     * @return ѹ�����ļ��Ĵ�С
     * @throws Exception
     */
    public void doZip(File targetZipFile, File srcFile) throws Exception
    {
        recordBaseDir(srcFile);
        
        ZipOutputStream zos = null;
        
        try
        {
            zos = new ZipOutputStream(new FileOutputStream(targetZipFile));
            doZipSingleFile(targetZipFile, zos, srcFile);
        }
        finally
        {
            CommonUtil.closeOutputStream(zos);
        }
    }

    private void recordBaseDir(File srcFile)
    {
        if(srcFile.isFile())
        {
            this.baseDir = srcFile.getParentFile();
        }
        else
        {
            this.baseDir = srcFile;
        }
    }

    private void doZipSingleFile(File targetZipFile, ZipOutputStream zos, File srcFile) throws IOException, FileNotFoundException
    {
        if(srcFile.isDirectory())
        {
            File[] subFiles = srcFile.listFiles();
            for(File subFile : subFiles)
            {
                doZipSingleFile(targetZipFile, zos, subFile);
            }
        }
        else
        {
            zos.putNextEntry(getZipEntry(srcFile, targetZipFile));

            if(srcFile.isFile())
            {
                doZipForFile(zos, srcFile, buf);
            }
        }
    }

    private void doZipForFile(ZipOutputStream zos, File srcFile, byte[] buf) throws FileNotFoundException, IOException
    {
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile));

            int readLen = -1;
            while((readLen = is.read(buf, 0, 1024)) != -1)
            {
                zos.write(buf, 0, readLen);
            }

            is.close();
        }
        finally
        {
            CommonUtil.closeInputStream(is);
        }
    }

    private ZipEntry getZipEntry(File srcFile, File targetZipFile)
    {
        ZipEntry ze = new ZipEntry(CommonUtil.getRelativeFileName(baseDir, srcFile));
        ze.setSize(srcFile.length());
        ze.setTime(srcFile.lastModified());
        return ze;
    }
    
}
