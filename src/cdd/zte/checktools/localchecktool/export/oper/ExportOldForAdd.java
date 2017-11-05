package cdd.zte.checktools.localchecktool.export.oper;

import cdd.zte.checktools.common.info.ChangedPathInfo;

public class ExportOldForAdd extends ExportOperation
{
    @Override
    public void doExport(ChangedPathInfo[] changedPathInfoList, String exportToBasePath, String responsitoryPath) throws Exception
    {
        return;
    }

    @Override
    public int getActualOperRevision(int revision)
    {
        return revision - 1;
    }

}
