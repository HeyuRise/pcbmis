package com.pcbwx.pcbmis.dao;

import com.pcbwx.pcbmis.model.PcbRelationFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jisx
 */
public interface PcbRelationFileMapper extends BaseMapper<PcbRelationFile> {

    /**
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<PcbRelationFile> list);

    /**
     * 根据不合格品处置单的单号查询关联的文件，返回关系表的字段
     * @param unqualifiedId
     * @param type
     * @return
     */
    List<PcbRelationFile> selectByUnqualifiedId(@Param("unqualifiedId") Integer unqualifiedId, @Param("type") Integer type);

    /**
     * 根据不合格品处置单的单号查询关联的文件，返回附件列表
     * @param unqualifiedId
     * @param type
     * @return
     */
    List<Map<String, Object>> selectByUnqualifiedIdForFileList(@Param("unqualifiedId") Integer unqualifiedId, @Param("type") Integer type);
}