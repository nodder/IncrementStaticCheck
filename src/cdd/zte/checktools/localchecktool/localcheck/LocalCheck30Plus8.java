package cdd.zte.checktools.localchecktool.localcheck;

import cdd.zte.checktools.common.cmds.BatLocalCheckExecutor;

public class LocalCheck30Plus8 extends LocalCheckBat
{
    @Override
    public void doLocalCheck(String dirToCheck) throws Exception
    {
        BatLocalCheckExecutor.execute30Plus8Check(dirToCheck);
    }
}
