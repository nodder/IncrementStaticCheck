package cdd.zte.checktools.committool.xmlcheck;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import cdd.zte.checktools.common.LogPrint;

public class XmlChecker
{      
    private static Logger logger = Logger.getLogger(XmlChecker.class.getName());
    
    private static int sucess = 0;
    private static int fail = 0;
    
    private static boolean isLogSuccess = false;
    
    private static void checkXmlFile(DocumentBuilder db, File tmpXml)
    {        
        try
        {
            Document doc = db.parse(tmpXml);
            doc.getDocumentElement().normalize();
            if(isLogSuccess)
            {
                LogPrint.logInfo(logger, "文件: " + tmpXml + "\n文档结构正确！");
            }
            sucess++;
        }
        catch (SAXParseException e)
        {
            XmlCheckInfo info = new XmlCheckInfo(e);
            LogPrint.logError(logger, info + "\n文档结构错误！！\n");
            fail++;
        }
        catch (Exception e)
        {
            LogPrint.logError(logger, "解析文件出错！！");
            LogPrint.logError(logger, "文件名: " + tmpXml);
            LogPrint.logError(logger, "详细信息: " + e.getMessage() + "\n");
            fail++;
        }
    }

    private static void reset(int fileCount)
    {
        sucess = 0;
        fail = 0;
        
        if(fileCount == 1)
        {
            isLogSuccess = true;
        }
        else
        {
            isLogSuccess = false;
        }
    }
    
    public static void check(ArrayList<File> xmlFileList)
    {
        reset(xmlFileList.size());
        LogPrint.printStep(logger, "开始检查XML文件格式");
        
        try
        {
            if(xmlFileList.size() == 0)
            {
                LogPrint.logInfo(logger, "该目录下没有找到xml文件。");
                return;
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            for (int i = 0; i < xmlFileList.size(); i++)
            {               
                checkXmlFile(db, xmlFileList.get(i)); 
            }
            
            LogPrint.logInfo(logger, "xml检查结果：成功数 " + sucess + "   失败数 " + fail + "   总数 " + xmlFileList.size());
        }
        catch (Exception ex)
        {
            LogPrint.logError(logger, ex);
        }
    }
}
