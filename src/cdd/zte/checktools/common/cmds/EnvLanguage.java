package cdd.zte.checktools.common.cmds;

public class EnvLanguage
{
    public static final int LANG_ENG = 0x1000;
    public static final int LANG_CHI = 0x1001;
    
    private static int envLang;
    
    public static void setLanguage(int lang)
    {
        envLang = lang;
    }
    
    public static boolean isEnglish()
    {
        return envLang == LANG_ENG;
    }
}
