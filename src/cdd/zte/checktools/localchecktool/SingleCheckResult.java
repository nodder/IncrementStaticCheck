package cdd.zte.checktools.localchecktool;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class SingleCheckResult
{
    public int revision;
    public boolean isNoErrorOccurred = true;
    public int diff = 0;
    
    SingleCheckResult(int revision)
    {
        this.revision = revision;
    }
    
    @Override
    public String toString()
    {
        return "SingleCheckResult [revision=" + revision + ", isNoErrorOccurred=" + isNoErrorOccurred + ", diff=" + diff + "]";
    }

}
