package cdd.zte.checktools.localchecktool.export;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.info.ChangedPathInfo;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ChangedPathInfoListFilter
{
    private static Logger logger = Logger.getLogger(ChangedPathInfoListFilter.class.getName());
    
    /**
     * 对重命名文件夹，且该文件夹下还有文件修改、删除、创建的情况，仅需要导出重命名前后的文件夹即可，不需要再对具体文件再处理。这些具体文件应该被过滤掉。
     * 例如，如下情况，只需要导出入库前的comF4和入库后的newComF4文件夹即可。后面具体的java文件都不需要处理。
     * C:\Users\chenduoduo>svn log -vqr 18600:18600 https://10.67.9.84:8443/svn/ZXNM01V4_DOC

     * ------------------------------------------------------------------------
     * r18600 | chenduoduo | 2012-08-01 14:42:21 +0800 (周三, 01 八月 2012)
     * Changed paths:
     *    D /trunk/summarize/个人总结/DSL开发人员/陈多多/test/comF4
     *    A /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4 (from /trunk/sum
     * marize/个人总结/DSL开发人员/陈多多/test/comF4:18528)
     *    M /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4/ca.java
     *    M /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4/cb.java
     *    M /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4/cc.java
     *    M /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4/inComF/inCA.java
     * 
     *    M /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4/inComF/inCB.java
     * 
     *    M /trunk/summarize/个人总结/DSL开发人员/陈多多/test/newComF4/inComF/inCC.java
     * 
     * ------------------------------------------------------------------------
     */
    public ChangedPathInfo[] filter(ArrayList<ChangedPathInfo> allChangedPathList)
    {
        ArrayList<ChangedPathInfo> renameToDirInfoList = getRenameToDirInfoList(allChangedPathList);
        return filterOutSubFileInRenameToDir(allChangedPathList, renameToDirInfoList);
    }

    private ChangedPathInfo[] filterOutSubFileInRenameToDir(ArrayList<ChangedPathInfo> allChangedPathList, ArrayList<ChangedPathInfo> renameToDirInfoList)
    {
        ArrayList<ChangedPathInfo> filteredList = new ArrayList<ChangedPathInfo>();
        filteredList.addAll(allChangedPathList);
        
        for(int i = filteredList.size() - 1; i >= 0; i--)
        {
            ChangedPathInfo tmpAll = filteredList.get(i);
            
            for(ChangedPathInfo tmpRenameToDir : renameToDirInfoList)
            {
                if(isFileUnderDir(tmpAll, tmpRenameToDir))
                {
                    LogPrint.logDebug(logger, "Removed " + tmpAll.getPath());
                    filteredList.remove(tmpAll);
                }
            }
        }
        
        return filteredList.toArray(new ChangedPathInfo[0]);
    }

    private boolean isFileUnderDir(ChangedPathInfo tmpAll, ChangedPathInfo tmpRenameToDir)
    {
        return tmpAll.getPath().contains(tmpRenameToDir.getPath() + "/");
    }

    private ArrayList<ChangedPathInfo> getRenameToDirInfoList(ArrayList<ChangedPathInfo> changedPathList)
    {
        ArrayList<ChangedPathInfo> renameToDirInfo = new ArrayList<ChangedPathInfo>();
        
        for(ChangedPathInfo tmpInfo : changedPathList)
        {
            if(CommonUtil.isRenameToDirInSVNOper(tmpInfo))
            {
                renameToDirInfo.add(tmpInfo);
            }
        }
        return renameToDirInfo;
    }
}
