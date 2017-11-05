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
     * ���������ļ��У��Ҹ��ļ����»����ļ��޸ġ�ɾ�������������������Ҫ����������ǰ����ļ��м��ɣ�����Ҫ�ٶԾ����ļ��ٴ�����Щ�����ļ�Ӧ�ñ����˵���
     * ���磬���������ֻ��Ҫ�������ǰ��comF4�������newComF4�ļ��м��ɡ���������java�ļ�������Ҫ����
     * C:\Users\chenduoduo>svn log -vqr 18600:18600 https://10.67.9.84:8443/svn/ZXNM01V4_DOC

     * ------------------------------------------------------------------------
     * r18600 | chenduoduo | 2012-08-01 14:42:21 +0800 (����, 01 ���� 2012)
     * Changed paths:
     *    D /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/comF4
     *    A /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4 (from /trunk/sum
     * marize/�����ܽ�/DSL������Ա/�¶��/test/comF4:18528)
     *    M /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4/ca.java
     *    M /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4/cb.java
     *    M /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4/cc.java
     *    M /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4/inComF/inCA.java
     * 
     *    M /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4/inComF/inCB.java
     * 
     *    M /trunk/summarize/�����ܽ�/DSL������Ա/�¶��/test/newComF4/inComF/inCC.java
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
