package cdd.zte.checktools.localchecktool;

import junit.framework.Test;
import junit.framework.TestSuite;
import cdd.zte.checktools.common.cmds.SvnInfoQureyTest;
import cdd.zte.checktools.common.cmds.SvnLogQueryTest;
import cdd.zte.checktools.common.cmds.SvnRevisionQueryTest;
import cdd.zte.checktools.common.cmds.SvnVersionQueryTest;
import cdd.zte.checktools.common.cmds.common.RunProcessTest;
import cdd.zte.checktools.common.fileParser.FileZipperTest;
import cdd.zte.checktools.localchecktool.export.oper.ClassExclusionTest;
import cdd.zte.checktools.localchecktool.revisonlist.StartEndPeriodRevisionsTest;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class TestAll
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Policy Test");
        suite.addTestSuite(SingleCheckRunnerTest.class);
        suite.addTestSuite(StartEndPeriodRevisionsTest.class);
        suite.addTestSuite(SvnInfoQureyTest.class);
        suite.addTestSuite(SvnLogQueryTest.class);
        suite.addTestSuite(SvnRevisionQueryTest.class);
        suite.addTestSuite(SvnVersionQueryTest.class);
        suite.addTestSuite(FileZipperTest.class);
        suite.addTestSuite(ClassExclusionTest.class);
        suite.addTestSuite(RunProcessTest.class);
        return suite;
    }
}
