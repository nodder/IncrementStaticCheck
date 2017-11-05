package cdd.zte.checktools.common.cmds.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.LogPrint;

public class CmdGenerator
{  
    private static Logger logger = Logger.getLogger(CmdGenerator.class.getName());
//    private static String userdir = System.getProperty("user.dir");

    private CmdGenerator()
    {
    }
    
    public static String[] callBat(String batFile, String[] args)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add(batFile);
        
        for (int i = 0; i < args.length; i++)
        {
            cmd.add(args[i]);
        }
        
        LogPrint.logDebug(logger, "callBat:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);     
    }
    
    /**
     * 命令举例：svn export -r 2513 --depth files e:/testSvn e:/svnTemp 
     * @param fromDir
     * @param toDir
     * @param revision
     * @param depth
     * @return
     */
    public static String[] svnExport(String fromDir, String toDir, int revision, String depth)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("export");
        cmd.add("--force");
        cmd.add("-r");
        cmd.add(Integer.toString(revision));
        cmd.add("--depth");
        cmd.add(depth);
        cmd.add(fromDir);
        cmd.add(toDir);
        
        LogPrint.logDebug(logger, "svnExportCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);
    }
    
    /**
     * 命令举例：svn log -v -q -r 4 e:/testSvn
     * @param file
     * @param revsion
     * @return
     */
    public static String[] svnLogCmd(String file, int revsion)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("log");
        cmd.add("-vqr");
        cmd.add(String.valueOf(revsion));
        cmd.add(file);
        
        LogPrint.logDebug(logger, "svnLogCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);
    }
    
    /**
     * 命令举例：svn log -ql 50 e:/testSvn
     * @param file
     * @param revsion
     * @return
     */
    public static String[] svnLogByLenthCmd(String file, int logEntryNumber)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("log");
        cmd.add("-ql");
        cmd.add(String.valueOf(logEntryNumber));
        cmd.add(file);
        
        LogPrint.logDebug(logger, "svnLogByLenthCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);
    }
    
    /**
     * 命令举例：svn log -qr 2395 https://10.67.9.84:8443/svn/NETNUMENN31_AG/trunk/ag
     * @param file 本地文件或URL
     * @param revsion
     * @return
     */
    public static String[] svnLogSpecRevisionByLenthCmd(String file, int revision)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("log");
        cmd.add("-qr");
        cmd.add(String.valueOf(revision));
        cmd.add(file);
        
        LogPrint.logDebug(logger, "svnLogByLenthCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);
    }
    
    /**
     * 命令举例：svn log -vqr 6:3 e:/testSvn
     * beginRevision可以大于endRevision
     * @param file
     * @param beginRevsion
     * @param endRevison
     * @return
     */
    public static String[] svnLogCmd(String file, int beginRevsion, int endRevison)
    {
        ArrayList<String> cmd = new ArrayList<String>();

        cmd.add("svn");
        cmd.add("log");
        cmd.add("-vr");
        cmd.add(endRevison + ":" + beginRevsion);
        cmd.add(file);

        LogPrint.logDebug(logger, "svnLogCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);
    }
    
    /**
     * 命令举例：svn info e:/testSvn
     * @param file
     * @return
     */
    public static String[] svnInfoCmd(String file)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("info");
        cmd.add(file);
        
        
        LogPrint.logDebug(logger, "svnInfoCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);

    }
    
    public static String[] svnStatusCmd(String path)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("status");
        cmd.add(path);

        LogPrint.logDebug(logger, "svnStatusCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);     
    }
    
    /**
     * DOS方式搜索指定路径下的文件，支持通配符
     * 命令举例：g:\fileSearch.bat G:\4x\ems *.xml
     * @param searchPath
     * @param target
     * @return
     */
    public static String[] fileSearthCmd(String searchPath, String target)
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("bin\\fileSearch.bat");
        cmd.add(searchPath);
        cmd.add(target);

        LogPrint.logDebug(logger, "fileSearthCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);     
    }
    
    /**
     * svn --version
     * @return
     */
    public static String[] svnVersion()
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("--version");

        LogPrint.logDebug(logger, "svnVersionCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);   
    }
    
    /**
     * svn --version --quiet
     * @return
     */
    public static String[] svnVersionQuiet()
    {
        ArrayList<String> cmd = new ArrayList<String>();
        
        cmd.add("svn");
        cmd.add("--version");
        cmd.add("--quiet");

        LogPrint.logDebug(logger, "svnVersionQuietCmd:" + logCmd(cmd));
        
        return cmd.toArray(new String[cmd.size()]);   
    }
    
    private static String logCmd(List<String> list)
    {
        String[] as = list.toArray(new String[list.size()]);
        
        StringBuilder stringbuilder = new StringBuilder(80);
        for(int i = 0; i < as.length; i++)
        {
            if(i > 0)
                stringbuilder.append(' ');
            String s3 = as[i];
            if(s3.indexOf(' ') >= 0 || s3.indexOf('\t') >= 0)
            {
                if(s3.charAt(0) != '"')
                {
                    stringbuilder.append('"');
                    stringbuilder.append(s3);
                    if(s3.endsWith("\\"))
                        stringbuilder.append("\\");
                    stringbuilder.append('"');
                    continue;
                }
                if(s3.endsWith("\""))
                    stringbuilder.append(s3);
                else
                    throw new IllegalArgumentException();
            } else
            {
                stringbuilder.append(s3);
            }
        }
        
        return stringbuilder.toString();
    }
}
