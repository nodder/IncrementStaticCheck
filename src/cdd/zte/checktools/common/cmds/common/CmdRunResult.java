package cdd.zte.checktools.common.cmds.common;

import java.util.List;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class CmdRunResult
{
    public List<String> outputResult = null;
    public List<String> errorResult = null;
    
    public CmdRunResult()
    {
    }
    
    public CmdRunResult(List<String> outputResult, List<String> errorResult)
    {
        this.outputResult = outputResult;
        this.errorResult = errorResult;
    }
    
    public boolean isSuccess()
    {
        if(null == errorResult || errorResult.isEmpty())
        {
            return true;
        }
        
        return false;
    }
}
