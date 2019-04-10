package com.pcbwx.pcbmis.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcbwx.pcbmis.bean.CheckInfo;
import com.pcbwx.pcbmis.bean.CommonEnumBean;
import com.pcbwx.pcbmis.bean.CommonStrEnumBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.BoardPlyToleranceMapper;
import com.pcbwx.pcbmis.dao.EdaGuestMapper;
import com.pcbwx.pcbmis.dao.FactoryInfoMapper;
import com.pcbwx.pcbmis.dao.FrameToleranceMapper;
import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.PcbTemplateMapper;
import com.pcbwx.pcbmis.dao.ProductNoteMapper;
import com.pcbwx.pcbmis.enums.CheckStateEnum;
import com.pcbwx.pcbmis.enums.ColOrderEnum;
import com.pcbwx.pcbmis.enums.ColSmtReportEnum;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.DisposalMethodEnum;
import com.pcbwx.pcbmis.enums.ProductTypeEnum;
import com.pcbwx.pcbmis.enums.ReportStateEnum;
import com.pcbwx.pcbmis.enums.SmtOrderEnum;
import com.pcbwx.pcbmis.enums.UnqualifiedSourceEnum;
import com.pcbwx.pcbmis.model.BoardPlyTolerance;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.FrameTolerance;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbTemplate;
import com.pcbwx.pcbmis.model.ProductNote;
import com.pcbwx.pcbmis.model.UserAuth;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.UtilService;
import com.pcbwx.pcbmis.utils.StringUtil;

@Service("utilService")
public class UtilServiceImpl implements UtilService{

	@Autowired
	private FactoryInfoMapper factoryInfoMapper;
	@Autowired
	private FrameToleranceMapper frameToleranceMapper;
	@Autowired
	private BoardPlyToleranceMapper boardPlyToleranceMapper;
	@Autowired
	private ProductNoteMapper productNoteMapper;
	@Autowired
	private JoinBoardOrderMapper joinBoardOrderMapper;
	@Autowired
	private PcbTemplateMapper pcbTemplateMapper;
	
	@Autowired
	private EdaGuestMapper edaGuestMapper;
	@Autowired
	private CacheService cacheService;
	
	@Override
	public JSONArray getRoles(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		Map<Integer, UserRole> userMap = cacheService.getUserRoles();
		if (userMap == null || userMap.size() == 0) {
			return jsonArray;
		}
		for (Integer id : userMap.keySet()) {
			UserRole userRole = userMap.get(id);
			if (userRole.getRoleName().equals("游客") || userRole.getEnable() == 0) {
				continue;
			}
			JSONObject json = new JSONObject();
			json.put("p_id", id);
			json.put("p_text", userMap.get(id).getRoleName());
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	@Override
	public JSONArray getAuths(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		Map<Integer, UserAuth> userauthMap = cacheService.getUserAuths();
		if (userauthMap == null || userauthMap.size() == 0) {
			return null;
		}
		for (Integer authType : userauthMap.keySet()) {
			JSONObject json = new JSONObject();
			json.put("p_id", authType);
			json.put("p_text", userauthMap.get(authType).getAuthName());
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	
	@Override
	public JSONArray getFactoryList() {
		JSONArray jsonArray = new JSONArray();
		List<FactoryInfo> factoryInfos = factoryInfoMapper.load();
		if (factoryInfos == null || factoryInfos.isEmpty()) {
			return jsonArray;
		}
		for (FactoryInfo factoryInfo : factoryInfos) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("p_id", factoryInfo.getInnerId());
			jsonObject.put("p_text", factoryInfo.getFactoryName());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getGrade() {
		JSONArray jsonArray = new JSONArray();
		List<Dictionary> dictionaries = cacheService.getDictionarys(DictionaryEnum.CATEGORY_GRADE);
		if (dictionaries == null || dictionaries.isEmpty()) {
			return jsonArray;
		}
		for (Dictionary dictionary : dictionaries) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("p_id", dictionary.getInnerId());
			jsonObject.put("p_text", dictionary.getValueStr());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public String getFrameTolerance(Integer frameId) {
		if (frameId == null) {
			return "-";
		}
		String boardTolerance = "-";
		List<Integer> innerIds = new ArrayList<Integer>();
		innerIds.add(frameId);
		List<FrameTolerance> frameTolerances = frameToleranceMapper
				.listByInnerIds(innerIds);
		if (frameTolerances != null && !frameTolerances.isEmpty()) {
			FrameTolerance frameTolerance = frameTolerances.get(0);
			boardTolerance = frameTolerance.getFtName();
		}
		return boardTolerance;
	}

	@Override
	public String getBoardPlyTolerance(Integer boardPlyId) {
		if (boardPlyId == null) {
			return "-";
		}
		String boardTolerance = "-";
		List<Integer> plyIds = new ArrayList<Integer>();
		plyIds.add(boardPlyId);
		List<BoardPlyTolerance> boardPlyTolerances = boardPlyToleranceMapper.listByInnerIds(plyIds);
		if (boardPlyTolerances != null && boardPlyTolerances.size() != 0) {
			BoardPlyTolerance boardPlyTolerance = boardPlyTolerances.get(0);
			boardTolerance = boardPlyTolerance.getToleranceName();
		}
		return boardTolerance;
	}

	@Override
	public JSONArray getCheckContent() {
		JSONArray jsonArray = new JSONArray();
		List<Dictionary> dictionaries = cacheService.getDictionarys(DictionaryEnum.CHECK_CONTENT);
		if (dictionaries == null || dictionaries.isEmpty()) {
			return jsonArray;
		}
		for (Dictionary dictionary : dictionaries) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("p_id", dictionary.getInnerId());
			jsonObject.put("p_text", dictionary.getValueStr());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getCheckStateList() {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (CheckStateEnum checkStateEnum : CheckStateEnum.values()) {
			if (checkStateEnum.getCode() == 0) {
				continue;
			}
			jsonObject = new JSONObject();
			jsonObject.put("p_id", checkStateEnum.getCode());
			jsonObject.put("p_text", checkStateEnum.getDescr());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getReportStateList() {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (ReportStateEnum reportStateEnum : ReportStateEnum.values()) {
			if (reportStateEnum.getCode() == 0) {
				continue;
			}
			jsonObject = new JSONObject();
			jsonObject.put("p_id", reportStateEnum.getCode());
			jsonObject.put("p_text", reportStateEnum.getDescr());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public List<String> getProductNotes(String orderNum, Integer type) {
		List<String> productNotes = new ArrayList<String>();
		List<ProductNote> productNoteList = productNoteMapper.selectByOrderNumAndType(orderNum, type);
		if (productNoteList == null || productNoteList.isEmpty()) {
			return productNotes;
		}
		for (ProductNote productNote : productNoteList) {
			productNotes.add(productNote.getProductNote());
		}
		return productNotes;
	}
	
	@Override
	public List<String> getEdaGuest(String guest) {
		List<String> edaGuestCode = new ArrayList<String>();
		if (StringUtil.isEmpty(guest)) {
			return null;
		}
		List<EdaGuest> edaGuests = edaGuestMapper.selectByShortName(guest);
		if (!(edaGuests == null || edaGuests.isEmpty())) {
			for (EdaGuest edaGuest : edaGuests) {
				edaGuestCode.add(edaGuest.getGuestCode());
			}
		}
		if (edaGuestCode.isEmpty()) {
			edaGuestCode.add("odmjuwnxdhjswnkcsjsdnfk");
		}
		return edaGuestCode;
	}

	@Override
	public List<CommonEnumBean>  getDisposalMethodList() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (DisposalMethodEnum disposalMethodEnum : DisposalMethodEnum.values()) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(disposalMethodEnum.getIndex());
			commonEnumBean.setDesc(disposalMethodEnum.getDesc());
			commonEnumBeans.add(commonEnumBean);
		}

		return commonEnumBeans;
	}

	@Override
	public List<CommonEnumBean> getProductTypeList() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(productTypeEnum.getIndex());
			commonEnumBean.setDesc(productTypeEnum.getDesc());
			commonEnumBeans.add(commonEnumBean);
		}

		return commonEnumBeans;
	}

	@Override
	public List<CommonEnumBean> getUnqualifiedSourceList() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (UnqualifiedSourceEnum sourceEnum : UnqualifiedSourceEnum.values()) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(sourceEnum.getIndex());
			commonEnumBean.setDesc(sourceEnum.getDesc());
			commonEnumBeans.add(commonEnumBean);
		}

		return commonEnumBeans;
	}

	@Override
	public List<CommonEnumBean> getProductLevelList() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (Dictionary dictionary : cacheService.getDictionarys(DictionaryEnum.CATEGORY_GRADE)) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(dictionary.getInnerId());
			commonEnumBean.setDesc(dictionary.getValueStr());
			commonEnumBeans.add(commonEnumBean);
		}

		return commonEnumBeans;
	}
	
	@Override
	public List<CheckInfo> addAll(List<PcbCheckOrder> pcbCheckOrders, List<CheckInfo> checkInfos) {
		List<String> joinOrderCodes = new ArrayList<String>();
		Map<String, PcbCheckOrder> pcbMap = new HashMap<String, PcbCheckOrder>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			pcbMap.put(pcbCheckOrder.getCheckNum(), pcbCheckOrder);
			String orderType = pcbCheckOrder.getOrderType();
			if (orderType != null) {
				if (orderType.equals("JoinBoardOrder")) {
					joinOrderCodes.add(pcbCheckOrder
							.getJoinBoardOrderCode());
				}
			}
		}
		if (joinOrderCodes.size() == 0) {
			joinOrderCodes = null;
		}
		List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper
				.listByCodes(joinOrderCodes);
		Map<String, JoinBoardOrder> joinMap = new HashMap<String, JoinBoardOrder>();
		if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
			for (JoinBoardOrder joinBoardOrder : joinBoardOrders) {
				joinMap.put(joinBoardOrder.getJoinBoardCode(), joinBoardOrder);
			}
		}
		for (CheckInfo checkInfo : checkInfos) {
			PcbCheckOrder pcbCheckOrder = pcbMap.get(checkInfo
					.getCheckNum());
			String joinCode = pcbCheckOrder.getJoinBoardOrderCode();
			String orderType = pcbCheckOrder.getOrderType();
			if (orderType != null) {
				if (orderType.equals("JoinBoardOrder")) {
					JoinBoardOrder joinBoardOrder = joinMap.get(joinCode);
					if (joinBoardOrder == null) {
						continue;
					}
					checkInfo.setBoardLong(joinBoardOrder.getJoinBoardLong());
					checkInfo.setBoardWide(joinBoardOrder.getJoinBoardWide());
					checkInfo.setBoardName(joinBoardOrder.getJoinBoardName());
				}
			}
		}
		return checkInfos;
	}

	@Override
	public String getContent(String contentIds) {
		String content = null;
		try {
			List<Integer> contentIdList = null;
			Gson g = new Gson();
			Type type = (Type) new TypeToken<List<Integer>>() {
			}.getType();
			contentIdList = g.fromJson(contentIds, type);
			Dictionary dictionary = null;
			for (Integer integer : contentIdList) {
				dictionary = cacheService.getDictionary(
						DictionaryEnum.CHECK_CONTENT, integer);
				if (content == null) {
					content = dictionary.getValueStr();
				} else {
					content = content + "," + dictionary.getValueStr();
				}
			}
		} catch (Exception e) {
		}
		return content;
	}

	@Override
	public List<CommonEnumBean> getTemplateList() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		List<PcbTemplate> pcbTemplateList = pcbTemplateMapper.loadEnable();
		for (PcbTemplate pcbTemplate : pcbTemplateList) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(pcbTemplate.getId());
			commonEnumBean.setDesc(pcbTemplate.getName());
			commonEnumBeans.add(commonEnumBean);
		}
		return commonEnumBeans;
	}

	@Override
	public List<CommonStrEnumBean> getUserList() {
		Map<String, WxtbUser> userMap = cacheService.getWxtbUser();
		List<CommonStrEnumBean> list = new ArrayList<>();
		CommonStrEnumBean commonStrEnumBean = null;
		for (Map.Entry<String, WxtbUser> entry : userMap.entrySet()) {
			WxtbUser wxtbUser = entry.getValue();
			if (!Objects.equals(wxtbUser.getEnable(), 1)) {
				continue;
			}
			commonStrEnumBean = new CommonStrEnumBean();
			commonStrEnumBean.setIndex(wxtbUser.getUserCode());
			commonStrEnumBean.setDesc(wxtbUser.getUsername());
			list.add(commonStrEnumBean);
		}
		return list;
	}



	@Override
	public List<CommonEnumBean> getReportStatus() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (ColSmtReportEnum sourceEnum : ColSmtReportEnum.values()) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(sourceEnum.getCode());
			commonEnumBean.setDesc(sourceEnum.getDescr());
			commonEnumBeans.add(commonEnumBean);
		}
		return commonEnumBeans;
	}

	@Override
	public List<CommonEnumBean> getSMTOrderStatus() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (SmtOrderEnum sourceEnum : SmtOrderEnum.values()) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(sourceEnum.getCode());
			commonEnumBean.setDesc(sourceEnum.getDescr());
			commonEnumBeans.add(commonEnumBean);
		}
		return commonEnumBeans;
	}

	@Override
	public List<CommonEnumBean> getCOLOrderStatus() {
		List<CommonEnumBean> commonEnumBeans = new ArrayList<>();
		CommonEnumBean commonEnumBean;
		for (ColOrderEnum sourceEnum : ColOrderEnum.values()) {
			commonEnumBean = new CommonEnumBean();
			commonEnumBean.setIndex(sourceEnum.getCode());
			commonEnumBean.setDesc(sourceEnum.getDescr());
			commonEnumBeans.add(commonEnumBean);
		}
		return commonEnumBeans;
	}

}
