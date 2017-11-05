package cdd.zte.checktools.common.cmds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;
import cdd.zte.checktools.common.info.SvnStatusInfo;

public class SvnStatusQuery
{        
//    private static Logger logger = Logger.getLogger(SvnStatusQuery.class.getName());
 
    public static ArrayList<SvnStatusInfo> query(String filePath) throws Exception
    {        
        return querySvnLogInfo(filePath);
    }

    private static ArrayList<SvnStatusInfo> querySvnLogInfo(String filePath)
            throws IOException, Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnStatusCmd(filePath));
        CommonUtil.throwExceptionIfError(result);
        
        return assembleSvnStatusInfoList(result.outputResult);
    }

    static ArrayList<SvnStatusInfo> assembleSvnStatusInfoList(List<String> resultStr) throws Exception
    {      
        if(resultStr == null)
        {
            throw new Exception("assembleFileLogInfo exception : runDosCommand returns null");
        }
        
        ArrayList<SvnStatusInfo> listSvnStatusInfo = new ArrayList<SvnStatusInfo>();
        
        for(int i = 0; i < resultStr.size(); i++)
        {       
            String line = resultStr.get(i);
            
            String changeType = line.substring(0, line.indexOf(" ")).trim();
            String path = line.substring(line.indexOf(" ")).trim();
                      
            SvnStatusInfo svnStatusInfo = new SvnStatusInfo();
            svnStatusInfo.setChangedType(changeType); 
            svnStatusInfo.setPath(path);
            
            String fileName = path.substring(path.lastIndexOf("\\") + 1);
            if(fileName.indexOf(".") > 0)
            {
                svnStatusInfo.setExtension(fileName.substring(fileName.indexOf(".")));
            } 
            
            listSvnStatusInfo.add(svnStatusInfo);
        }
        
        return listSvnStatusInfo;
    }
  
    private SvnStatusQuery()
    {
    }
}
