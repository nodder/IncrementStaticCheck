package cdd.zte.checktools.localchecktool.export.cmdproxy;

import java.util.HashSet;

import cdd.zte.checktools.common.cmds.SvnExportExecutor;


/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SvnExportProxy
{
    private static HashSet<String> records = new HashSet<String>();
    
    public static void execute(String fromDir, String toDir, int revision) throws Exception
    {
        String key = fromDir + ":" + toDir + ":" + revision;
        
        if(cmdAlreadyExecuted(key))
        {
//            System.out.println("not run " + key);
            return;
        }
        else
        {
//            System.out.println("run " + key);
            SvnExportExecutor.execute(fromDir, toDir, revision);
            records.add(key);
        }
    }

    private static boolean cmdAlreadyExecuted(String key)
    {
        return records.contains(key);
    }
    
    public static void clearRunningRecords()
    {
        records.clear();
    }
    
//    public static void main(String[] args) throws Exception
//    {
//        SvnExportProxy.execute("a", "b", 1);
//        SvnExportProxy.execute("a", "b", 2);
//        SvnExportProxy.execute("a", "b", 2);
//        SvnExportProxy.execute("a", "b", 1);
//        
//        SvnExportProxy.clearRunningRecords();
//        
//        SvnExportProxy.execute("a", "b", 1);
//        SvnExportProxy.execute("a", "b", 2);
//        SvnExportProxy.execute("a", "b", 2);
//        SvnExportProxy.execute("a", "b", 1);
//    }
}
