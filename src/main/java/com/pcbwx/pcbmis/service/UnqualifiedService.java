package com.pcbwx.pcbmis.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.UnqualifiedBean;
import com.pcbwx.pcbmis.bean.UnqualifiedHearBean;
import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.PcbFile;
import com.pcbwx.pcbmis.model.PcbUnqualified;

/**
 * @author jisx
 */
public interface UnqualifiedService {

    /**
     * 保存不合格品处置单
     *
     * @param checkNumber
     *@param unqualifiedNumber 不合格品数量
     * @param unqualifiedDesc 不合格描述   @return
     */
    PcbUnqualified save(AkAuthUser wxtbUser, String checkNumber, int unqualifiedNumber, String unqualifiedDesc);


    /**
     * 更新详情
     * @param wxtbUser
     * @param unqualifiedBean
     * @return
     */
    ErrorCodeEnum saveDetail(AkAuthUser wxtbUser, UnqualifiedBean unqualifiedBean);

    /**
     * 获取详情
     * @param wxtbUser
     * @param serialNumber
     * @return
     */
    Map<String, Object> getDetail(AkAuthUser wxtbUser, String serialNumber,String checkNumber);

    /**
     * 获取不合格品处置单列表
     * @see UnqualifiedService#getTotalList(UnqualifiedQueryBean) 分页的操作分两个部分，一个是获取总的长度
     * @see UnqualifiedService#getList(UnqualifiedQueryBean, int, int)  获取具体的分页数据
     * @param unqualifiedQueryBean
     * @param page
     *@param rows
     * @return
     */
    List<Map<String, Object>> getList(UnqualifiedQueryBean unqualifiedQueryBean, int page, int rows);

    /**
     * 获取不合格品处置单列表-总长度
     * @param unqualifiedQueryBean
     * @return
     */
    int getTotalList(UnqualifiedQueryBean unqualifiedQueryBean);
    /**
     *
     * 保存审理结果
     * @param unqualifiedHearBean
     * @return
     */
    boolean saveHear(UnqualifiedHearBean unqualifiedHearBean, AkAuthUser wxtbUser);

    /**
     * 获取不合格品处置单的文件列表
     * @param unqualifiedId
     * @return
     */
    List<Map<String, Object>> getHearFile(int unqualifiedId);

    /**
     * 获取详情，为了导出处置单
     * @param unqualifiedId
     * @param status
     * @return
     */
    Map<String,Object> getDetailForExport(int unqualifiedId, int status);

    /**
     * 获取处置单中的图片
     * @param unqualifiedId
     * @return
     */
    List<PcbFile> getImagesByUnqualifiedId(int unqualifiedId);
    
    /**
     * 导出
     * @param workbook
     * @param sheet
     * @param map
     * @param pcbFiles
     * @param status
     */
    void createExcelUnqualified(Workbook workbook, Sheet sheet, Map<String, Object> map, List<PcbFile> pcbFiles,
			int status);
}

