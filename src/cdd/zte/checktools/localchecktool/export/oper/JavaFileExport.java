package cdd.zte.checktools.localchecktool.export.oper;

import java.io.File;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.cmds.SvnExportExecutor;
import cdd.zte.checktools.common.info.ChangedPathInfo;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public abstract class JavaFileExport extends ExportOperation
{
    @Override
    public void doExport(ChangedPathInfo[] changedPathInfoList, String exportToBasePath, String responsitoryPath) throws Exception
    {
        for(ChangedPathInfo changedPathInfo : changedPathInfoList)
        { 
            File destFile = assembleDestFile(changedPathInfo, exportToBasePath, actualOperRevision);
            
            if(isJava(destFile))
            {
                String tmpSrcFile = assembleSrcFile(changedPathInfo, responsitoryPath);
                CommonUtil.createdDirIfNoExist(destFile.getParent());
                
                //导出失败，则使用间接方法从父目录或者更早的祖先目录导出。
                try
                {
                    SvnExportExecutor.execute(tmpSrcFile, destFile.getAbsolutePath(), actualOperRevision);
                    logExportSingleFile(changedPathInfo);
                }
                catch(Exception e)
                {
                    new JavaFileExportIndirectly(actualOperRevision).exportUsingAncestorDir(changedPathInfo, exportToBasePath, responsitoryPath);
                    logExportSingleFile(changedPathInfo);
                }
            }
        }
    }
    
    private static boolean isJava(File file)
    {
        return file.getName().endsWith(".java");
    }

    protected File assembleDestFile(ChangedPathInfo changedPathInfo, String exportToBasePath, int revision)
    {
        return new File(exportToBasePath + "/" + revision + changedPathInfo.getPath());
    }

    protected String assembleSrcFile(ChangedPathInfo changedPathInfo, String responsitoryPath)
    {
        return responsitoryPath + changedPathInfo.getPath();
    }  
}

