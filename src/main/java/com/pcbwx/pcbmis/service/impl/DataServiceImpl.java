package com.pcbwx.pcbmis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.pcbmis.dao.ProductBasemAterialMapper;
import com.pcbwx.pcbmis.model.ProductBasemAterial;
import com.pcbwx.pcbmis.service.DataService;

/**
 * 数据接口实现类
 * 
 * @author 王海龙
 *
 */
@Service("dataService")
public class DataServiceImpl implements DataService {

//	private static Logger logger = Logger.getLogger(DataServiceImpl.class);
	
	@Autowired
	private ProductBasemAterialMapper productBasemAterialMapper;
	
	DataServiceImpl() {
	}

	@Override
	public boolean recordProductBasemAterials(String productOrderNum, List<Integer> basemAterials) {
		//logger.info("重置pcb工单基材信息.orderNum=" + productOrderNum);
		productBasemAterialMapper.clearByProductOrder(productOrderNum);
		if (basemAterials == null || basemAterials.isEmpty()) {
			return true;
		}
		for (Integer basemAterialId : basemAterials) {
			ProductBasemAterial record = new ProductBasemAterial();
			record.setOrderNum(productOrderNum);
			record.setBasemAterialId(basemAterialId);
			record.setCreateTime(new Date());
			record.setUpdateTime(record.getCreateTime());
			
			productBasemAterialMapper.insertSelective(record);
		}
		return true;
	}
		
}


