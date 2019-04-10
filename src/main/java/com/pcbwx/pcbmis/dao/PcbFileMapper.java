package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbFile;

/**
 * @author jisx
 */
public interface PcbFileMapper  extends BaseMapper<PcbFile> {

    /**
     * 批量插入
     * @param list
     */
    int insertBatch(@Param("list") List<PcbFile> list);

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    int deleteOnStatus(@Param("fileId") Integer fileId);

    /**
     * 查找批量文件
     * @param fileIds
     * @return
     */
    List<PcbFile> selectBatch(@Param("list") List<Integer> fileIds);
}