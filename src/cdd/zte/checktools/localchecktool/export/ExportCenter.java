package cdd.zte.checktools.localchecktool.export;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.info.ChangedPathInfo;
import cdd.zte.checktools.common.info.FileSvnLogInfo;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnExportProxy;
import cdd.zte.checktools.localchecktool.export.cmdproxy.SvnLogQueryProxy;

public class ExportCenter
{
    private final String responsitoryPath;
    private final String exportToBasePath;
    
    private final FileSvnLogInfo svnLogInfo;
    private String[] toBeExcludedClassPattern;
    
    public ExportCenter(int module, String exportToBaseDir, int revision) throws Exception
    {
        this(module, exportToBaseDir, revision, new String[0]);
    }
    
    public ExportCenter(int module, String exportToBaseDir, int revision, String[] toBeExcludedClassPattern) throws Exception
    {
        this.responsitoryPath = CommonUtil.getResponsitoryPath(module);
        this.svnLogInfo = SvnLogQueryProxy.query(CommonUtil.getUrl(module), revision);
        this.exportToBasePath = exportToBaseDir;
        this.toBeExcludedClassPattern = toBeExcludedClassPattern;
    }

    public void runExportProcess() throws Exception
    {
        ArrayList<ChangedPathInfo> changedPathList = svnLogInfo.getChangedPaths();

        ExpOperationsDispatch expDispatch = new ExpOperationsDispatch(svnLogInfo.getRevision(), exportToBasePath, responsitoryPath);
        expDispatch.prepareData(changedPathList);
        
        String exportPathForCurr = expDispatch.exportForCurrentRevision(toBeExcludedClassPattern);
        String exportPathForOld = expDispatch.exportForOldRevision(toBeExcludedClassPattern);
        
        cleanData(exportPathForCurr, exportPathForOld);
    }

    private void cleanData(String exportPathForCurr, String exportPathForOld) throws IOException
    {
        cleanMidDir(new File(exportPathForCurr, CommonConst.TEMPDIR_EXPORT_FOLDER));
        cleanMidDir(new File(exportPathForOld, CommonConst.TEMPDIR_EXPORT_FOLDER));
        
        SvnExportProxy.clearRunningRecords();
        SvnLogQueryProxy.clearRunningRecords();
    }
    
    private void cleanMidDir(File midDir) throws IOException
    {
        CommonUtil.deleteDir(midDir);
    }

}
