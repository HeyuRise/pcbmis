package com.pcbwx.pcbmis.service;

import java.util.List;
import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;

/**
 * 检验单服务
 * 
 * @author 孙贺宇
 *
 */
public interface CheckService {

	/**
	 * 修改尺寸翘曲度
	 * 
	 * @param checkNum
	 * @param sizeAndWarpDegree
	 * @return
	 */
	Map<String, Object> modifySize(String checkNum, List<SizeAndWarpDegree> sizeAndWarpDegree);

	/**
	 * 上传检验详情
	 * @param wxtbUser
	 * @param checkNum
	 * @param orderNum
	 * @param checkDetail 接收的数据
	 * @param templateId  模板Id
	 * @return
	 */
	ErrorCodeEnum postDetail(AkAuthUser wxtbUser, String checkNum, String orderNum, CheckDetail checkDetail,
			Integer templateId);
	
	/**
	 * 获取检验单，报告单是否可编辑是否
	 * @param type  1 ：检验单  2：报告单
	 * @param orderNum
	 * @return
	 */
	boolean getIsCanEdit(Integer type, String orderNum);
}
