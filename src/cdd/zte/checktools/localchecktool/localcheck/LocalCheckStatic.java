package cdd.zte.checktools.localchecktool.localcheck;

import cdd.zte.checktools.common.cmds.BatLocalCheckExecutor;

public class LocalCheckStatic extends LocalCheckBat
{
    @Override
    public void doLocalCheck(String dirToCheck) throws Exception
    {
        BatLocalCheckExecutor.executeStaticCheck(dirToCheck);
    }
    
    public static void main(String[] args) throws Exception
    {
        new LocalCheckStatic().doLocalCheck("F:\\SVN_4X\\ZXNM01V4\\zxnm01\\antools\\IncrementStaticCheck\\tool\\.\\temp\\current");
    }
}
