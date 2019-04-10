package com.pcbwx.pcbmis.service.impl;

import com.pcbwx.pcbmis.dao.PcbFileMapper;
import com.pcbwx.pcbmis.dao.PcbRelationFileMapper;
import com.pcbwx.pcbmis.model.PcbFile;
import com.pcbwx.pcbmis.model.PcbRelationFile;
import com.pcbwx.pcbmis.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jisx
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private PcbFileMapper fileMapper;
    @Autowired
    private PcbRelationFileMapper pcbRelationFileMapper;

    @Override
    public boolean saveImages(List<PcbFile> list) {

        return fileMapper.insertBatch(list) > 0;
    }

    @Override
    public PcbFile selectByImageId(Integer imageId) {
        return fileMapper.selectByPrimaryKey(imageId);
    }

    @Override
    public boolean deleteFileId(Integer fileId) {
        PcbFile pcbFile = fileMapper.selectByPrimaryKey(fileId);
        if (pcbFile == null || pcbFile.getId() == null) {
            return false;
        }
        pcbFile.setStatus(0);
        return fileMapper.updateByPrimaryKey(pcbFile) > 0;
    }

    @Override
    public boolean saveFiles(int unqualifiedId, List<PcbFile> list) {

        List<PcbRelationFile> relationFiles = new ArrayList<>();
        PcbRelationFile pcbRelationFile;
        for (PcbFile pcbFile : list) {
            pcbRelationFile = new PcbRelationFile();
            pcbRelationFile.setUnqualifiedId(unqualifiedId);
            pcbRelationFile.setFileId(pcbFile.getId());
            pcbRelationFile.setCreateTime(new Date());
            pcbRelationFile.setEnable(1);
            relationFiles.add(pcbRelationFile);
        }
        return pcbRelationFileMapper.insertBatch(relationFiles) > 0;
    }
}
