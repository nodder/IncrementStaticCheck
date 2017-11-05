package cdd.zte.checktools.localchecktool.export.oper;

import cdd.zte.checktools.common.info.ChangedPathInfo;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ExportOperUnsupport extends ExportOperation
{
    private String errorMessage;

    public ExportOperUnsupport(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public void doExport(ChangedPathInfo[] changedPathInfoList, String exportToBasePath, String responsitoryPath) throws Exception
    {
        throw new Exception(errorMessage);
    }

    @Override
    public int getActualOperRevision(int revision)
    {
        return -1;
    }
}
