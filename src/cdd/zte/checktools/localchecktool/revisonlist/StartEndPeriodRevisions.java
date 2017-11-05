package cdd.zte.checktools.localchecktool.revisonlist;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.LogPrint;
import cdd.zte.checktools.common.cmds.SvnRevisionsQuery;

public class StartEndPeriodRevisions implements IRevisionListMaint
{
    private static Logger logger = Logger.getLogger(StartEndPeriodRevisions.class.getName());
   
    private LinkedList<Integer> revisonList;
    
    private String url;
    private int startRevision;
    private int endRevision;
    
    public StartEndPeriodRevisions(int module, int startRevision, int endRevision)
    {
        this.url = CommonUtil.getUrl(module);
        this.startRevision = startRevision;
        this.endRevision = endRevision;
    }
  
    @Override
    public int nextRevision()
    {
        return getPreRevision();
    }

    //获取前一个版本号
    private int getPreRevision()
    {
        if(this.revisonList == null)
        {
            try
            {
                revisonList = initRevisonList();
            }
            catch(Exception e)
            {
                LogPrint.logError(logger, "Get revision list failed", e);
                return -1;
            }
        }
     
        if(revisonList.isEmpty())
        {
            return -1;
        }
        
        return this.revisonList.pollFirst();
    }

    private LinkedList<Integer> initRevisonList() throws Exception
    {
        if(startRevision > endRevision || endRevision < 0 || startRevision < 0)
        {
            throw new IllegalArgumentException("Invalid period: start: " + startRevision + ", end:" + endRevision);
        }
        
        LinkedList<Integer> validRevions = new LinkedList<Integer>();
        for(int i = startRevision; i <= endRevision; i++)
        {
            if(SvnRevisionsQuery.isRevisionExist(url, i))
            {
                validRevions.add(i);
            }
        }
        
        LogPrint.logDebug(logger, "revisions to check:" + validRevions);
        return validRevions;
    }
}
