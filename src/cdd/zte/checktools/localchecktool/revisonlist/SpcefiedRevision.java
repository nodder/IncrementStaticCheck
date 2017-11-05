package cdd.zte.checktools.localchecktool.revisonlist;



/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SpcefiedRevision implements IRevisionListMaint
{
    private int[] revisions;
    private int index;

    public SpcefiedRevision(int revision)
    {
        this.revisions = new int[] {revision};
        this.index = 0;
    }
    
    @Override
    public int nextRevision()
    {
        if(index == revisions.length)
        {
            return -1;
        }
        
        return revisions[index++];
    }
}