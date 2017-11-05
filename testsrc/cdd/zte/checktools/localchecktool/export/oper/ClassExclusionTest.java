package cdd.zte.checktools.localchecktool.export.oper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.util.AntPathMatcher;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.info.ChangedPathInfo;

public class ClassExclusionTest extends TestCase
{
    private ChangedPathInfo[] list_length1;
    private ChangedPathInfo[] list_length2;
    
    @Override
    protected void setUp() throws Exception
    {
        CommonConst.SOURCE_FOLDERS = new String[] {"src", "testsrc"};
        
        list_length1 = initList("/trunk/dsl/src/com/zte/ums/an/Aaa.java");
        list_length2 = initList("/trunk/dsl/src/com/zte/ums/an/common/Ccc.java", "/trunk/dsl/src/com/zte/ums/an/ui/Ddd.java");
    }
    
    public void test_AnPathMatcher()
    {
        AntPathMatcher matcher = new AntPathMatcher();
        
        assertTrue(matcher.match("com/**", "com/zte/ums/an/Aaa.java"));
        assertTrue(matcher.match("com/**/*.java", "com/zte/ums/an/Aaa.java"));
        assertTrue(matcher.match("com/**/*", "com/zte/ums/an/Aaa.java"));
        assertTrue(matcher.match("**/zte/**/an/**", "com/zte/ums/an/Aaa.java"));
        assertTrue(matcher.match("**/an/**", "com/zte/ums/an/Aaa.java"));
        assertTrue(matcher.match("**/an/*", "com/zte/ums/an/Aaa.java"));
        assertTrue(matcher.match("com/zte/ums/an/A*", "com/zte/ums/an/Aaa.java"));
        
        
        assertFalse(matcher.match("**/ums/*", "com/zte/ums/an/Aaa.java"));
        assertFalse(matcher.match("*/an/**", "com/zte/ums/an/Aaa.java"));
        assertFalse(matcher.match("com/*/*.java", "com/zte/ums/an/Aaa.java"));
        assertFalse(matcher.match("com*/.java", "com/zte/ums/an/Aaa.java"));
        assertFalse(matcher.match("com/zte/ums/an/B*", "com/zte/ums/an/Aaa.java"));
    }
    
    public void test_exclude_nothing()
    {
        String[] toBeExcludedClassPattern = new String[0];
        
        ChangedPathInfo[] excludedList = ClassExclusion.excludeClasses(list_length1, toBeExcludedClassPattern);
        assertChangedPathList(list_length1, excludedList);
        
        excludedList = ClassExclusion.excludeClasses(list_length2, toBeExcludedClassPattern);
        assertChangedPathList(list_length2, excludedList);
    }
    
    public void test_exclude_all1()
    {
        String[] toBeExcludedClassPattern = {"com.zte.ums.an.**.*"};
        testForAll(toBeExcludedClassPattern);
    }

    public void test_exclude_all2()
    {
        String[] toBeExcludedClassPattern = {"com.zte.ums.an.**"};
        testForAll(toBeExcludedClassPattern);
    }
    
    private void testForAll(String[] toBeExcludedClassPattern)
    {
        ChangedPathInfo[] excludedList = ClassExclusion.excludeClasses(list_length1, toBeExcludedClassPattern);
        assertChangedPathList(new ChangedPathInfo[0], excludedList);
        
        excludedList = ClassExclusion.excludeClasses(list_length2, toBeExcludedClassPattern);
        assertChangedPathList(new ChangedPathInfo[0], excludedList);
    }
    
    public void test_exclude_part()
    {
        String[] toBeExcludedClassPattern = {"**.zte.**.an.common.**"};
        
        ChangedPathInfo[] excludedList = ClassExclusion.excludeClasses(list_length1, toBeExcludedClassPattern);
        assertChangedPathList(excludedList, excludedList);
        
        excludedList = ClassExclusion.excludeClasses(list_length2, toBeExcludedClassPattern);
        assertChangedPathList(initList("/trunk/dsl/src/com/zte/ums/an/ui/Ddd.java"), excludedList);
    }
    
    public void test_multiPatterns()
    {
        String[] toBeExcludedClassPattern = {"**.zte.**.an.common.**", "**.zte.**.an.ui.*"};
        
        ChangedPathInfo[] excludedList = ClassExclusion.excludeClasses(list_length1, toBeExcludedClassPattern);
        assertChangedPathList(excludedList, excludedList);
        
        excludedList = ClassExclusion.excludeClasses(list_length2, toBeExcludedClassPattern);
        assertChangedPathList(new ChangedPathInfo[0], excludedList);
    }
    
    public void test_exclude_wrong_pattern()
    {
        String[] toBeExcludedClassPattern = {"**.wrong.**"};
        ChangedPathInfo[] excludedList = ClassExclusion.excludeClasses(list_length1, toBeExcludedClassPattern);
        
        assertChangedPathList(list_length1, excludedList);
    }
    
    public void test_src_folders_not_exist()
    {
        ChangedPathInfo[] list = initList("/trunk/dsl/nosrc/com/zte/ums/an/Aaa.java");
        
        String[] toBeExcludedClassPattern = {"**.zte.**.an.common.**"};
        
        ChangedPathInfo[] excludedList = ClassExclusion.excludeClasses(list, toBeExcludedClassPattern);
        
        assertChangedPathList(list, excludedList);
    }

    private void assertChangedPathList(ChangedPathInfo[] expectedList, ChangedPathInfo[] actualList)
    {
        assertEquals(expectedList.length, actualList.length);
        
        for(int i = 0; i < expectedList.length; i++)
        {
            assertEquals(expectedList[i], actualList[i]);
        }
    }
    
    private ChangedPathInfo[] initList(String... paths)
    {
        List<ChangedPathInfo> list = new ArrayList<ChangedPathInfo>();
        
        for(String path : paths)
        {
            ChangedPathInfo info = new ChangedPathInfo();
            info.setPath(path);
            
            list.add(info);
        }
        
        return list.toArray(new ChangedPathInfo[0]);
    }
}
