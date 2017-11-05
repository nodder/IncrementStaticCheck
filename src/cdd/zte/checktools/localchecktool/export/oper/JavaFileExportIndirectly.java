package cdd.zte.checktools.localchecktool.export.oper;

import java.io.File;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.info.ChangedPathInfo;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnExportProxy;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnLogQueryProxy;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class JavaFileExportIndirectly
{
    private String midDir;
    private int revision;
    
    public JavaFileExportIndirectly(int revision)
    {
        this.revision = revision;
    }

    public void exportUsingAncestorDir(ChangedPathInfo changedPathInfo, String exportToBasePath, String responsitoryPath) throws Exception
    {
        this.midDir = exportToBasePath + CommonConst.TEMPDIR_EXPORT_FOLDER;
        
        String exportToBaseDir = exportToBasePath + "/" + revision;
        
        String ancestorChangedPath = getExistedAncestorChangedPath(responsitoryPath, changedPathInfo);
        
        exportParentDirToMidDir(responsitoryPath, ancestorChangedPath);
        
        copyDeletedFilesFromMibDirToExportDir(changedPathInfo, exportToBaseDir);
    }
    
    private String getExistedAncestorChangedPath(String responsitoryPath, ChangedPathInfo changedPathInfo)
    {
        String ancestorPath = getParentPath(changedPathInfo.getPath());

        while(!isPathExist(ancestorPath, responsitoryPath))
        {
            ancestorPath = getParentPath(ancestorPath);
        }
        
        return ancestorPath;
    }

    private boolean isPathExist(String changedpath, String responsitoryPath)
    {
        try
        {
            SvnLogQueryProxy.query(responsitoryPath + changedpath , this.revision);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private void copyDeletedFilesFromMibDirToExportDir(ChangedPathInfo changedPathInfo, String exportToBaseDir)
    {
        String specifiedFile = changedPathInfo.getPath();
        copyToExportDir(specifiedFile, exportToBaseDir);
    }

    private void copyToExportDir(String specfiedFile, String exportToBaseDir)
    {
        String srcMidFile = midDir + specfiedFile;
        String destExportFile = exportToBaseDir + specfiedFile;

        CommonUtil.copyFiles(new File(srcMidFile), new File(destExportFile));
    }
    
    private void exportParentDirToMidDir(String responsitoryPath, String parentDirForExp) throws Exception
    {
        String destMidDir = getDestMidDir(parentDirForExp);
        SvnExportProxy.execute(responsitoryPath + parentDirForExp, destMidDir, this.revision);
    }

    private String getDestMidDir(String parentDirForExp)
    {
        return new File(midDir, parentDirForExp).getAbsolutePath();
    }
    
    private static String getParentPath(String path)
    {
        if(path.indexOf("/") >= 0)
        {
            return path.substring(0, path.lastIndexOf("/"));
        }

        return path;
    }
}

