package cdd.zte.checktools.localchecktool.cireport;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class CIReportUtil
{
    private static final String VARIABLE_MODULE = "@#$";
    
    private static final String REPORT_BASE_DIR = "report";

    private static final String CSV_REPORT_30PLUS8 = "report/30plus8_check_result/" + VARIABLE_MODULE + "/" + VARIABLE_MODULE + "_30plus8_check_summary.csv";
    private static final String ZIP_REPORT_30PLUS8 = "report/30plus8_check_result/" + VARIABLE_MODULE + "/" + VARIABLE_MODULE + "_30plus8_check_report.zip";
    
    private static final String CSV_REPORT_STATIC = "report/static_check_result/" + VARIABLE_MODULE + "/" + VARIABLE_MODULE + "_static_check_summary.csv";
    private static final String ZIP_REPORT_STATIC = "report/static_check_result/" + VARIABLE_MODULE + "/" + VARIABLE_MODULE + "_static_check_report.zip";
    
    private static final String CSV_REPORT_MANUAL_CHECK = "report/manual_check_30plus8/" + VARIABLE_MODULE + "_manual_check_30plus8.csv";
   
    private static final String CSV_REPORT_EXCEPTION = "report/exception_result/" + VARIABLE_MODULE + "_exception_summary.csv";
    
    public static String getReportBaseDir()
    {
        return REPORT_BASE_DIR;
    }
    
    public static String getCsvReportFor30Plus8(int module)
    {
        return CSV_REPORT_30PLUS8.replace(VARIABLE_MODULE, CommonUtil.getModuleName(module).toLowerCase());
    }
    
    public static String getZipReportFor30Plus8(int module)
    {
        return ZIP_REPORT_30PLUS8.replace(VARIABLE_MODULE, CommonUtil.getModuleName(module).toLowerCase());
    }
    
    public static String getCsvReportForStatic(int module)
    {
        return CSV_REPORT_STATIC.replace(VARIABLE_MODULE, CommonUtil.getModuleName(module).toLowerCase());
    }
    
    public static String getZipReportForStatic(int module)
    {
        return ZIP_REPORT_STATIC.replace(VARIABLE_MODULE, CommonUtil.getModuleName(module).toLowerCase());
    }
    
    public static String getCsvReportForManualCheck(int module)
    {
        return CSV_REPORT_MANUAL_CHECK.replace(VARIABLE_MODULE, CommonUtil.getModuleName(module).toLowerCase());
    }
    
    public static String getCsvReportForException(int module)
    {
        return CSV_REPORT_EXCEPTION.replace(VARIABLE_MODULE, CommonUtil.getModuleName(module).toLowerCase());
    }
    
    public static void main(String[] args)
    {
        System.out.println(getCsvReportFor30Plus8(CommonConst.MODULE_COMMONSH));
        System.out.println(getZipReportFor30Plus8(CommonConst.MODULE_COMMONSH));
    }
}
