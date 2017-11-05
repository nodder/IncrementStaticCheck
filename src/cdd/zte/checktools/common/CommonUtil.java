package cdd.zte.checktools.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.info.ChangedPathInfo;

public class CommonUtil
{
    private static Logger logger = Logger.getLogger(CommonUtil.class.getName());
    public static String getVersion;
    
    public static String getUrl(int module)
    {
        switch(module)
        {
            case CommonConst.MODULE_COMMON:
                return CommonConst.URL_COMMON;
            case CommonConst.MODULE_COMMONSH:
                return CommonConst.URL_COMMONSH;
            case CommonConst.MODULE_UNI:
                return CommonConst.URL_UNI;
            case CommonConst.MODULE_MAP:
                return CommonConst.URL_MAP;
            case CommonConst.MODULE_DSL:
                return CommonConst.URL_DSL;
            case CommonConst.MODULE_IS:
                return CommonConst.URL_IS;
            case CommonConst.MODULE_AG:
                return CommonConst.URL_AG;
            case CommonConst.MODULE_A10:
                return CommonConst.URL_A10;
            case CommonConst.MODULE_EODN:
                return CommonConst.URL_EODN;
            case CommonConst.MODULE_EASYOPTICAL:
                return CommonConst.URL_EASYOPTICAL;
            case CommonConst.MODULE_DEBUG:
                return CommonConst.URL_DEBUG;
            default:
                LogPrint.logError(logger, "CommonUtil.getResponsitoryPath, Unknown module:" + module);
                return null;
        }
    }
    
    public static String getResponsitoryPath(int module)
    {
        switch(module)
        {
            case CommonConst.MODULE_COMMON:
                return CommonConst.RESPONSITORY_COMMON;
            case CommonConst.MODULE_COMMONSH:
                return CommonConst.RESPONSITORY_COMMONSH;
            case CommonConst.MODULE_UNI:
                return CommonConst.RESPONSITORY_UNI;
            case CommonConst.MODULE_MAP:
                return CommonConst.RESPONSITORY_MAP;
            case CommonConst.MODULE_DSL:
                return CommonConst.RESPONSITORY_DSL;
            case CommonConst.MODULE_IS:
                return CommonConst.RESPONSITORY_IS;
            case CommonConst.MODULE_AG:
                return CommonConst.RESPONSITORY_AG;
            case CommonConst.MODULE_A10:
                return CommonConst.RESPONSITORY_A10;
            case CommonConst.MODULE_EODN:
                return CommonConst.RESPONSITORY_EODN;
            case CommonConst.MODULE_EASYOPTICAL:
                return CommonConst.RESPONSITORY_EASYOPTICAL;
            case CommonConst.MODULE_DEBUG:
                return CommonConst.RESPONSITORY_DEBUG;
            default:
                LogPrint.logError(logger, "CommonUtil.getResponsitoryPath, Unknown module:" + module);
                return null;
        }
    }
    
    public static String getModuleName(int module)
    {
        switch(module)
        {
            case CommonConst.MODULE_COMMON:
                return CommonConst.STR_MODULE_COMMON;
            case CommonConst.MODULE_COMMONSH:
                return CommonConst.STR_MODULE_COMMONSH_CI;
            case CommonConst.MODULE_UNI:
                return CommonConst.STR_MODULE_UNI;
            case CommonConst.MODULE_MAP:
                return CommonConst.STR_MODULE_MAP;
            case CommonConst.MODULE_DSL:
                return CommonConst.STR_MODULE_DSL;
            case CommonConst.MODULE_IS:
                return CommonConst.STR_MODULE_IS;
            case CommonConst.MODULE_AG:
                return CommonConst.STR_MODULE_AG;
            case CommonConst.MODULE_A10:
                return CommonConst.STR_MODULE_A10;
            case CommonConst.MODULE_EODN:
                return CommonConst.STR_MODULE_EODN;
            case CommonConst.MODULE_EASYOPTICAL:
                return CommonConst.STR_MODULE_EASYOPTICAL;
            case CommonConst.MODULE_DEBUG:
                return CommonConst.STR_MODULE_DEBUG;
            default:
                LogPrint.logError(logger, "CommonUtil.getModuleName, Unknown module:" + module);
                return null;
        }
    }
    
    public static int getModuleFromStr(String str)
    {
        if(str.equalsIgnoreCase(CommonConst.STR_MODULE_COMMON) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_COMMON)))
        {
            return CommonConst.MODULE_COMMON;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_COMMONSH) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_COMMONSH)))
        {
            return CommonConst.MODULE_COMMONSH;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_UNI) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_UNI)))
        {
            return CommonConst.MODULE_UNI;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_MAP) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_MAP)))
        {
            return CommonConst.MODULE_MAP;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_DSL) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_DSL)))
        {
            return CommonConst.MODULE_DSL;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_IS) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_IS)))
        {
            return CommonConst.MODULE_IS;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_AG) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_AG)))
        {
            return CommonConst.MODULE_AG;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_A10) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_A10)))
        {
            return CommonConst.MODULE_A10;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_EODN) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_EODN)))
        {
            return CommonConst.MODULE_EODN;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_EASYOPTICAL) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_EASYOPTICAL)))
        {
            return CommonConst.MODULE_EASYOPTICAL;
        }
        else if(str.equalsIgnoreCase(CommonConst.STR_MODULE_DEBUG) || str.equalsIgnoreCase(Integer.toString(CommonConst.MODULE_DEBUG)))
        {
            return CommonConst.MODULE_DEBUG;
        }
        else
        {
            return -1;
        }
    }
    
//    public static String getLineSeperator()
//    {
//        return (String) java.security.AccessController.doPrivileged(
//            new sun.security.action.GetPropertyAction("line.separator"));
//    }
    
    public static void closeInputStream(InputStream inputStream)
    {
        if(inputStream != null)
        {
            try
            {
                inputStream.close();
            }
            catch (IOException ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
    }
    
    public static void closeOutputStream(OutputStream outputStream)
    {
        if(outputStream != null)
        {
            try
            {
                outputStream.close();
            }
            catch (IOException ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
    }
    
    public static void closeBufferedReader(BufferedReader bufferedReader)
    {
        if(bufferedReader != null)
        {
            try
            {
                bufferedReader.close();
            }
            catch (IOException ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
    }
    
    public static void closeInputStreamReader(InputStreamReader inputStreamReader)
    {
        if(inputStreamReader != null)
        {
            try
            {
                inputStreamReader.close();
            }
            catch (IOException ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
    }
    
    public static void closeFileWriter(FileWriter out)
    {
        if(out != null)
        {
            try
            {
                out.flush();
                out.close();
            }
            catch (IOException ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
    }
    
    public static void closeFileReader(FileReader reader)
    {
        if(reader != null)
        {
            try
            {
                reader.close();
            }
            catch (IOException ex)
            {
                LogPrint.logError(logger, ex);
            }
        }
    }
    
    /**
     * 如果file是目录，则file下所有子目录和所有文件(不删除当前目录)
     * 如果file是文件，则删除file
     * @return void
     * @throws IOException 
     */
    public static void deleteFiles(File file) throws IOException
    {
        if(file.isDirectory())
        {
            File delFile[] = file.listFiles();
            for(int i = 0; i < delFile.length; i++)
            {
                deleteFiles(delFile[i]);
                delFile[i].delete();
            }
        }
        else
        {
            if(file.isFile())
            {
                if(!file.delete())
                {
                    throw new IOException("Cannot delete file :" + file.getAbsolutePath());
                }
            }
        }
    }
    
    /**
     * 递归删除指定目录file下所有文件，保留文件夹结构
     * 如果file为文件，则删除file
     * @param file
     * @throws IOException
     */
    public static void deleteFilesWithoutDir(File file) throws IOException
    {
        if(file.isDirectory())
        {
            File delFile[] = file.listFiles();
            for(int i = 0; i < delFile.length; i++)
            {
                deleteFilesWithoutDir(delFile[i]);
//                delFile[i].delete();
            }
        }
        else
        {
            if(file.isFile())
            {
                if(!file.delete())
                {
                    throw new IOException("Cannot delete file :" + file.getAbsolutePath());
                }
            }
        }
    }
    
    /**
     * 功能类似deleteFiles。但如果file是文件夹，则凡是名为ignoreFolderName的file的子文件夹，均不会去删除这些子文件夹及其包含的内容。
     * @return void
     * @throws IOException 
     */
    public static void deleteFilesWithIgnore(File file, String ignoreFolderName) throws IOException
    {
        if(file.isDirectory())
        {
            if(file.getName().equals(ignoreFolderName))
            {
                return;
            }
            
            File delFile[] = file.listFiles();
            for(int i = 0; i < delFile.length; i++)
            {
                deleteFilesWithIgnore(delFile[i], ignoreFolderName);
                delFile[i].delete();
            }
        }
        else
        {
            if(file.isFile())
            {
                if(!file.delete())
                {
                    throw new IOException("Cannot delete file :" + file.getAbsolutePath());
                }
            }
        }
    }
    
    /**
     * 递归删除指定目录file下所有文件，保留文件夹结构
     * 如果file为文件，则删除file
     * ignoreFolderName为忽略不删除的文件夹名称
     * @param file
     * @throws IOException
     */
    public static void deleteFilesWithIgnoreWithoutDir(File file, String ignoreFolderName) throws IOException
    {
        if(file.isDirectory())
        {
            if(file.getName().equals(ignoreFolderName))
            {
                return;
            }
            
            File delFile[] = file.listFiles();
            for(int i = 0; i < delFile.length; i++)
            {
                deleteFilesWithIgnoreWithoutDir(delFile[i], ignoreFolderName);
//                delFile[i].delete();
            }
        }
        else
        {
            if(file.isFile())
            {
                if(!file.delete())
                {
                    throw new IOException("Cannot delete file :" + file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 删除指定目录，如果file表示一个文件，则删除该文件
     * @return void
     * @throws IOException 
     */
    public static boolean deleteDir(File file) throws IOException
    {
        if(file.exists())
        {
            if(file.isDirectory())
            {
                deleteFiles(file);
            }

            return file.delete();
        }
        
        return true;
    }
    
    /**
     * 给定目录路径，如果该路径下不存在目录，则创建。
     * @return File
     */
    public static File createdDirIfNoExist(String fullFileName)
    {
        File file = new File(fullFileName);
        if(!file.isDirectory())
        {
            if(!file.mkdirs())
            {
                System.out.println("create dir for " + fullFileName + "failed.");
            }
        }

        return file;
    }
    
    
    public static boolean isJava(File file)
    {
        return getExtension(file).equals(".java");
    }
    
    public static boolean isXml(File file)
    {
        return getExtension(file).equals(".xml");
    }
    
    public static boolean isCsv(File file)
    {
        return getExtension(file).equals(".csv");
    }
    
    public static boolean isSql(File file)
    {
        return getExtension(file).equals(".sql");
    }
    
    private static String getExtension(File file)
    {
        String fileName = file.getName();
        
        int index = fileName.lastIndexOf(".");
        if(index > 0)
        {
            return fileName.substring(index);
        }
        
        return "";
    }
    
    public static void throwExceptionIfError(CmdRunResult result) throws Exception
    {
        if(!result.isSuccess())
        {
            throw new Exception(toLine(result.errorResult));
        }
    }
    
    public static String toLine(List<String> lines)
    {
        if(null == lines || lines.isEmpty())
        {
            return "";
        }
        
        StringBuffer buf = new StringBuffer();
        
        for(String line : lines)
        {
            buf.append(line + ",");
        }
        
        return buf.substring(0, buf.length() - 1);
    }
    
    /**
     * 支持单文件拷贝，也支持从文件夹递归拷贝到文件夹
     * @return void
     */
    public static void copyFiles(File fileFromBaseDir, File fileToBaseDir)
    {
        copyFiles(fileFromBaseDir, fileToBaseDir, fileFromBaseDir);
    }
    
    private static void copyFiles(File fileFromBaseDir, File fileToBaseDir, File tmpFileToCopy)
    {
        try
        {
            if(tmpFileToCopy.isDirectory())
            {
                createDirForTmpFile(fileFromBaseDir, fileToBaseDir, tmpFileToCopy);
                
                File delFile[] = tmpFileToCopy.listFiles();
                for(int i = 0; i < delFile.length; i++)
                {
                    copyFiles(fileFromBaseDir, fileToBaseDir, delFile[i]);
                }
            }
            else
            {
                copySingleFile(tmpFileToCopy, getCopyToFile(fileFromBaseDir, fileToBaseDir, tmpFileToCopy));
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
    }

    private static void createDirForTmpFile(File fileFromBaseDir, File fileToBaseDir, File tmpFileToCopy)
    {
        getCopyToFile(fileFromBaseDir, fileToBaseDir, tmpFileToCopy).mkdirs();
    }

    private static File getCopyToFile(File fileFromBaseDir, File fileToBaseDir, File srcFileToCopy)
    {
        String relativePath = srcFileToCopy.getAbsolutePath().substring(fileFromBaseDir.getAbsolutePath().length());
        return new File(fileToBaseDir, relativePath);
    }
    
    private static boolean copySingleFile(File fileFrom, File copyToFile)
    {
        createParentDir(copyToFile);
        
        InputStream is = null;
        OutputStream os = null;
        try
        {
            is = new FileInputStream(fileFrom);
            os = new FileOutputStream(copyToFile);
            int readLength;
            byte[] buf = new byte[1024000];
            while((readLength = is.read(buf, 0, buf.length)) != -1)
            {
                os.write(buf, 0, readLength);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            closeInputStream(is);
            closeOutputStream(os);
        }

        return true;
    }
    
    private static void createParentDir(File copyToFile)
    {
        File parentDir = copyToFile.getParentFile();
        if(!parentDir.exists())
        {
            parentDir.mkdirs();
        }
    }
    
    public static boolean isRenameToDirInSVNOper(ChangedPathInfo changedPathInfo)
    {
        return isFolder(changedPathInfo.getPath()) && hasCopyFromPath(changedPathInfo);
    }
    
    private static boolean hasCopyFromPath(ChangedPathInfo changedPathInfo)
    {
        return changedPathInfo.getCopyFromPath() != null;
    }
    
    private static boolean isFolder(String path)
    {
        if(path.indexOf("/") >= 0 )
        {
            String fileName = path.substring(path.lastIndexOf("/") + 1);
            
            return !fileName.contains(".");//没有后缀名，认为是文件夹。这样有问题，但没其他办法。
        }
        
        return false;
    }
    
    public static void emptyPath(String path) throws IOException
    {
        CommonUtil.createdDirIfNoExist(path);
        CommonUtil.deleteFiles(new File(path));
        LogPrint.logDebug(logger, path + " has been emptied.");
    }
    
    public static String getReportBackPath(ConfigFileReader configReader, int module, int revision)
    {
        return configReader.getTempPath() + CommonConst.TEMPDIR_REPORT + "/" + CommonUtil.getModuleName(module) + "/" + revision;
    }
    
    public static String getRelativeFileName(File baseDir, File fullFile)
    {
        String[] singleBaseDirs = baseDir.getAbsolutePath().split("\\\\");
        String[] singleFullDirs = fullFile.getAbsolutePath().split("\\\\");
        
        if(singleBaseDirs.length > singleFullDirs.length)
        {
            throw new IllegalArgumentException("Invalid basedir or fullFile:" + baseDir + ":" + fullFile);
        }
        
        int diffIndex = -1;
        for(int i = 0; i < singleBaseDirs.length; i++)
        {
            if(!singleBaseDirs[i].equals(singleFullDirs[i]))
            {
                throw new IllegalArgumentException("Check failed, Invalid basedir or fullFile:" + baseDir + ":" + fullFile);
            }
        }
        
        if(diffIndex == - 1)
        {
            diffIndex = singleBaseDirs.length;
            
        }
        
        String relativeFileName = "";
        for(int i = diffIndex; i < singleFullDirs.length; i++)
        {
            relativeFileName += singleFullDirs[i] + "\\";
        }
        
        if("".equalsIgnoreCase(relativeFileName))
        {
            return "";
        }
        
        return relativeFileName.substring(0, relativeFileName.length() - 1);
    }
    
    
    private CommonUtil()
    {      
    }

}
