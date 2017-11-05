package cdd.zte.checktools.common;

public class CommonConst
{      
    public static final int MODULE_COMMON = 1;
    public static final int MODULE_COMMONSH = 2;
    public static final int MODULE_UNI = 3;
    public static final int MODULE_MAP = 4;
    public static final int MODULE_DSL = 5;
    public static final int MODULE_IS = 6;
    public static final int MODULE_AG = 7;  
    public static final int MODULE_A10 = 8;
    public static final int MODULE_EODN = 9;
    public static final int MODULE_EASYOPTICAL = 10;
    public static final int MODULE_DEBUG = 100;
    public static final int MODULE_INVALID = 32767;
    
    public static final String STR_MODULE_COMMON = "COMMON";
    public static final String STR_MODULE_COMMONSH = "COMMONSH";
    public static final String STR_MODULE_COMMONSH_CI = "common_sh";
    public static final String STR_MODULE_UNI = "UNI";
    public static final String STR_MODULE_MAP = "MAP";
    public static final String STR_MODULE_DSL = "DSL";
    public static final String STR_MODULE_IS = "IS";
    public static final String STR_MODULE_AG = "AG";
    public static final String STR_MODULE_A10 = "A10";
    public static final String STR_MODULE_EODN = "EODN";
    public static final String STR_MODULE_EASYOPTICAL = "EASYOPTICAL";
    public static final String STR_MODULE_DEBUG = "DEBUG";
    
    public static final String URL_COMMON          = "https://10.67.9.84:8443/svn/NETNUMENN31_General/trunk/zxnm01/common";
    public static final String URL_COMMONSH        = "https://10.67.9.84:8443/svn/NETNUMENN31_Common_SH/trunk/common_sh";
    public static final String URL_UNI             = "https://10.67.9.84:8443/svn/NETNUMENN31_UNI/trunk/uni";
    public static final String URL_MAP             = "https://10.67.9.84:8443/svn/NETNUMENN31_MAP/trunk/map";
    public static final String URL_DSL             = "https://10.67.9.84:8443/svn/NETNUMENN31_DSL/trunk/dsl";
    public static final String URL_IS              = "https://10.67.9.84:8443/svn/NETNUMENN31_IS/trunk/is";
    public static final String URL_AG              = "https://10.67.9.84:8443/svn/NETNUMENN31_AG/trunk/ag";
    public static final String URL_A10             = "https://10.67.9.84:8443/svn/NETNUMENN31_A10/trunk/a10";
    public static final String URL_EODN            = "https://10.67.9.84:8443/svn/NETNUMENN31_EODN/trunk/eodn";
    public static final String URL_EASYOPTICAL     = "https://10.67.9.12:8443/ROS2/ZXESS_CODE/trunk/EasyService3.2.2";
    public static String URL_DEBUG                 = "";

    public static final String RESPONSITORY_COMMON   = "https://10.67.9.84:8443/svn/NETNUMENN31_General";
    public static final String RESPONSITORY_COMMONSH = "https://10.67.9.84:8443/svn/NETNUMENN31_Common_SH";
    public static final String RESPONSITORY_UNI      = "https://10.67.9.84:8443/svn/NETNUMENN31_UNI";
    public static final String RESPONSITORY_MAP      = "https://10.67.9.84:8443/svn/NETNUMENN31_MAP";
    public static final String RESPONSITORY_DSL      = "https://10.67.9.84:8443/svn/NETNUMENN31_DSL";
    public static final String RESPONSITORY_IS       = "https://10.67.9.84:8443/svn/NETNUMENN31_IS";
    public static final String RESPONSITORY_AG       = "https://10.67.9.84:8443/svn/NETNUMENN31_AG";
    public static final String RESPONSITORY_A10      = "https://10.67.9.84:8443/svn/NETNUMENN31_A10";
    public static final String RESPONSITORY_EODN     = "https://10.67.9.84:8443/svn/NETNUMENN31_EODN";
    public static final String RESPONSITORY_EASYOPTICAL     = "https://10.67.9.12:8443/ROS2/ZXESS_CODE";
    public static String RESPONSITORY_DEBUG          = "";
    
    public static final String BAT_30PLUS8 = "increment_30plus8_check.bat";
    
    public static final String BAT_STATIC = "increment_static_check.bat";
    
    public static final String LOCALCHECK_CC_PRJ_NAME = "XXX";
    
    public static final String LOCALCHECK_REPORT_FILE_HTML = "checkstyle_report.html";
    public static final String LOCALCHECK_REPORT_FILE_XML = "checkstyle_report.xml";
    public static final String LOCALCHECK_REPORT_FILE_TXT = LOCALCHECK_CC_PRJ_NAME + "_errors.txt";
    
    public static final String TEMPDIR_EXPORT_FOLDER = "/middir"; //删除动作专用
    public static final String TEMPDIR_EXPORT = "/export";
    public static final String TEMPDIR_REPORT = "/report";
    
    public static final String TEMPDIR_EXPORT_CURR = "/current";   
    public static final String TEMPDIR_EXPORT_OLD = "/old";   
    
    public static final String TEMPDIR_REPORT_PREFIX_CURR = "current_";   
    public static final String TEMPDIR_REPORT_PREFIX_OLD = "old_";
    
    public static ConfigFileReader configFileReader;
    public static ClassExclusionFileReader classExclusionFileReader;
    
    public static final int TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_PASS_AND_NO_NEED_MANUAL_CHECK_30PLUS8 = 0;
    public static final int TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_PASS_AND_NEED_MANUAL_CHECK_30PLUS8 = 1;
    public static final int TOOL_RESULT_STATICCHECK_NOPASS = 10;
    public static final int TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_NOPASS_AND_NO_NEED_MANUAL_CHECK_30PLUS8 = 11;
    public static final int TOOL_RESULT_STATICCHECK_PASS_AND_30PLUS8_NOPASS_AND_NEED_MANUAL_CHECK_30PLUS8 = 12;
    public static final int TOOL_RESULT_EXCEPTION_OCCURRED_IN_TOOL = 13;
    
    public static final String CI_NO_NEED_CHECK_STR = "[Checkstyle30+8检查]:N";
    
    
    public static String[] SOURCE_FOLDERS = {"src", "testsrc"};
        
    private CommonConst()
    {
    }
}

