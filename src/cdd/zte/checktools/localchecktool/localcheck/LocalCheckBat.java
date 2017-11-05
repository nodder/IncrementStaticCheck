package cdd.zte.checktools.localchecktool.localcheck;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public abstract class LocalCheckBat
{
    public LocalCheckBat()
    {
    }
    
    abstract public void doLocalCheck(String dirToCheck) throws Exception;
}
