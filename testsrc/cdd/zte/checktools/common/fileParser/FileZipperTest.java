package cdd.zte.checktools.common.fileParser;

import java.io.File;

import junit.framework.TestCase;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class FileZipperTest extends TestCase
{
    public void testxxx()
    {
//        assertEquals("333", CommonUtil.getRelativeFileName(new File("g:\\123"), new File("g:\\123\\333")));
//        assertEquals("333\\444", CommonUtil.getRelativeFileName(new File("g:\\123"), new File("g:\\123\\333\\444")));
//        assertEquals("333\\444.txt", CommonUtil.getRelativeFileName(new File("g:\\123"), new File("g:\\123\\333\\444.txt")));
//        
//        try
//        {
//            CommonUtil.getRelativeFileName(new File("c:\\123"), new File("g:\\123\\333\\444.txt"));
//            assertTrue(false);
//        }
//        catch(IllegalArgumentException ex)
//        {
//            assertTrue(true);
//        }
//        
//        try
//        {
//            assertEquals("333\\444.txt", CommonUtil.getRelativeFileName(new File("g:\\123\223"), new File("g:\\223")));
//            assertTrue(false);
//        }
//        catch(IllegalArgumentException ex)
//        {
//            assertTrue(true);
//        }
    }
    
    
    public static void main(String[] args) throws Exception
    {
        FileZipper zip = new FileZipper();
        
        zip.doZip(new File("g:/111/single.zip"), new File("g:/111/18600ca.java"));
        zip.doZip(new File("g:/111/folder.zip"), new File("g:/111/123"));
        zip.doZip(new File("g:/111/comFolders.zip"), new File("g:/111/223"));
        
//        zip.doZip(new File("g:/111/3.zip"), new File("g:/111/aaa¡£txt"), new File("g:/111/123"));
        zip.doZip(new File("g:/111/ems.zip"), new File("G:/4x/2012-08-06/ums-server/procs/ppus/an.ppu/an-map.pmu"));
    }
}
