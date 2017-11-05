package cdd.zte.checktools.localchecktool.export.oper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.AntPathMatcher;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.info.ChangedPathInfo;

public class ClassExclusion
{
    private static Logger logger = Logger.getLogger(ClassExclusion.class.getName());
    
    public static ChangedPathInfo[] excludeClasses(ChangedPathInfo[] changedPathInfoList, String[] toBeExcludedClassPattern)
    {
        List<ChangedPathInfo> excludedList = new ArrayList<ChangedPathInfo>();

        for(ChangedPathInfo path : changedPathInfoList)
        {
            if(!isNeedExclude(path, toBeExcludedClassPattern))
            {
                excludedList.add(path);
            }
        }

        ChangedPathInfo[] rtnArr = excludedList.toArray(new ChangedPathInfo[0]);
        
        return rtnArr;
    }

    private static boolean isNeedExclude(ChangedPathInfo path, String[] toBeExcludedClassPatterns)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        
        for(String needExcludeClassPattern : toBeExcludedClassPatterns)
        {
            if(matcher.match(formatPattern(needExcludeClassPattern), getClassPath(path.getPath(), CommonConst.SOURCE_FOLDERS)))
            {
                LogPrint.logInfo(logger, "\nIngored by classExclusion.cfg: " + path.getPath() + "\n");
                return true;
            }
            
        }
        
        return false;
    }
    
    //例如，给定**.web.*.msg.*，返回**/web/*/msg/*
    private static String formatPattern(String needExcludeClassPattern)
    {
        return needExcludeClassPattern.replace(".", "/");
    }

    //给定path如/trunk/dsl/src/com/zte/ums/an/Aaa.java,sourceFolders如{"src", "testsrc"}，过滤出com/zte/ums/an.Aaa。注意后缀.java是要去掉的。
    private static String getClassPath(String path, String[] sourceFolders)
    {
        int srcFolderIndex = getSrcFolderIndex(path, sourceFolders);
        String classPath = (srcFolderIndex == -1) ? path : path.substring(srcFolderIndex);
        return classPath.endsWith(".java") ? classPath.substring(0, classPath.length() - ".java".length()) : classPath;
    }
    
    private static int getSrcFolderIndex(String path, String[] sourceFolders)
    {
        for(String srcFolder : sourceFolders)
        {
            int index = path.indexOf(srcFolder + "/");
            if(index != -1)
            {
                return index + (srcFolder + "/").length();
            }
        }
        return -1;
    }
}
