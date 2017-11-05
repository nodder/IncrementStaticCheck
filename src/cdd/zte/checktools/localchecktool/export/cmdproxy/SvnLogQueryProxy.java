package cdd.zte.checktools.localchecktool.export.cmdproxy;

import java.util.HashMap;

import cdd.zte.checktools.common.cmds.SvnLogQuery;
import cdd.zte.checktools.common.info.FileSvnLogInfo;


/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SvnLogQueryProxy
{
    private static HashMap<String, FileSvnLogInfo> successRecords = new HashMap<String, FileSvnLogInfo>();
    private static HashMap<String, Exception> failedecords = new HashMap<String, Exception>();
    
    public static FileSvnLogInfo query(String filePath, int revision) throws Exception
    {
        String key = filePath + ":" + revision;
        
        if(successRecords.containsKey(key))
        {
            return successRecords.get(key);
        }
        else if(failedecords.containsKey(key))
        {
            throw failedecords.get(key);
        }
        else
        {
            return doQueryAndRecord(filePath, revision, key);
        }
    }

    private static FileSvnLogInfo doQueryAndRecord(String filePath, int revision, String key) throws Exception
    {
        FileSvnLogInfo svnLogInfo = null;
        
        try
        {
            svnLogInfo = SvnLogQuery.query(filePath, revision);
            successRecords.put(key, svnLogInfo);
            return successRecords.get(key);
        }
        catch(Exception ex)
        {
            failedecords.put(key, ex);
            throw ex;
        }
    }
    
    public static void clearRunningRecords()
    {
        successRecords.clear();
        failedecords.clear();
    }
}
