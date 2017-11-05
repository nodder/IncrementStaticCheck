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
public class ExportCurrForAdd extends JavaFileExport
{
    @Override
    public void doExport(ChangedPathInfo[] changedPathInfoList, String exportToBasePath, String responsitoryPath) throws Exception
    {
        super.doExport(changedPathInfoList, exportToBasePath, responsitoryPath);
        
        for(ChangedPathInfo changedPathInfo : changedPathInfoList)
        { 
            if(isFolderThatNeedExport(changedPathInfo))
            {
                File destFile = assembleDestFile(changedPathInfo, exportToBasePath, actualOperRevision);
                String tmpSrcDir = assembleSrcFile(changedPathInfo, responsitoryPath);
                SvnExportExecutor.execute(tmpSrcDir, destFile.getAbsolutePath(), actualOperRevision);
                logExportSingleFile(changedPathInfo);
            }
        }
    }

    private boolean isFolderThatNeedExport(ChangedPathInfo changedPathInfo)
    {
        return CommonUtil.isRenameToDirInSVNOper(changedPathInfo);
    }

    @Override
    public int getActualOperRevision(int revision)
    {
        return revision;
    }
}
