package cdd.zte.checktools.common.cmds;

import java.util.List;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;
import cdd.zte.checktools.common.info.FileSvnInfo;

public class SvnInfoQuery
{
    public static FileSvnInfo query(String filePath) throws Exception
    {
        return querySvnInfo(filePath);
    }

    private static FileSvnInfo querySvnInfo(String filePath) throws Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnInfoCmd(filePath));
        CommonUtil.throwExceptionIfError(result);

        return assembleFileSvnInfo(result.outputResult);
    }

    static FileSvnInfo assembleFileSvnInfo(List<String> rtnStrList) throws Exception
    {
        if(EnvLanguage.isEnglish())
        {
            return assembleFromEnvEnglish(rtnStrList);
        }
        
        return assembleFromEnvChinese(rtnStrList);
    }

    private static FileSvnInfo assembleFromEnvChinese(List<String> rtnStrList)
    {
        FileSvnInfo fileSvnInfo = new FileSvnInfo();
        for(int i = 0; i < rtnStrList.size(); i++)
        {
            String element = rtnStrList.get(i);
            if(element.startsWith("路径: "))
            {
                fileSvnInfo.setPath(getStrContent(element));
            }
            else if(element.startsWith("URL: "))
            {
                fileSvnInfo.setUrl(getStrContent(element));
            }
            else if(element.startsWith("版本库根: "))
            {
                fileSvnInfo.setRepositoryRoot(getStrContent(element));
            }
            else if(element.startsWith("版本: "))
            {
                fileSvnInfo.setRevision(Integer.parseInt(getStrContent(element)));
            }
            else if(element.startsWith("节点种类: "))
            {
                fileSvnInfo.setNodeKind(getStrContent(element));
            }
            else if(element.startsWith("最后修改的作者: "))
            {
                fileSvnInfo.setLastChangedAuthor(getStrContent(element));
            }
            else if(element.startsWith("最后修改的版本: "))
            {
                fileSvnInfo.setLastChangedRev(Integer.parseInt(getStrContent(element)));
            }
            else if(element.startsWith("最后修改的时间: "))
            {
                fileSvnInfo.setLastChangedDate(formatTimeFromStr(getStrContent(element)));
            }
        }
        
        return fileSvnInfo;
    }

    private static FileSvnInfo assembleFromEnvEnglish(List<String> rtnStrList)
    {
        FileSvnInfo fileSvnInfo = new FileSvnInfo();
        for(int i = 0; i < rtnStrList.size(); i++)
        {
            String element = rtnStrList.get(i);
            if(element.startsWith("Path"))
            {
                fileSvnInfo.setPath(getStrContent(element));
            }
            else if(element.startsWith("Name"))
            {
                fileSvnInfo.setName(getStrContent(element));
            }
            else if(element.startsWith("URL"))
            {
                fileSvnInfo.setUrl(getStrContent(element));
            }
            else if(element.startsWith("Repository Root"))
            {
                fileSvnInfo.setRepositoryRoot(getStrContent(element));
            }
            else if(element.startsWith("Revision"))
            {
                fileSvnInfo.setRevision(Integer.parseInt(getStrContent(element)));
            }
            else if(element.startsWith("Node Kind"))
            {
                fileSvnInfo.setNodeKind(getStrContent(element));
            }
            else if(element.startsWith("Last Changed Author"))
            {
                fileSvnInfo.setLastChangedAuthor(getStrContent(element));
            }
            else if(element.startsWith("Last Changed Rev"))
            {
                fileSvnInfo.setLastChangedRev(Integer.parseInt(getStrContent(element)));
            }
            else if(element.startsWith("Last Changed Date"))
            {
                fileSvnInfo.setLastChangedDate(formatTimeFromStr(getStrContent(element)));
            }
        }
        
        return fileSvnInfo;
    }

    private static String getStrContent(String element)
    {
        return element.substring(element.indexOf(":") + 1).trim();
    }
    
    private static String formatTimeFromStr(String time)
    {
        String trimTime = time.trim();
        return time.substring(0, trimTime.indexOf("+")).trim();
    }
    
    private SvnInfoQuery()
    {
    }   
}
