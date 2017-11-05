package cdd.zte.checktools.committool.common;

import java.io.File;
import java.util.ArrayList;

import cdd.zte.checktools.common.cmds.SvnStatusQuery;
import cdd.zte.checktools.common.info.ChangedPathInfo;
import cdd.zte.checktools.common.info.SvnStatusInfo;


public class FileConvertor
{
    private FileConvertor()
    {
    }
    
    public static ArrayList<SvnStatusInfo> fileToSvnStatusInfo(String absolutePath, ArrayList<File> files) throws Exception
    {
        ArrayList<SvnStatusInfo> listStatusInPath = SvnStatusQuery.query(absolutePath);
        
        ArrayList<SvnStatusInfo> listStatusOfFile = new ArrayList<SvnStatusInfo>();
        
        for(int i = 0; i < files.size(); i++)
        {            
            SvnStatusInfo tmpSvnStatusInfo = new SvnStatusInfo();
            tmpSvnStatusInfo.setPath(files.get(i).getAbsolutePath());
            tmpSvnStatusInfo.setChangedType(ChangedPathInfo.CHANGE_TYPE_UNCONTROL);
            
            for(int j = 0; j < listStatusInPath.size(); j++)
            {
                SvnStatusInfo svnStatusInfo = listStatusInPath.get(j);
                
                File fileOfSvnStatus = new File(svnStatusInfo.getPath());              
                if(fileOfSvnStatus.equals(files.get(j)))
                {
                    tmpSvnStatusInfo.setChangedType(svnStatusInfo.getChangedType());
                }
            }
        }
        
        return listStatusOfFile;
    }
}
