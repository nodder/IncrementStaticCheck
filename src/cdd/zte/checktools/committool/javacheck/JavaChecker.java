package cdd.zte.checktools.committool.javacheck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.committool.javacheck.compiledependence.CDChecker;
import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.ConfigFileReader;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.ResultComparator;
import cdd.zte.checktools.common.cmds.SvnExportExecutor;
import cdd.zte.checktools.common.cmds.SvnInfoQuery;
import cdd.zte.checktools.common.info.FileSvnInfo;

public class JavaChecker
{
    private static Logger logger = Logger.getLogger(JavaChecker.class.getName());
    
    private static final String exportToDir =  ConfigFileReader.getInstance().getTempPath();
    private static final String bakReportPath = (new File(System.getProperty("user.dir"))).getAbsolutePath() + "/report";
    
    private JavaChecker()
    { 
    }
    
    public static void check(String path, ArrayList<File> curJavaFiles) throws Exception
    {
        LogPrint.printStep(logger, "开始java文件Local Check");
        
        if(curJavaFiles.size() == 0)
        {
            LogPrint.logInfo(logger, "该目录下没有找到java文件。");
            return;
        }
        
        prepare();
        
        checkCompileDependence(curJavaFiles);
          
        int diff;
        LogPrint.logInfo(logger, "正在进行30 + 8和静态检查......");
        
        if(isUnderSvnControl(path))
        {
            ArrayList<File> srcDirList = SrcDirSearch.search(path);
            
            for(int i = 0; i < srcDirList.size(); i++)
            {
                FileSvnInfo svnInfoOfSrcDir = SvnInfoQuery.query(srcDirList.get(i).getAbsolutePath());
                FileSvnInfo svnInfoOfSrcDirInRepository = SvnInfoQuery.query(svnInfoOfSrcDir.getUrl());
                SvnExportExecutor.execute(svnInfoOfSrcDirInRepository.getUrl(), exportToDir + "/" + i, svnInfoOfSrcDirInRepository.getRevision());

                localCheckInExportDir();           
            }
            
            localCheckInCurrPath(path);
            
            diff = ResultComparator.compare(new File(getBackReportofXml(CommonConst.TEMPDIR_REPORT_PREFIX_OLD)), new File(
                getBackReportofXml(CommonConst.TEMPDIR_REPORT_PREFIX_CURR)));
        }
        else
        {
            localCheckInCurrPath(path);          
            diff = ResultComparator.compare(null, new File(getBackReportofXml(CommonConst.TEMPDIR_REPORT_PREFIX_CURR)));
        }
        
        
        printResult(diff);
    }

    private static void printResult(int diff)
    {
        if(diff > 0)
        {
            LogPrint.logError(logger, "Local Check失败，新增错误数：" + diff + "\n");
            LogPrint.logError(logger, "错误详情请查看：" + (new File(bakReportPath)).getAbsolutePath());
        }
        else if(diff == 0)
        {
            LogPrint.logInfo(logger, "Local Check成功，错误数没有变化。\n");
        }
        else if(diff < 0)
        {
            LogPrint.logInfo(logger, "Local Check成功，错误数减少了" + Math.abs(diff) + "\n");
        }
    }
    
    private static boolean checkCompileDependence(ArrayList<File> curJavaFiles)
    {
        boolean result = true;
        LogPrint.logInfo(logger, "正在检查编译依赖......");
        for(int i = 0; i < curJavaFiles.size(); i++)
        {
            result = result && CDChecker.check(curJavaFiles.get(i));
        }
        
        if(result)
        {
            LogPrint.logInfo(logger, "编译依赖关系检查通过。\n");
        }
        return result;
    }

    private static void localCheckInCurrPath(String path) throws Exception
    {
        LocalChecker localCheck = new LocalChecker(path);
        if(localCheck.runProcess())
        {
            bakLocalCheckReport(CommonConst.TEMPDIR_REPORT_PREFIX_CURR);
        }
    }

    private static void localCheckInExportDir() throws Exception
    {            
        LocalChecker localCheck = new LocalChecker(exportToDir);
        if(localCheck.runProcess())
        {
            bakLocalCheckReport(CommonConst.TEMPDIR_REPORT_PREFIX_OLD);
        }
    }

    private static boolean isUnderSvnControl(String path)
    {
        FileSvnInfo svnInfo = null;
        
        try
        {
            svnInfo = SvnInfoQuery.query(path);
            return svnInfo.getRevision() > 0;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    private static void bakLocalCheckReport(String prefix)
    {        
        String reportOfHtml = ConfigFileReader.getInstance().getCheckstylePath() + "/" + CommonConst.LOCALCHECK_REPORT_FILE_HTML;
        String reportOfXml = ConfigFileReader.getInstance().getCheckstylePath() + "/" + CommonConst.LOCALCHECK_REPORT_FILE_XML;
//        String reportOfTxt = ConfigFileReader.getInstance().getCheckstylePath() + "/" + CommonConst.LOCALCHECK_REPORT_FILE_TXT;
        
        String bakReportOfHtml = bakReportPath + "/" + prefix + CommonConst.LOCALCHECK_REPORT_FILE_HTML;
        String bakReportOfXml = getBackReportofXml(prefix);
//        String bakReportOfTxt = getBackReportOfTxtPath(prefix);

        CommonUtil.createdDirIfNoExist(bakReportPath);
        CommonUtil.copyFiles(new File(reportOfHtml), new File(bakReportOfHtml));
        CommonUtil.copyFiles(new File(reportOfXml), new File(bakReportOfXml));
//        CommonUtil.copyFiles(new File(reportOfTxt), new File(bakReportOfTxt));      
    }

    private static String getBackReportofXml(String prefix)
    {
        return bakReportPath + "/" + prefix + CommonConst.LOCALCHECK_REPORT_FILE_XML;
    }
    
    private static void prepare() throws IOException
    {
        if(!CommonUtil.deleteDir(new File(exportToDir)))
        {
            LogPrint.logError(logger, "删除文件夹" + exportToDir + "失败！请手工删除文件夹再试。");
        }
        
        CommonUtil.deleteFiles(new File(bakReportPath));
        CommonUtil.createdDirIfNoExist(bakReportPath);
    }
}
