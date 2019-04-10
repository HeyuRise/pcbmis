package com.pcbwx.pcbmis.service;

import com.pcbwx.pcbmis.model.PcbFile;

import java.util.List;

/**
 * @author jisx
 */
public interface FileService {

    /**
     * 保存图片至数据库
     * @param list
     * @return
     */
    boolean saveImages(List<PcbFile> list);

    /**
     * 查看图片
     * @param imageId
     * @return
     */
    PcbFile selectByImageId(Integer imageId);

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    boolean deleteFileId(Integer fileId);

    /**
     * 保存文件和处置单的关系
     * @param unqualifiedId
     * @param list
     * @return
     */
    boolean saveFiles(int unqualifiedId, List<PcbFile> list);
}
