package com.pcbwx.pcbmis.control;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbInStorageOrderMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.ProductOrder;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private JoinBoardOrderMapper joinBoardOrderMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@ResponseBody
	public void operateProduct(@RequestBody ProductOrder productOrder) {
		ProductOrder productOrder2 = productOrderMapper.selectByPrimaryKey(productOrder.getId());

		pcbCheckReportMapper.updatePcbBoardName(productOrder.getProductionNumPcs(), productOrder.getOrderNum(),
				productOrder.getBoardName());
		if (!StringUtils.equals(productOrder2.getBoardName(), productOrder.getBoardName())) {
			pcbInStorageOrderMapper.updatePcbBoardName(productOrder.getOrderNum(), productOrder.getBoardName());
			pcbCheckOrderMapper.updatePcbBoardName(productOrder.getOrderNum(), productOrder.getBoardName());

		}

	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public void operateJoinBoard(@RequestBody JoinBoardOrder joinBoardOrder) {
		JoinBoardOrder joinBoardOrder2 = joinBoardOrderMapper.selectByPrimaryKey(joinBoardOrder.getId());
		if (!StringUtils.equals(joinBoardOrder2.getJoinBoardName(), joinBoardOrder.getJoinBoardName())) {
			pcbInStorageOrderMapper.updateJoinBoardName(joinBoardOrder.getJoinBoardCode(),
					joinBoardOrder.getJoinBoardName());
			pcbCheckOrderMapper.updateJoinBoardName(joinBoardOrder.getJoinBoardCode(),
					joinBoardOrder.getJoinBoardName());
			pcbCheckReportMapper.updateJoinBoardName(joinBoardOrder.getJoinBoardCode(),
					joinBoardOrder.getJoinBoardName());
		}
	}

//	@RequestMapping(value = "/join/{size}", method = RequestMethod.GET)
//	@ResponseBody
//	public void test(@PathVariable Integer size) {
//		colService.generateSizeWarping(new BigDecimal("159"), new BigDecimal("104"), new BigDecimal("2.0"), size, 2, 2);
//	}

}
