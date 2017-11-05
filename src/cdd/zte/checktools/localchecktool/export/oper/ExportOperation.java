package cdd.zte.checktools.localchecktool.export.oper;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.info.ChangedPathInfo;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public abstract class ExportOperation
{
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    protected int actualOperRevision;
    
    public final void doExport(ChangedPathInfo[] changedPathInfoList, int revision, String exportToBasePath, String responsitoryPath, String[] toBeExcludedClassPattern) throws Exception
    {
        this.actualOperRevision = getActualOperRevision(revision);
        
        ChangedPathInfo[] excludedPathList = ClassExclusion.excludeClasses(changedPathInfoList, toBeExcludedClassPattern);
        
        doExport(excludedPathList, exportToBasePath, responsitoryPath);
    }
    
    protected void logExportSingleFile(ChangedPathInfo changedPathInfo)
    {
        String outputStr = "Export:" + changedPathInfo.getPath() + "[" + changedPathInfo.getChangedType() + ":" + actualOperRevision + "]";
        LogPrint.logInfo(logger, outputStr);
    }
    
    abstract public void doExport(ChangedPathInfo[] changedPathInfoList, String exportToBasePath, String responsitoryPath) throws Exception;
    abstract public int getActualOperRevision(int revision);
}
