package cdd.zte.checktools.localchecktool.export.oper;


/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ExportCurrForModify extends JavaFileExport
{
    @Override
    public int getActualOperRevision(int revision)
    {
        return revision;
    }
}
