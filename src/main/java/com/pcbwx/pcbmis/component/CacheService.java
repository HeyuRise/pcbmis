package com.pcbwx.pcbmis.component;

import java.util.List;
import java.util.Map;

import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.FormFieldTypeEnum;
import com.pcbwx.pcbmis.model.BasemAterial;
import com.pcbwx.pcbmis.model.BoardPlyTolerance;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.CraftInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.FormDefaultPerson;
import com.pcbwx.pcbmis.model.FrameTolerance;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.Menu;
import com.pcbwx.pcbmis.model.PcbCheckTemplate;
import com.pcbwx.pcbmis.model.PcbReportTemplate;
import com.pcbwx.pcbmis.model.RoleAuth;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.UserAuth;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.UserRoleRelation;
import com.pcbwx.pcbmis.model.WxtbDepartment;
import com.pcbwx.pcbmis.model.WxtbUser;

public interface CacheService {
	void reloadDictionary(List<Dictionary> dictionarys);
	void reloadJoinBoardWay(List<JoinBoardWay> joinBoardWays);
	void reloadEdaGuest(List<EdaGuest> edaGuests);
	void reloadWxtbDepartment(List<WxtbDepartment> wxtbDepartments);
	void reloadFactory(List<FactoryInfo> factories);
	void reloadSurfaceProcess(List<SurfaceProcess> processes);
	void reloadCompany(List<CompanyInfo> companies);
	void reloadBoardPlyTolerance(List<BoardPlyTolerance> records);
	void reloadBasemAterial(List<BasemAterial> records);
	void reloadFrameTolerance(List<FrameTolerance> records);
	void reloadCraftInfo(List<CraftInfo> records);
	void reloadPcbCheckTemplate(List<PcbCheckTemplate> records);
	void reloadPcbReportTemplate(List<PcbReportTemplate> records);
	void reloadFormDefaultPerson(List<FormDefaultPerson> records);
	
	void reloadUserRole(List<UserRole> records);
	void reloadUserRoleRelation(List<UserRoleRelation> records);
	void reloadWxtbUser(List<WxtbUser> records);
	void reloadUserAuth(List<UserAuth> records);
	void reloadRoleAuth(List<RoleAuth> records);
	void reloadMenu(List<Menu> records);
	//--------------------------------------------------
	Dictionary getDictionary(DictionaryEnum type, Integer innerId);
	Dictionary getDictionary(DictionaryEnum type, String innerCode);
	Dictionary getDictionaryByValue(DictionaryEnum type, String value);
	
	List<Dictionary> getDictionarys(DictionaryEnum type);
		
	JoinBoardWay getJoinBoardWay(Integer id);
	EdaGuest getGuest(String key);
	WxtbUser getWxtbUser(String userCode);
	WxtbDepartment getDepartment(String key);
	FactoryInfo getFactory(Integer id);
	SurfaceProcess getSurfaceProcess(Integer id);
	CompanyInfo getCompany(Integer id);
	CompanyInfo getCompany(String code);
	BoardPlyTolerance getBoardPlyTolerance(Integer id);
	CraftInfo getCraftInfo(Integer id);
	PcbCheckTemplate getPcbCheckTemplate(Integer id);
	PcbReportTemplate getPcbReportTemplate(Integer id);
	List<EdaGuest> selectGuestByCondition(String condition);
	FormDefaultPerson getFormDefaultPerson(FormFieldTypeEnum type);
	
	UserAuth getUserAuth(Integer id);
	Map<Integer, FactoryInfo> getFactorys();
	Map<Integer, SurfaceProcess> getSurfaceProcess();
	List<UserRole> getUserRole(String userCode);
	Map<Integer, UserRole> getUserRoles();
	Map<String, WxtbUser> getWxtbUser();
	Map<Integer, RoleAuth> getRoleAuth();
	Map<Integer, UserAuth> getUserAuths();
	Map<Integer, Menu> getMenu();
} 
