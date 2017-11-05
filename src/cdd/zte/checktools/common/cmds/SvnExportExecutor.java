package cdd.zte.checktools.common.cmds;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;

public class SvnExportExecutor
{        
//    private static Logger logger = Logger.getLogger(SvnExportExecutor.class.getName());
    
    public static final String DEPTH_FILES="files";
    public static final String DEPTH_INFINITY="infinity";
    
    public static void execute(String fromDir, String toDir, int revision) throws Exception
    {
        execute(fromDir, toDir, revision, DEPTH_INFINITY);
    }
    
    public static void execute(String fromDir, String toDir, int revision, String depth) throws Exception
    {
        executeExport(fromDir, toDir, revision, depth);
    }
    
    private static void executeExport(String fromDir, String toDir, int revision, String depth) throws Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnExport(fromDir, toDir, revision, depth));
        throwExceptionIfError(result);
    }
    
    private static void throwExceptionIfError(CmdRunResult result) throws Exception
    {
        if(!result.isSuccess())
        {
            result.errorResult.add(0, "导出操作出现错误，可能引起本次Check不准确。详细信息：");
            CommonUtil.throwExceptionIfError(result);
        }
    }
}
