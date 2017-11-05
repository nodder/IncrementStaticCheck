package cdd.zte.checktools.localchecktool.export;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import cdd.zte.checktools.common.CommonConst;
import cdd.zte.checktools.common.CommonUtil;
import cdd.zte.checktools.common.info.ChangedPathInfo;
import cdd.zte.checktools.localchecktool.export.oper.ExportCurrForAdd;
import cdd.zte.checktools.localchecktool.export.oper.ExportCurrForDelete;
import cdd.zte.checktools.localchecktool.export.oper.ExportCurrForModify;
import cdd.zte.checktools.localchecktool.export.oper.ExportOldForAdd;
import cdd.zte.checktools.localchecktool.export.oper.ExportOldForDelete;
import cdd.zte.checktools.localchecktool.export.oper.ExportOldForModify;
import cdd.zte.checktools.localchecktool.export.oper.ExportOperation;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public class ExpOperationsDispatch
{
    private static final String UNSUPPORT = "NA";//TODO —È÷§
    
    private static HashMap<String, ExportOperation> typeToOperCurr = initTypeToOperCurr();
    private static HashMap<String, ExportOperation> typeToOperOld = initTypeToOperOld();
    
    private HashMap<String, ArrayList<ChangedPathInfo>> typeToChangedPathInfo = initTypeToChangedPathInfo();

    private String responsitoryPath;
    private String exportToBasePath;
    private int revision;

    private String[] allChangedpathTypes;

    private static HashMap<String, ExportOperation> initTypeToOperCurr()
    {
        HashMap<String, ExportOperation> typeToOper = new HashMap<String, ExportOperation>();
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_MODIFY, new ExportCurrForModify());
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_ADD, new ExportCurrForAdd());
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_DELTE, new ExportCurrForDelete());
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_REPLACING, new ExportCurrForAdd());
        
        return typeToOper;
    }
    
    private static HashMap<String, ExportOperation> initTypeToOperOld()
    {
        HashMap<String, ExportOperation> typeToOper = new HashMap<String, ExportOperation>();
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_MODIFY, new ExportOldForModify());
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_ADD, new ExportOldForAdd());
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_DELTE, new ExportOldForDelete());
        typeToOper.put(ChangedPathInfo.CHANGE_TYPE_REPLACING, new ExportOldForAdd());
        return typeToOper;
    }
    
    private HashMap<String, ArrayList<ChangedPathInfo>> initTypeToChangedPathInfo()
    {
        HashMap<String, ArrayList<ChangedPathInfo>> typeToChangedPathInfo = new HashMap<String, ArrayList<ChangedPathInfo>>();
        
        typeToChangedPathInfo.put(ChangedPathInfo.CHANGE_TYPE_ADD, new ArrayList<ChangedPathInfo>());
        typeToChangedPathInfo.put(ChangedPathInfo.CHANGE_TYPE_MODIFY, new ArrayList<ChangedPathInfo>());
        typeToChangedPathInfo.put(ChangedPathInfo.CHANGE_TYPE_DELTE, new ArrayList<ChangedPathInfo>());
        typeToChangedPathInfo.put(ChangedPathInfo.CHANGE_TYPE_REPLACING, new ArrayList<ChangedPathInfo>());
        typeToChangedPathInfo.put(UNSUPPORT, new ArrayList<ChangedPathInfo>());
        
        return typeToChangedPathInfo;
    }
    
    public ExpOperationsDispatch(int revision, String exportToBasePath, String responsitoryPath)
    {
        this.revision = revision;
        this.exportToBasePath = new File(exportToBasePath).getAbsolutePath();
        this.responsitoryPath = responsitoryPath;
    }

    public void prepareData(ArrayList<ChangedPathInfo> changedPathList)
    {
        initTypeToChangedPathInfo();
        
        ChangedPathInfo[] filterdChangedPathList = new ChangedPathInfoListFilter().filter(changedPathList);
        
        dispatchChangedPathInfo(filterdChangedPathList);
        
        this.allChangedpathTypes = getChangedPathTypes();
    }
    
    public String exportForCurrentRevision(String[] toBeExcludedClassPattern) throws Exception
    {
        String exportPath = exportToBasePath + CommonConst.TEMPDIR_EXPORT_CURR;
        CommonUtil.emptyPath(exportPath);
        
        for(String changedPathType : allChangedpathTypes)
        {
            ArrayList<ChangedPathInfo> changedPathInfoList = typeToChangedPathInfo.get(changedPathType);
            
            if(!changedPathInfoList.isEmpty())
            {
                ExportOperation expOperForCurr = typeToOperCurr.get(changedPathType);

                expOperForCurr.doExport(changedPathInfoList.toArray(new ChangedPathInfo[changedPathInfoList.size()]), revision, exportPath,
                    responsitoryPath, toBeExcludedClassPattern);
            }
        }
        
        return exportPath;
    }
    
    
    public String exportForOldRevision(String[] toBeExcludedClassPattern) throws Exception
    {
        String exportPath = exportToBasePath + CommonConst.TEMPDIR_EXPORT_OLD;
        CommonUtil.emptyPath(exportPath);
        
        for(String changedPathType : allChangedpathTypes)
        {
            ArrayList<ChangedPathInfo> changedPathInfoList = typeToChangedPathInfo.get(changedPathType);
            
            if(!changedPathInfoList.isEmpty())
            {
                ExportOperation expOperForOld = typeToOperOld.get(changedPathType);

                expOperForOld.doExport(changedPathInfoList.toArray(new ChangedPathInfo[changedPathInfoList.size()]), revision,
                    exportToBasePath + CommonConst.TEMPDIR_EXPORT_OLD, responsitoryPath, toBeExcludedClassPattern);
            }
        }
        
        return exportPath;
    }
    
    private String[] getChangedPathTypes()
    {
        ArrayList<String> changedPathTypes = new ArrayList<String>();
        
        Iterator<Entry<String, ArrayList<ChangedPathInfo>>> it = typeToChangedPathInfo.entrySet().iterator();
        while(it.hasNext())
        {
            Entry<String, ArrayList<ChangedPathInfo>> entry = it.next();
            changedPathTypes.add(entry.getKey());
        }
        
        return changedPathTypes.toArray(new String[changedPathTypes.size()]);
    }

    private void dispatchChangedPathInfo(ChangedPathInfo[] changedPathList)
    {
        for(ChangedPathInfo changedPath : changedPathList)
        {
            if(typeToChangedPathInfo.containsKey(changedPath.getChangedType()))
            {
                typeToChangedPathInfo.get(changedPath.getChangedType()).add(changedPath);
            }
            else
            {
                typeToChangedPathInfo.get(UNSUPPORT).add(changedPath);
            }
        }
    }
}
