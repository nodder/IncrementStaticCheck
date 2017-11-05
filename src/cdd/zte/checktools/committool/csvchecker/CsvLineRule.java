package cdd.zte.checktools.committool.csvchecker;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cdd.zte.checktools.common.LogPrint;

public class CsvLineRule
{
    private static Logger logger = Logger.getLogger(CsvLineRule.class.getName());
    
    private CsvLineRule()
    {
    }

    public static boolean isLineValid(String line)
    {
        if(line.startsWith("#") || line.startsWith("//") || line.trim().equalsIgnoreCase(""))
        {
            return true;
        }
        
        ArrayList<Integer> alCommaIndex = new ArrayList<Integer>();
        ArrayList<Integer> alQuoteIndex = new ArrayList<Integer>();
        
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == ',')
            {
                alCommaIndex.add(new Integer(i));
            }
            else if(line.charAt(i) == '\"')
            {
                alQuoteIndex.add(new Integer(i));
            }
        }
        
        int validCommaCount = alCommaIndex.size();
        for(int i = 0; i < alCommaIndex.size(); i++)
        {
            if(isCommaInQuote(alQuoteIndex, alCommaIndex.get(i)))
            {
                validCommaCount--;
            }
        }
        
        if(validCommaCount != 2)
        {
            LogPrint.logError(logger, "Valid comma count == " + validCommaCount + " in line :" + line);
        }
        
        return validCommaCount == 2;
    }
    
    private static boolean isCommaInQuote(ArrayList<Integer> alQuoteIndex, int commaIndex)
    {
        int quoteParis = alQuoteIndex.size()/2;
    
        for(int i = 0; i < quoteParis; i++)
        {
           if((commaIndex > alQuoteIndex.get(2*i)) 
                 && (commaIndex < alQuoteIndex.get(2*i + 1)))
           {
                return true;
           }
        }
        
        return false;
    }
}
