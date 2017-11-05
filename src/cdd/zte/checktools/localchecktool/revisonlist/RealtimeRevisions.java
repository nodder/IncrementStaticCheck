package cdd.zte.checktools.localchecktool.revisonlist;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.cmds.SvnRevisionsQuery;

public class RealtimeRevisions implements IRevisionListMaint
{
    private static final int listLen = 30;
    
    private static Logger logger = Logger.getLogger(RealtimeRevisions.class.getName());
   
    private LinkedList<Integer> revisonList;
    
    private int count = 0;
    
    private String url;
    
    public RealtimeRevisions(int module)
    {
        this.url = CommonUtil.getUrl(module);  
        
        this.revisonList = new LinkedList<Integer>();
    }
  
    @Override
    public int nextRevision()
    {
        return getPreRevision();
    }

    //获取前一个版本号
    private int getPreRevision()
    {
        if(this.revisonList.size() == 0)
        {
            try
            {
                this.revisonList = getPreRevisonList();
            }
            catch(Exception e)
            {
                LogPrint.logError(logger, "Get revision list failed", e);
                return -1;
            }
        }
     
        return this.revisonList.pollFirst();
    }
    
    private LinkedList<Integer> getPreRevisonList() throws Exception
    {
        count++;
        
        int logEntryNumber = listLen * count;
        LinkedList<Integer> revisionList = SvnRevisionsQuery.query(this.url, logEntryNumber);    
        
        int numberToRemove = listLen * (count - 1);
        return filterLinkedList(revisionList, numberToRemove);
    }
    
    private LinkedList<Integer> filterLinkedList(LinkedList<Integer> fullList, int numberToRemove)
    {
        if(fullList.size() < numberToRemove)
        {
            LogPrint.logDebug(logger, "filterLinkedList fullList.size() " + fullList.size() + " < numberToRemove" + numberToRemove);
            return new LinkedList<Integer>();
        }
        
        for (int i = 0; i < numberToRemove; i++)
        {
            fullList.removeFirst();
        }
        
        LogPrint.logDebug(logger, "getLinkedList at time " + count + ". Revision List ==" + fullList);
        return fullList;
    }
    
    public static void main(String[] args)
    {
        RealtimeRevisions rs = new RealtimeRevisions(CommonConst.MODULE_COMMONSH);
        
        int count = 32;
        
        for (int i = 0; i < count; i++)
        {
            System.out.print(rs.nextRevision() + ", ");
        }
    }
}
