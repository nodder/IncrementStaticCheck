package cdd.zte.checktools.committool.javacheck.compiledependence;

import java.util.Hashtable;

public class CDRule
{    
    protected static final int RULE_LEVEL_UNDEFINED = 0;
    
    protected static final int RULE_LEVEL_UEP = 3;
    protected static final int RULE_LEVEL_COMMON = 4;
    protected static final int RULE_LEVEL_COMMONSH = 5;
    protected static final int RULE_LEVEL_UNI_FRAMEWORK = 6;
    protected static final int RULE_LEVEL_UNI_SERVICE = 7;    
    protected static final int RULE_LEVEL_UNI_SYS = 8;
    protected static final int RULE_LEVEL_UNI_LAYER2 = 8;
    protected static final int RULE_LEVEL_UNI_LAYER3 = 8;
    protected static final int RULE_LEVEL_UNI_DSL = 8;
    protected static final int RULE_LEVEL_UNI_VOICE = 8;
    protected static final int RULE_LEVEL_UNI_SIMPLEVOIP = 8;
    protected static final int RULE_LEVEL_UNI_OFD = 8;   
    protected static final int RULE_LEVEL_MAP = 9;   
    protected static final int RULE_LEVEL_DSL = 10;
    protected static final int RULE_LEVEL_IS = 10;
    protected static final int RULE_LEVEL_PON = 10;
    protected static final int RULE_LEVEL_MSTPCOMMON = 10;
    protected static final int RULE_LEVEL_AG = 10;
    protected static final int RULE_LEVEL_EOC = 10;
    protected static final int RULE_LEVEL_A10 = 10;  
      
    
    private static final String RULE_UEP_STR = " com.zte.ums.uep";
    private static final String RULE_COMMON_STR = "com.zte.ums.n3common";
    private static final String RULE_COMMONSH_STR = "com.zte.ums.an.commonsh";
    private static final String RULE_UNI_FRAMEWORK_STR = "com.zte.ums.an.uni.framework";
    private static final String RULE_UNI_SERVICE_STR = "com.zte.ums.an.uni.service";
    private static final String RULE_UNI_SYS_STR = "com.zte.ums.an.uni.sys";
    private static final String RULE_UNI_LAYER2_STR = "com.zte.ums.an.uni.layer2";
    private static final String RULE_UNI_LAYER3_STR = "com.zte.ums.an.uni.layer3";
    private static final String RULE_UNI_DSL_STR = "com.zte.ums.an.uni.dsl";
    private static final String RULE_UNI_VOICE_STR = "com.zte.ums.an.uni.voice";
    private static final String RULE_UNI_SIMPLEVOIP_STR = "com.zte.ums.an.uni.simplevoip";
    private static final String RULE_UNI_OFD_STR = "com.zte.ums.an.uni.ofd";
    private static final String RULE_MAP_STR = "com.zte.ums.an.map";
    private static final String RULE_DSL_STR = "com.zte.ums.an.dsl";
    private static final String RULE_IS_STR = "com.zte.ums.an.is";
    private static final String RULE_PON_STR = "com.zte.ums.an.pon";
    private static final String RULE_MSTPCOMMON_STR = "com.zte.ums.an.mstpcommon";
    private static final String RULE_AG_STR = "com.zte.ums.an.ag";
    private static final String RULE_EOC_STR = "com.zte.ums.an.eoc";
    private static final String RULE_A10_STR = "com.zte.ums.an.a10";
    
    private Hashtable<String, Integer> ruleTable = new Hashtable<String, Integer>();
    
    private CDRule instance = new CDRule();
    
    private CDRule()
    {
        try
        {
            buildRule();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    protected CDRule getInstance()
    {
        return this.instance;
    }
    
    protected Hashtable<String, Integer> getRuleMap()
    {
        return this.ruleTable;
    }
    
    private void buildRule() throws Exception
    {
        
    }
    
    protected static boolean isSameModule(String fileClass, String importedpackets)
    {
        if((fileClass.contains(RULE_UEP_STR) && importedpackets.contains(RULE_UEP_STR))
            || (fileClass.contains(RULE_COMMON_STR) && importedpackets.contains(RULE_COMMON_STR))
            || (fileClass.contains(RULE_COMMONSH_STR) && importedpackets.contains(RULE_COMMONSH_STR))
            || (fileClass.contains(RULE_UNI_FRAMEWORK_STR) && importedpackets.contains(RULE_UNI_FRAMEWORK_STR))
            || (fileClass.contains(RULE_UNI_SERVICE_STR) && importedpackets.contains(RULE_UNI_SERVICE_STR))
            || (fileClass.contains(RULE_UNI_SYS_STR) && importedpackets.contains(RULE_UNI_SYS_STR))
            || (fileClass.contains(RULE_UNI_LAYER2_STR) && importedpackets.contains(RULE_UNI_LAYER2_STR))
            || (fileClass.contains(RULE_UNI_LAYER3_STR) && importedpackets.contains(RULE_UNI_LAYER3_STR))
            || (fileClass.contains(RULE_UNI_DSL_STR) && importedpackets.contains(RULE_UNI_DSL_STR))
            || (fileClass.contains(RULE_UNI_VOICE_STR) && importedpackets.contains(RULE_UNI_VOICE_STR))
            || (fileClass.contains(RULE_UNI_SIMPLEVOIP_STR) && importedpackets.contains(RULE_UNI_SIMPLEVOIP_STR))
            || (fileClass.contains(RULE_UNI_OFD_STR) && importedpackets.contains(RULE_UNI_OFD_STR))
            || (fileClass.contains(RULE_MAP_STR) && importedpackets.contains(RULE_MAP_STR))
            || (fileClass.contains(RULE_DSL_STR) && importedpackets.contains(RULE_DSL_STR))
            || (fileClass.contains(RULE_IS_STR) && importedpackets.contains(RULE_IS_STR))
            || (fileClass.contains(RULE_PON_STR) && importedpackets.contains(RULE_PON_STR))
            || (fileClass.contains(RULE_MSTPCOMMON_STR) && importedpackets.contains(RULE_MSTPCOMMON_STR))
            || (fileClass.contains(RULE_AG_STR) && importedpackets.contains(RULE_AG_STR))
            || (fileClass.contains(RULE_EOC_STR) && importedpackets.contains(RULE_EOC_STR))
            || (fileClass.contains(RULE_A10_STR) && importedpackets.contains(RULE_A10_STR)))
        {
            return true;
        }
        
        return false;
    }
    
    protected static boolean isISModule(String fileClass)
    {
        return fileClass.contains(RULE_IS_STR)
            || fileClass.contains(RULE_PON_STR)
            || fileClass.contains(RULE_MSTPCOMMON_STR);
    }
    
    protected static int findLevel(String data)
    {
        if(data.contains(RULE_COMMON_STR))
        {
            return CDRule.RULE_LEVEL_COMMON;
        }
        else if(data.contains(RULE_COMMONSH_STR))
        {
            return CDRule.RULE_LEVEL_COMMONSH;
        }
        
        else if(data.contains(RULE_UNI_FRAMEWORK_STR))
        {
            return CDRule.RULE_LEVEL_UNI_FRAMEWORK;
        }
        else if(data.contains(RULE_UNI_SERVICE_STR))
        {
            return CDRule.RULE_LEVEL_UNI_SERVICE;
        }
        
        else if(data.contains(RULE_UNI_SYS_STR))
        {
            return CDRule.RULE_LEVEL_UNI_SYS;
        }
        else if(data.contains(RULE_UNI_LAYER2_STR))
        {
            return CDRule.RULE_LEVEL_UNI_LAYER2;
        }
        else if(data.contains(RULE_UNI_LAYER3_STR))
        {
            return CDRule.RULE_LEVEL_UNI_LAYER3;
        }
        else if(data.contains(RULE_UNI_DSL_STR))
        {
            return CDRule.RULE_LEVEL_UNI_DSL;
        }
        else if(data.contains(RULE_UNI_VOICE_STR))
        {
            return CDRule.RULE_LEVEL_UNI_VOICE;
        }
        else if(data.contains(RULE_UNI_SIMPLEVOIP_STR))
        {
            return CDRule.RULE_LEVEL_UNI_SIMPLEVOIP;
        }
        else if(data.contains(RULE_UNI_OFD_STR))
        {
            return CDRule.RULE_LEVEL_UNI_OFD;
        }
        
        else if(data.contains(RULE_MAP_STR))
        {
            return CDRule.RULE_LEVEL_MAP;
        }
        else if(data.contains(RULE_DSL_STR))
        {
            return CDRule.RULE_LEVEL_DSL;
        }
        else if(data.contains(RULE_IS_STR))
        {
            return CDRule.RULE_LEVEL_IS;
        }
        else if(data.contains(RULE_PON_STR))
        {
            return CDRule.RULE_LEVEL_PON;
        }
        else if(data.contains(RULE_MSTPCOMMON_STR))
        {
            return CDRule.RULE_LEVEL_MSTPCOMMON;
        }
        else if(data.contains(RULE_AG_STR))
        {
            return CDRule.RULE_LEVEL_AG;
        }
        else if(data.contains(RULE_EOC_STR))
        {
            return CDRule.RULE_LEVEL_EOC;
        }
        else if(data.contains(RULE_A10_STR))
        {
            return CDRule.RULE_LEVEL_A10;
        }
        
        else
        {
            return CDRule.RULE_LEVEL_UNDEFINED;
        }
    }
}
