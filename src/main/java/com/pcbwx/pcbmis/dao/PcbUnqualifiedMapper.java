package com.pcbwx.pcbmis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;
import com.pcbwx.pcbmis.model.PcbUnqualified;

/**
 * @author jisx
 */
public interface PcbUnqualifiedMapper extends BaseMapper<PcbUnqualified>{
    /**
     * 根据流水号获取详情
     * @param serialNumber 流水号
     * @return
     */
    PcbUnqualified selectBySerialNumber(@Param("serialNumber") String serialNumber);

    /**
     * 根据检验单号查询不合格品记录
     * @param checkNumber
     * @return
     */
    PcbUnqualified selectByCheckNumber(@Param("checkNumber") String checkNumber);

    /**
     * 获取分页具体数据
     * @param unqualifiedQueryBean
     * @param startIndex
     * @param size
     * @return
     */
    List<Map<String, Object>> selectListByQuery(@Param("unqualifiedQueryBean") UnqualifiedQueryBean unqualifiedQueryBean, @Param("startIndex") Integer startIndex, @Param("size") Integer size);

    /**
     * 获取总长度
     * @param unqualifiedQueryBean
     * @return
     */
    int selectListTotalByQuery(@Param("unqualifiedQueryBean")UnqualifiedQueryBean unqualifiedQueryBean);

    /**
     * 根据不合格品处置单号和状态，查询字段，为了导出不合格品处置单excel做数据准备
     * @param unqualifiedId
     * @param status
     * @return
     */
    Map<String, Object> selectByUnqualifiedIdAndStatus(@Param("unqualifiedId") int unqualifiedId, @Param("status") int status);
    
    /**
     * 按检验单号集合获取不合格品集合
     * @param checkNums
     * @return
     */
    List<PcbUnqualified> selectByCheckNums(@Param("checkNums") Set<String> checkNums);
    
    /**
     * 更新板名
     * @param boardName
     * @param orderNumber
     * @return
     */
    Integer updateBoardNameByOrderNumber(@Param("boardName") String boardName, @Param("orderNumber") String orderNumber);

    /**
     * 按检验单号查找不可用的1个
     * @param checkNum    检验单号
     * @return
     */
    PcbUnqualified selectByCheckNumUnable(@Param("checkNum") String checkNum);
}