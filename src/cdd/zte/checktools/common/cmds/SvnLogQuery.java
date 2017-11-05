package cdd.zte.checktools.common.cmds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.cmds.common.CmdGenerator;
import cdd.zte.checktools.common.cmds.common.CmdRunResult;
import cdd.zte.checktools.common.cmds.common.RunProcess;
import cdd.zte.checktools.common.info.ChangedPathInfo;
import cdd.zte.checktools.common.info.FileSvnLogInfo;

public class SvnLogQuery
{        
    private static Logger logger = Logger.getLogger(SvnLogQuery.class.getName());
    
    public static FileSvnLogInfo query(String filePath, int revision) throws Exception
    {
        if(revision < 0 )
        {
            throw new IllegalArgumentException("Invalid number : revision==" + revision);
        }
        
        return querySvnLogInfo(filePath, revision, revision);
    }
    
    public static FileSvnLogInfo query(String filePath, int beginRevision, int endRevision) throws Exception
    {
        if(beginRevision < 0 || beginRevision > endRevision)
        {
            throw new IllegalArgumentException("Invalid number : beginRevision==" + beginRevision 
                    + ", endRevision=="+ endRevision);
        }
        
        return querySvnLogInfo(filePath, beginRevision, endRevision);
    }

    private static FileSvnLogInfo querySvnLogInfo(String filePath, int beginRevision, int endRevision)
            throws IOException, Exception
    {
        CmdRunResult result = RunProcess.runDosCommand(CmdGenerator.svnLogCmd(filePath,
                beginRevision, endRevision));
        
        CommonUtil.throwExceptionIfError(result);
        return assembleFileLogInfo(result.outputResult);
    }

    static FileSvnLogInfo assembleFileLogInfo(List<String> resultStr)
    {      
        FileSvnLogInfo fileSvnLogInfo = new FileSvnLogInfo();
        
        final int summaryLine = 1;
        if(resultStr.size() > summaryLine)
        {
            String line = resultStr.get(summaryLine);
            assembleSummaryInfo(line, fileSvnLogInfo);
            
            final int[] changedPathLinePeriod = getChangedPathLinePeriod(resultStr);
            assembleSummaryInfo(resultStr, changedPathLinePeriod, fileSvnLogInfo);
            
            
            final int[] noteLinePeriod = getNoteLinePeriod(resultStr, changedPathLinePeriod[changedPathLinePeriod.length - 1] + 1);
            assembleNoteLineInfo(resultStr, noteLinePeriod, fileSvnLogInfo);
        }
        
        return fileSvnLogInfo;
    }

    /**
     * @param resultStr
     * @param noteLinePeriod
     * @param fileSvnLogInfo
     */
    private static void assembleNoteLineInfo(List<String> resultStr, int[] noteLinePeriod, FileSvnLogInfo fileSvnLogInfo)
    {
        StringBuffer strBuff = new StringBuffer();
        for(int lineNo : noteLinePeriod)
        {
            strBuff.append(resultStr.get(lineNo) + "\n");
        }
        
        fileSvnLogInfo.setNoteStr(strBuff.toString());
    }

    /**
     * @param resultStr
     * @param changedPathLinePeriod
     * @param fileSvnLogInfo
     */
    private static void assembleSummaryInfo(List<String> resultStr, int[] changedPathLinePeriod, FileSvnLogInfo fileSvnLogInfo)
    {
        ArrayList<ChangedPathInfo> listChangedPathInfo = new ArrayList<ChangedPathInfo>(); 
        fileSvnLogInfo.setChangedPaths(listChangedPathInfo);
        
        for(int lineNo : changedPathLinePeriod)
        {
            ChangedPathInfo tmpInfo = new ChangedPathInfo();
            
            String trimLine = resultStr.get(lineNo).trim();
            tmpInfo.setChangedType(trimLine.substring(0, 1));
            
            String fullPath = trimLine.substring(2).trim();
            tmpInfo.setPath(getPath(fullPath));
            tmpInfo.setCopyFromPath(getCopyFromPath(fullPath));
            tmpInfo.setCopyFromRevision(getCopyFromRevision(fullPath));
            
            listChangedPathInfo.add(tmpInfo);
        }
    }

    private static void assembleSummaryInfo(String line, FileSvnLogInfo fileSvnLogInfo)
    {
        StringTokenizer st = new StringTokenizer(line, "|");
        if(st.countTokens() != 4)
        {
            String errDesc = "assembleFileLogInfo exception :\n No valid data found in line:" + line;                    
            LogPrint.logError(logger, errDesc);
        }
        
        fileSvnLogInfo.setRevision(Integer.parseInt(st.nextToken().substring(1).trim()));
        fileSvnLogInfo.setAuthor(st.nextToken().trim());
        fileSvnLogInfo.setModifyTime(formatTimeFromStr(st.nextToken()));
    }
    
    /**
     * @param resultStr
     * @param startLineNo 
     * @return
     */
    private static int[] getNoteLinePeriod(List<String> resultStr, int startLineNo)
    {
        return getPeriodFromStartToend(startLineNo, resultStr.size() - 2);
    }

    /**
     * @param resultStr
     * @return
     */
    private static int[] getChangedPathLinePeriod(List<String> resultStr)
    {
        final int startLineNo = 3;
        
        if(resultStr.size() <= startLineNo)
        {
            return new int[0];
        }
        
        int endLineNo = resultStr.size() - 1;
        for(int i = startLineNo + 1; i < resultStr.size(); i++)
        {
            if("".equals(resultStr.get(i)))
            {
                endLineNo = i - 1;
                break;
            }
        }
        
        return getPeriodFromStartToend(startLineNo, endLineNo);
    }

    private static int[] getPeriodFromStartToend(final int startLineNo, int endLineNo)
    {
        if(endLineNo < startLineNo)
        {
            return new int[0];
        }
        
        int[] linePeriod = new int[endLineNo - startLineNo + 1];
        for(int i = 0; i < linePeriod.length; i++)
        {
            linePeriod[i] = startLineNo + i;
        }
        
        return linePeriod;
    }

    private static String getCopyFromRevision(String str)
    {
        int index = str.indexOf(getSepreate_copyPath_String());
        if(index > 0)
        {
            return str.substring(str.lastIndexOf(':') + 1, str.length() - 1);
        }
        
        return null;
    }

    private static String getCopyFromPath(String str)
    {
        int index = str.indexOf(getSepreate_copyPath_String());
        if(index > 0)
        {
            return str.substring(index + getSepreate_copyPath_String().length(), str.lastIndexOf(':')).trim();
        }
        
        return null;
    }

    private static String getPath(String str)
    {
        int index = str.indexOf(getSepreate_copyPath_String());
        if(index > 0)
        {
            return str.substring(0, index).trim();
        }
        
        return str;
    }

    private static String formatTimeFromStr(String time)
    {
        String trimTime = time.trim();
        return time.substring(0, trimTime.indexOf("+")).trim();
    }
    
    private static String getSepreate_copyPath_String()
    {
        if(EnvLanguage.isEnglish())
        {
            return "(from";
        }
        else
        {
            return "(´Ó";
        }
    }
    
    private SvnLogQuery()
    {
    }
}
