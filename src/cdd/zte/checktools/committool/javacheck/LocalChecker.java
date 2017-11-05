package cdd.zte.checktools.committool.javacheck;

import cdd.zte.checktools.common.cmds.BatLocalCheckExecutor;

public class LocalChecker
{
    private String dirToCheck; 
    
    public LocalChecker(String dirToCheck)
    {
        this.dirToCheck = dirToCheck;   
    }
    
    public boolean runProcess() throws Exception
    {
        doLocalCheck();
        return true;
    }

    private void doLocalCheck() throws Exception
    {
        BatLocalCheckExecutor.execute30Plus8Check(dirToCheck);
    }
}