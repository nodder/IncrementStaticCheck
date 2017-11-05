package cdd.zte.checktools.localchecktool.export.oper;

import cdd.zte.checktools.common.info.ChangedPathInfo;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ExportOldForDelete extends ExportOperation
{
    @Override
    public void doExport(ChangedPathInfo[] changedPathInfoList, String exportToBasePath, String responsitoryPath) throws Exception
    {
        JavaFileExportIndirectly expIndirectly = new JavaFileExportIndirectly(this.actualOperRevision);
        
        for(ChangedPathInfo changedPathInfo : changedPathInfoList)
        {
            expIndirectly.exportUsingAncestorDir(changedPathInfo, exportToBasePath, responsitoryPath);
            logExportSingleFile(changedPathInfo);
        }
    }
    
    @Override
    public int getActualOperRevision(int revision)
    {
        return revision - 1;
    }  
}

