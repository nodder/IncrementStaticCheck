package cdd.zte.checktools.localchecktool.revisonlist;

import cdd.zte.checktools.common.CommonConst;
import junit.framework.TestCase;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class StartEndPeriodRevisionsTest extends TestCase
{
    public void testNormal()
    {
        IRevisionListMaint rs = new StartEndPeriodRevisions(CommonConst.MODULE_AG, 2485, 2490);
        compare(rs, new int[] {2485, 2488, 2489, 2490, -1});
        
        rs = new StartEndPeriodRevisions(CommonConst.MODULE_AG, 2450, 2456);
        compare(rs,  new int[] {2453, 2454, 2455, -1});
        
        rs = new StartEndPeriodRevisions(CommonConst.MODULE_AG, 2453, 2453);
        compare(rs,  new int[] {2453, -1});
        
        rs = new StartEndPeriodRevisions(CommonConst.MODULE_AG, 2456, 2456);
        compare(rs,  new int[] {-1});
    }

    private void compare(IRevisionListMaint rs, int[] expected)
    {
        for(int i = 0; i < expected.length; i++)
        {
            assertEquals(expected[i], rs.nextRevision());
        }
    }
}
