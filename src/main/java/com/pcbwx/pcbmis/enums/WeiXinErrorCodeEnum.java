package com.pcbwx.pcbmis.enums;

/**
 * 角色枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum WeiXinErrorCodeEnum {

	SYSTEM_BUSY(-1, "系统繁忙，此时请开发者稍候再试"),
	SUCCESS(0, "请求成功"),
	APPSECRET_ERROR(40001, "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口"),
	TOKEN_ERROR(40002, "不合法的凭证类型"),
	OPENID_ERROR(40003, "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID"),
	MEDIA_FILE_ERROR(40004, "不合法的媒体文件类型"),
	FILE_TYPE_ERROR(40005, "不合法的文件类型"),
	FILE_SIZE_ERROR(40006, "不合法的文件大小"),
	MEDIA_FILEID_ERROR(40007, "不合法的媒体文件id"),
	MSG_TYPE_ERROR(40008, "不合法的消息类型"),
	PIC_FILE_SIZE(40009, "不合法的图片文件大小"),
	VOICE_FILE_SIZE(40010, "不合法的语音文件大小"),
	VIDEO_FILE_SIZE(40011, "不合法的视频文件大小"),
	SMALL_PIC_FILE_SIZE(40012, "不合法的缩略图文件大小"),
	APPID_ERROR(40013, "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写"),
	ACCESS_TOKEN_ERROR(40014, "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),
	MENU_TYPE_ERROR(40015, "不合法的菜单类型"),
	BUTTON_NUM_ERROR(40016, "不合法的按钮个数"),
	BUTTON_NUM_ERROR2(40017, "不合法的按钮个数"),
	BUTTON_NAME_LEN_ERROR(40018, "不合法的按钮名字长度"),
	BUTTON_KEY_LEN_ERROR(40019, "不合法的按钮KEY长度"),
	BUTTON_URL_LEN_ERROR(40020, "不合法的按钮URL长度"),
	MENU_VER_ERROR(40021, "不合法的菜单版本号"),
	SUBMENU_GRADE_ERROR(40022, "不合法的子菜单级数"),
	SUBMENU_BUTTON_NUM_ERROR(40023, "不合法的子菜单按钮个数"),
	SUBMENU_BUTTON_TYPE_ERROR(40024, "不合法的子菜单按钮类型"),
	SUBMENU_BUTTON_NAME_LEN_ERROR(40025, "不合法的子菜单按钮名字长度"),
	SUBMENU_BUTTON_KEY_LEN_ERROR(40026, "不合法的子菜单按钮KEY长度"),
	SUBMENU_BUTTON_URL_LEN_ERROR(40027, "不合法的子菜单按钮URL长度"),
	CUSTEM_MENU_USER_ERROR(40028, "不合法的自定义菜单使用用户"),
	OAUTH_CODE_ERROR(40029, "不合法的oauth_code"),
	REFRESH_TOKEN_ERROR(40030, "不合法的refresh_token"),
	OPENID_LIST_ERROR(40031, "不合法的openid列表"),
	OPENID_LIST_LEN_ERROR(40032, "不合法的openid列表长度"),
	REQ_CHAR_ERROR(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符"),
	PARAM_ERROR(40035, "不合法的参数"),
	REQ_FORMAT_ERROR(40038, "不合法的请求格式"),
	URL_LEN_ERROR(40039, "不合法的URL长度"),
	GROUP_ID_ERROR(40050, "不合法的分组id"),
	GROUP_NAME_ERROR(40051, "分组名字不合法"),
	GROUP_NAME_ERROR2(40117, "分组名字不合法"),
	MEDIAID_LEN_ERROR(40118, "media_id大小不合法"),
	BUTTON_TYPE_ERROR(40119, "button类型错误"),
	BUTTON_TYPE_ERROR2(40120, "button类型错误"),
	MEDIA_ERROR(40121, "不合法的media_id类型"),
	WEIXIN_CODE_ERROR(40132, "微信号不合法"),
	UNKNOW_PIC_FORMAT(40137, "不支持的图片格式"),
	NO_ACCESS_TOKEN(41001, "缺少access_token参数"),
	NO_APPID(41002, "缺少appid参数"),
	NO_REFRESH_TOKEN(41003, "缺少refresh_token参数"),
	NO_SECRET_CODE(41004, "缺少secret参数"),
	NO_MEDIA_FILE_DATA(41005, "缺少多媒体文件数据"),
	NO_MEDIA_ID(41006, "缺少media_id参数"),
	NO_SUBMENU(41007, "缺少子菜单数据"),
	NO_OAUTH_CODE(41008, "缺少oauth code"),
	NO_OPENID(41009, "缺少openid"),
	ACCESS_TOKEN_TIMEOUT(42001, "access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明"),
	REFRESH_TOKEN_TIMEOUT(42002, "refresh_token超时"),
	OAUTH_CODE_TIMEOUT(42003, "oauth_code超时"),
	ACCESSTOKEN_TIMEOUT(42007, "用户修改微信密码，accesstoken和refreshtoken失效，需要重新授权"),
	MUST_GET_METHOD(43001, "需要GET请求"),
	MUST_POST_METHOD(43002, "需要POST请求"),
	MUST_HTTPS(43003, "需要HTTPS请求"),
	NEED_ATTENTION(43004, "需要接收者关注"),
	MUST_FRIEND(43005, "需要好友关系"),
	MULTIMDIA_ISEMPTY(44001, "多媒体文件为空"),
	POST_DATA_ISEMPTY(44002, "POST的数据包为空"),
	PICMSG_CONTENT_ISEMPTY(44003, "图文消息内容为空"),
	TESTMSG_CONTENT_ISEMPTY(44004, "文本消息内容为空"),
	MULTIFILE_SIZE_TOOBIG(45001, "多媒体文件大小超过限制"),
	MSG_CONTENT_OVERSTEP(45002, "消息内容超过限制"),
	TITLE_FIELD_OVERSTEP(45003, "标题字段超过限制"),
	DESC_FIELD_OVERSTEP(45004, "描述字段超过限制"),
	LINK_FIELD_OVERSTEP(45005, "链接字段超过限制"),
	PIC_LINK_FIELD_OVERSTEP(45006, "图片链接字段超过限制"),
	VOICE_PLAY_TIME_OVERSTEP(45007, "语音播放时间超过限制"),
	PICMSG_OVERSTEP(45008, "图文消息超过限制"),
	API_CALL_OVERSTEP(45009, "接口调用超过限制"),
	MENU_NUM_OVERSTEP(45010, "创建菜单个数超过限制"),
	RESP_TIME_OVERSTEP(45015, "回复时间超过限制"),
	SYSTEM_GROUP_NOT_MODIFY(45016, "系统分组，不允许修改"),
	GROUP_NAME_LEN_OVERSTEP(45017, "分组名字过长"),
	GROUP_NUM_OVERSTEP(45018, "分组数量超过上限"),
	KF_SEND_NUM_OVERSTEP(45047, "客服接口下行条数超过上限"),
	UNKNOW_MEDIA_DATA(46001, "不存在媒体数据"),
	UNKNOW_MENU_VER(46002, "不存在的菜单版本"),
	UNKNOW_MENU_DATA(46003, "不存在的菜单数据"),
	NO_EXIST_USER(46004, "不存在的用户"),
	PARSE_JSON_ERROR(47001, "解析JSON/XML内容错误"),
	API_NOT_AUTH(48001, "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限"),
	API_IS_FORBID(48004, "api接口被封禁，请登录mp.weixin.qq.com查看详情"),
	NOT_AUTH_API(50001, "用户未授权该api"),
	USER_LIMITED(50002, "用户受限，可能是违规后接口被封禁"),
	INVALID_PARAM(61451, "参数错误(invalid parameter)"),
	INVALID_KF_ACCOUNT(61452, "无效客服账号(invalid kf_account)"),
	KF_ACCOUNT_EXSITED(61453, "客服帐号已存在(kf_account exsited)"),
	KF_ACCOUNT_NAME_OVERSTEP(61454, "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)"),
	KF_ACCOUNT_NAME_INVALID(61455, "客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)"),
	KF_ACOUNT_NUM_OVERSTEP(61456, "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)"),
	HEAD_PIC_FILE_INVALID(61457, "无效头像文件类型(invalid file type)"),
	SYSTEM_ERROR(61450, "系统错误(system error)"),
	DATE_FORMAT_ERROR(61500, "日期格式错误"),
	NO_MENU_ID(65301, "不存在此menuid对应的个性化菜单"),
	NO_THE_USER(65302, "没有相应的用户"),
	NO_DEF_EMNU(65303, "没有默认菜单，不能创建个性化菜单"),
	MATCHRULE_ISEMPTY(65304, "MatchRule信息为空"),
	CUSTOM_MENU_NUM_LIMITED(65305, "个性化菜单数量受限"),
	NOT_CUSTOM_MENU(65306, "不支持个性化菜单的帐号"),
	CUSTOM_MENU_ISEMPTY(65307, "个性化菜单信息为空"),
	BUTTON_IS_NOT_ACK(65308, "包含没有响应类型的button"),
	CUSTOM_MENU_IS_CLOSING(65309, "个性化菜单开关处于关闭状态"),
	COUNTRY_NOT_EMPTY(65310, "填写了省份或城市信息，国家信息不能为空"),
	PROVINCE_NOT_EMPTY(65311, "填写了城市信息，省份信息不能为空"),
	PROVINCE_COUNTRY_INVALID(65312, "不合法的国家信息"),
	PROVINCE_INFO_INVALID(65313, "不合法的省份信息"),
	CITY_INFO_INVALID(65314, "不合法的城市信息"),
	REDIRCT_URL_TO_MANY(65316, "该公众号的菜单设置了过多的域名外跳（最多跳转到3个域名的链接）"),
	URL_INVALID(65317, "不合法的URL"),
	POST_DATA_INVALID(9001001, "POST数据参数不合法"),
	REMOTE_SERVER_UNUSABLE(9001002, "远端服务不可用"),
	TICKET_INVALID(9001003, "Ticket不合法"),
	GET_NEARBY_USERS_FAILD(9001004, "获取摇周边用户信息失败"),
	GET_SHOP_FAIILD(9001005, "获取商户信息失败"),
	GET_OPENID_FAILD(9001006, "获取OpenID失败"),
	NO_UPLOAD_FILE(9001007, "上传文件缺失"),
	RES_FILE_TYPE_ERROR(9001008, "上传素材的文件类型不合法"),
	RES_FILE_SIZE_ERROR(9001009, "上传素材的文件尺寸不合法"),
	UPLOAD_FAILD(9001010, "上传失败"),
	ACCOUNT_ERROR(9001020, "帐号不合法"),
	ACTIVE_RATE_TOO_POOR(9001021, "已有设备激活率低于50%，不能新增设备"),
	DEVICE_APPLY_NUM_ERROR(9001022, "设备申请数不合法，必须为大于0的数字"),
	DEVICE_IS_CHECKING(9001023, "已存在审核中的设备ID申请"),
	QUERY_DEVICE_TOO_MANY(9001024, "一次查询设备ID数量不能超过50"),
	DEVICE_ID_ERROR(9001025, "设备ID不合法"),
	WEB_ID_ERROR(9001026, "页面ID不合法"),
	WEB_PARAM_ERROR(9001027, "页面参数不合法"),
	DEL_WEB_TOO_MANY(9001028, "一次删除页面ID数量不能超过10"),
	WEB_HAS_USED(9001029, "页面已应用在设备中，请先解除应用关系再删除"),
	QUERY_WEB_TOO_MANY(9001030, "一次查询页面ID数量不能超过50"),
	TIME_SECTION_ERROR(9001031, "时间区间不合法"),
	SAVE_DEVICE_BIND_PARAM_ERROR(9001032, "保存设备与页面的绑定关系参数错误"),
	SHOP_ID_ERROR(9001033, "门店ID不合法"),
	DEVICE_DESC_INFO_OVERSTEP(9001034, "设备备注信息过长"),
	DEVICE_REQ_PARAM_ERROR(9001035, "设备申请参数不合法"),
	BEGIN_VALUE_ERROR(9001036, "查询起始值begin不合法");
	
	private int code;
	private String descr;
	
	private WeiXinErrorCodeEnum(int code, String descr) {
		this.code = code;
		this.descr = descr;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
