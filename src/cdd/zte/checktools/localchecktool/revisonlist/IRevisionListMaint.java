package cdd.zte.checktools.localchecktool.revisonlist;

/**
 * @description 
 * @see    
 * @author      ChenDuoduo
 * @since       1.0
 * @date        
 */
public interface IRevisionListMaint
{

    /**
     * 获取下一个待检查的版本号。如果不存在下一个版本号，则返回-1。
     */
    int nextRevision();

}
