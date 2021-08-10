package com.yw.demo.common.enums;

/**
 * Created by DELL on 2017/8/4.
 */
public enum ResponseCode {

	//通用返回码
	SUCCESS("100000", "处理成功", true),
	ERROR("100001", "处理失败",false),
	FAILED("200002", "参数不全", false),
	SYSTEM_ERROR("100002", "系统错误",false),

	//业务错误码 - 参数错误
	PARAMS_ERROR("200001", "参数错误"),

	//产品系统业务返回码
	PRODUCT_PRODNO_ERROR("100098","该产品编号不存在",true),
	PRODUCT_VERSION_ERROR("100198","产品版本号异常",true),
	PRODUCT_PRODNO_EXCEPTION("100099","该产品编号不存在",true),
	INSERT_PROD_TYPE_ERROR("101001","新增产品类型失败",false),
	INSERT_PROD_BASIC_ERROR("101101","新增产品基础信息失败",false),
	PROD_TYPE_CODE_ERROR("109999","产品类型错误",false),
	UPDATE_PROD_TYPE_ERROR("102001","修改产品类型失败",false),
	UPDATE_PROD_BASIC_ERROR("102011","修改产品基础信息失败",false),
	UPDATE_PROD_PRICED_ERROR("102101","修改产品定价信息失败",false),
	DELETE_PROD_TYPE_ERROR("103001","删除产品类型失败",false),
	DELETE_PROD_BASIC_ERROR("103101","删除产品基础信息失败",false),
	DELETE_PROD_TYPE_EXIST_BASIC_ERROR("103002","该类型下存在产品基础信息，删除产品类型失败",false),
	SELECT_PROD_INFO_REDIS_ALL("103099","获取产品信息列表失败！",false),

	PROD_RATE_INFO_NOT_EXIST_EXCEPTION("104002","该产品没有对应费率信息"),
	INSERT_RATE_LINE_EXCEPTION("104003","费率行信息入库失败",false),
	UPDATE_RATE_LINE_EXCEPTION("104004","费率行信息修改失败",false),
	DELETE_RATE_LINE_EXCEPTION("104005","费率行信息作废失败",false),
	INSERT_RATE_HEAD_EXCEPTION("104013","费率头信息入库失败",false),
	UPDATE_RATE_HEAD_EXCEPTION("104014","费率头信息修改失败",false),
	DELETE_RATE_HEAD_EXCEPTION("104015","费率头信息作废失败",false),

	REGISTER_TEMPLATE_GROUP_ERROR("109099","合同模版组注册失败",false),
	BINDING_PROD_TEMPLATE_GROUP_ERROR("109001","合同模版组与产品绑定失败",false),
	BINDING_PROD_TEMPLATE_GROUP_NO_ERROR("109002","合同模版组编号不存在",false),
	BINDING_PROD_TEMPLATE_GROUP_SUCCESS("109003","合同模版组与产品绑定成功",true),
	DELETE_PROD_TEMPLATE_GROUP_ERROR("109011","合同模版组与产品关系解绑失败",false),
	DELETE_PROD_TEMPLATE_GROUP_SUCCESS("109012","合同模版组与产品关系解绑成功",true),

	//产品系统业务返回码
	INSERT_PRODUCT_BASIC_EXCEPTION("106101","新增产品基础信息失败",false),
	INSERT_PRODUCT_BASIC_SUCCESS("106100","新增产品基础信息成功",true),
	INSERT_PRODUCT_CREDIT_EXCEPTION("106201","新增产品授信信息失败",false),
	INSERT_PRODUCT_CREDIT_SUCCESS("106200","新增产品授信信息成功",true),
	INSERT_PRODUCT_LOAN_EXCEPTION("106301","新增产品用信信息失败",false),
	INSERT_PRODUCT_LOAN_SUCCESS("106300","新增产品用信信息成功",true),
	INSERT_PRODUCT_ASSURE_EXCEPTION("106401","新增产品担保信息失败",false),
	INSERT_PRODUCT_ASSURE_SUCCESS("106400","新增产品担保信息成功",true),
	SAVE_PRODUCT_ASSURE_EXCEPTION("106491","保存产品担保信息失败",false),
	SAVE_PRODUCT_ASSURE_SUCCESS("106490","保存产品担保信息成功",true),
	PRODUCT_ASSURE_PARAMTER_EXCEPTION("106499","产品担保信息参数异常",false),
	UPDATE_PRODUCT_BASIC_INFO_SUCCESS("107100","更新产品基础信息成功",true),
	UPDATE_PRODUCT_BASIC_EXCEPTION("107101","修改产品基础信息失败",false),
	UPDATE_PRODUCT_CREDIT_EXCEPTION("107201","修改产品授信信息失败",false),
	UPDATE_PRODUCT_CREDIT_SUCCESS("107200","修改产品授信信息成功",true),
	SELECT_PRODUCT_CREDIT_EXCEPTION("107291","修改产品授信信息失败",false),
	SELECT_PRODUCT_CREDIT_SUCCESS("107290","修改产品授信信息成功",true),
	UPDATE_PRODUCT_LOAN_EXCEPTION("107301","修改产品用信信息失败",false),
	UPDATE_PRODUCT_LOAN_SUCCESS("107300","修改产品用信信息成功",true),
	UPDATE_PRODUCT_ASSURE_EXCEPTION("107401","修改产品担保信息失败",false),
	UPDATE_PRODUCT_ASSURE_SUCCESS("107400","修改产品担保信息成功",true),
	PRODUCT_STATUS_UNUSE_CREDIT("107510","还有贷款未使用",false),

	INSERT_PRODUCT_FLOW_EXCEPTION("106501","新增产品业务流程信息失败",false),
	INSERT_PRODUCT_FLOW_SUCCESS("106500","新增产品业务流程信息成功",true),
	SAVE_PRODUCT_FLOW_EXCEPTION("106591","保存产品业务流程信息失败",false),
	SAVE_PRODUCT_FLOW_SUCCESS("106590","保存产品业务流程信息成功",true),
	PRODUCT_FLOW_PARAMTER_EXCEPTION("106599","产品业务流程信息参数异常",false),
	DELETE_PRODUCT_FLOW_EXCEPTION("107501","删除产品业务流程信息失败",false),
	DELETE_FORM_EXCEPTION("107502","该表单在使用中，请解除关联后再删除",false),
	DELETE_FORM_PARAMS_EXIST_EXCEPTION("107503","不能添加相同表单字段",false),
	DELETE_PRODUCT_FLOW_SUCCESS("107500","删除产品业务流程信息成功",true),

	INSERT_PRODUCT_CONSIGNEE_EXCEPTION("106601","新增产品收件标准信息失败",false),
	INSERT_PRODUCT_CONSIGNEE_SUCCESS("106600","新增产品收件标准信息成功",true),
	INSERT_PRODUCT_CONSIGNEE_REPETITION("106602","新增产品收件标准信息重复",false),
	UPDATE_PRODUCT_CONSIGNEE_EXCEPTION("106901","更新产品收件标准信息失败",false),
	UPDATE_PRODUCT_CONSIGNEE_SUCCESS("106900","更新产品收件标准信息成功",true),
	PRODUCT_CONSIGNEE_PARAMTER_EXCEPTION("106699","产品收件标准信息参数异常",false),
	DELETE_PRODUCT_CONSIGNEE_EXCEPTION("107601","删除产品收件标准信息失败",false),
	DELETE_PRODUCT_CONSIGNEE_SUCCESS("107600","删除产品收件标准信息成功",true),
	SELECT_ONE_PRODUCT_CONSIGNEE_SUCCESS("106800","查询产品收件标准信息成功",true),
	SELECT_ONE_PRODUCT_CONSIGNEE_EXCEPTION("106801","查询产品收件标准信息失败",true),
	THE_PRODUCT_NOT_EXICT_CONSIGNEE("106820","该产品名下不存在收件标准信息",false),
	SELECT_ALL_PRODUCT_CONSIGNEE_SUCCESS("106810","查询产品收件标准信息成功",true),
	SELECT_ALL_PRODUCT_CONSIGNEE_EXCEPTION("106811","查询产品收件标准信息失败",true),
	DELETE_PRODUCT_BASIC_EXCEPTION("103101","删除产品基础信息失败",false),
	DELETE_PRODUCT_TYPE_EXIST_BASIC_EXCEPTION("103002","该类型下存在产品基础信息，删除产品类型失败",false),

	INSERT_PRODUCT_CONTRACT_EXCEPTION("104101","新增产品合同配置信息失败",false),
	INSERT_PRODUCT_CONTRACT_SUCCESS("104100","新增产品合同配置信息成功",true),
	SELECT_ONE_PRODUCT_CONTRACT_SUCCESS("104200","查询产品合同配置信息成功",true),
	SELECT_ONE_PRODUCT_CONTRACT_EXCEPTION("104201","查询产品合同配置信息失败",true),
	UPDATE_ONE_PRODUCT_CONTRACT_SUCCESS("104300","修改产品合同配置信息成功",true),
	UPDATE_ONE_PRODUCT_CONTRACT_EXCEPTION("104301","修改产品合同配置信息失败",true),

	//产品规则引擎配置管理
	IS_EXIST_PRODUCT_RULE_CONFIG_EXCEPTION("108159","新增产品规则引擎配置信息失败！原因：当前产品，相同流程节点，相同对象人群，已经配置过规则引擎，不允许重复配置！",false),
	INSERT_PRODUCT_RULE_CONFIG_SUCCESS("108100","新增产品规则引擎配置信息成功",true),
	INSERT_PRODUCT_RULE_CONFIG_EXCEPTION("108109","新增产品规则引擎配置信息失败",false),
	UPDATE_PRODUCT_RULE_CONFIG_SUCCESS("108110","更新产品规则引擎配置信息成功",true),
	UPDATE_PRODUCT_RULE_CONFIG_EXCEPTION("108119","更新产品规则引擎配置信息失败",false),
	DELETE_PRODUCT_RULE_CONFIG_SUCCESS("108120","删除产品规则引擎配置信息成功",true),
	DELETE_PRODUCT_RULE_CONFIG_EXCEPTION("108129","删除产品规则引擎配置信息失败",false),
	INSERT_PRODUCT_RULE_DETAIL_CONFIG_SUCCESS("108180","新增产品规则引擎配置详情信息成功",true),
	INSERT_PRODUCT_RULE_DETAIL_CONFIG_EXCEPTION("108189","新增产品规则引擎配置详情信息失败",false),
	UPDATE_PRODUCT_RULE_DETAIL_CONFIG_SUCCESS("108181","更新产品规则引擎配置详情信息成功",true),
	UPDATE_PRODUCT_RULE_DETAIL_CONFIG_EXCEPTION("108188","更新产品规则引擎配置详情信息失败",false),
	DELETE_PRODUCT_RULE_DETAIL_CONFIG_SUCCESS("108182","删除产品规则引擎配置详情信息成功",true),
	DELETE_PRODUCT_RULE_DETAIL_CONFIG_EXCEPTION("108187","删除产品规则引擎配置详情信息失败",false),
	INSERT_PRODUCT_FLOW_FORM_EXCEPTION("108188","新增保证人表单失败，保证人类型必传",false),

	//工作流记录信息
	INSERT_CPMS_WORKFLOW_RECORD_SUCCESS("105001","新增产品工作流记录信息成功",true),
	INSERT_CPMS_WORKFLOW_RECORD_EXCEPTION("105002","新增产品工作流记录信息失败",false),
	UPDATE_CPMS_WORKFLOW_RECORD_SUCCESS("105003","修改产品工作流记录信息成功",true),
	UPDATE_CPMS_WORKFLOW_RECORD_EXCEPTION("105004","修改产品工作流记录信息失败",false),
	VALIDATE_CPMS_INFO_EXCEPTION("105005","产品编号为空",false),
	START_CPMS_WORKFLOW_EXCEPTION("105006","开启产品工作流失败",false),
	START_CPMS_CHANGE_WORKFLOW_EXCEPTION("105007","开启产品变更工作流失败",false),
	SUBMIT_CPMS_WORKFLOW_APPLICATION_SUCCESS("105008","提交产品工作流申请成功",true),
	SUBMIT_CPMS_WORKFLOW_APPLICATION_EXCEPTION("105009","提交产品工作流申请失败",false),

	VALIDATE_BASE_INFO_EXCEPTION("105010","产品【基本信息】模块未完善，无法提交审批！",false),
	VALIDATE_CREDIT_INFO_EXCEPTION("105011","产品【授信】模块未完善，无法提交审批！",false),
	VALIDATE_LOAN_INFO_EXCEPTION("105012","产品【用信】模块未完善，无法提交审批！！",false),
	VALIDATE_ASSURE_INFO_EXCEPTION("105013","产品【担保】模块未完善，无法提交审批！",false),
	VALIDATE_PROJECT_INFO_EXCEPTION("105014","产品【定价】模块未完善，无法提交审批！",false),
	VALIDATE_REPAYMENT_INFO_EXCEPTION("105015","产品【还款方式】模块未完善，无法提交审批！",false),
	VALIDATE_CONSIGNEE_INFO_EXCEPTION("105016","产品【收件标准】模块未完善，无法提交审批！",false),
	VALIDATE_FLOW_INFO_EXCEPTION("105017","产品【业务流程】模块未完善，无法提交审批！",false),
	VALIDATE_CONTRACT_INFO_EXCEPTION("105020","产品【合同签署】相关配置未配置，请检查后再提交！",false),

	SAVE_DATA_DICTIONARY_ERROR("105018","数据编号已存在"),
	SAVE_DATA_DICTIONARY_TYPE_ERROR("105019","数据类型已存在"),

	NO_PRODUCT_RULE_CONFIG("108200","风控规则此情况下需配置",false),
	SELECT_PRODUCT_FLOW_CONFIG_EXCEPTION("108201","查询产品节点配置信息失败",false),
	NO_WORKFLOW_CONFIG("108202","审批流此情况下需配置",false),
	NO_PRODUCT_RULE_CONFIG_AND_WORKFLOW_CONFIG("108203","风控规则和审批流此情况下需配置",false),

	QUERY_PERSSION_FROM_BSS_HYSTRIX_ERROR("200019","从基础服务获取用户权限异常熔断"),


	//表单信息
	DELETE_PRODUCT_FORM_INFO_EXCEPTION("210002","删除表单失败",false);

/*	//业务返回码
	PARAMS_ERROR(200001, "参数错误"),
	INFO_EXIST_ALREADY(200002, "信息已存在"),
	INFO_NOT_EXIST(200003, "信息不存在"),*/

	/*PARAMS_ERROR_BUSTYPE(200004,"业务类型参数不正确"),

	MONEY_FORMAT_ERROR(200005,"金额格式或数值小于0"),
	//卡管理返回码
	BANKCARD_INFO_NOT_EXIST(200011, "会员卡信息不存在"),

	THIRD_PARTY_ERROR(200012,"调用第三方接口失败"),

	NOTIFY_ORDER_SETTLE_STATUS_FAIL(200013,"通知订单（兑付、返佣、退款）状态接口失败"),

	SETTLEMENT_RELATION_INFO_NOT_EXIST(200014,"结算与订单关联关系获取失败"),

	PRPDUCT_CARD_INFO_GOT_FAIL(200015,"产品募集账号获取失败"),

	BUSINESS_TYPE_PARAM_ERROR(200016,"业务类型参数错误"),

	UPDATE_SETTLESTATUS_TO_OAP_HYSTRIX_ERROR(200017,"通知订单修改结算状态异常熔断"),
	QUERY_CARDACCOUNT_FROM_PAD_HYSTRIX_ERROR(200018,"从产品侧获取产品募集账号异常熔断"),
	QUERY_PERSSION_FROM_BSS_HYSTRIX_ERROR(200019,"从基础服务获取用户权限异常熔断"),
	UPDATE_COMMISSION_REQ_REPEATED(200020,"更新理财师佣金请求重复"),

    BUSER_COMMISSION_INFO_NOT_EXIST(200021,"理财师佣金信息不存在"),
    UPDATE_INVEST_REQ_REPEATED(200022,"更新c会员投资金额请求重复"),
	CUSER_INVEST_INFO_NOT_EXIST(200023,"c会员投资信息不存在"),

    QUERY_OMSLIST_FROM_OAP_HYSTRIX_ERROR(200024,"从基础服务获取用户权限异常熔断"),
    UPDATE_SETTLESTATUS_TO_OAP_PARAM_ERROR(200025,"通知订单修改结算状态接口参数异常"),


	QUERY_SETTLESTATUS_FROM_OAP_PARAM_ERROR(200026,"从订单处获取返佣详情接口失败"),
	QUERY_RETURNLIST_FROM_OAP_SYSTEM_ERROR(200027,"从订单处获取返佣列表接口失败"),

	UPDATE_COMMISSION_BUSTYPE_ERROR(200028,"修改理财师佣金请求BUSTYPE传参错误"),
	COMMISSION_BUSTYPE_TRANSACTION_ALREADY_EXIST(200029,"系统中已存在该订单下报单/订单取消/流标/佣金结算交易流水"),
	COMMISSION_BUSTYPE_ORDER_OK_NOT_EXIST(200030,"系统中不存在该订单的报单交易流水，无法生成佣金结算交易流水"),
	COMMISSION_BUSTYPE_CONFICT(200031,"系统中存在冲突的业务类型"),

	UPDATE_INVEST_BUSTYPE_ERROR(200032,"修改客户投资信息请求BUSTYPE传参错误"),
	INVEST_BUSTYPE_TRANSACTION_ALREADY_EXIST(200033,"系统中已存在该订单下报单/订单取消/流标/兑付结算交易流水"),
	INVEST_BUSTYPE_ORDER_OK_NOT_EXIST(200034,"系统中不存在该订单的报单交易流水，无法生成兑付结算交易流水"),
	INVEST_BUSTYPE_CONFICT(200035,"系统中存在冲突的业务类型"),

	HONOR_DETAIL_PARAM_ERROR(200036,"兑付明细参数有误"),
	COMMISSION_DETAIL_PARAM_ERROR(200037,"返佣明细参数有误"),
	BANKCARD_FORMAT_ERROR(200038,"银行卡号格式错误"),
	COMMISSION_DETAIL_PARAM_INVALID(200039,"返佣明细参数不全或信息已存在"),
	HONOR_DETAIL_PARAM_INVALID(200040,"兑付明细参数不全或信息已存在"),
	REFUND_DETAIL_PARAM_INVALID(200041,"退款明细参数不全或信息已存在"),
	RECEIPT_DETAIL_PARAM_INVALID(200042,"收款单明细参数不全或信息已存在"),
	PRODUCT_ACCOUNT_INFO_NOT_EXIST(200043,"产品募集账号未获得"),

	DIY_FAIL(299998,"自定义异常code占用"),
	CODE_LAST(299999, "信息不存在");*/

	private String code;
	private String msg;
	private boolean success;

	ResponseCode(String code, String msg, boolean success) {
		this.setCode(code);
		this.setMsg(msg);
		this.setSuccess(success);
	}

	ResponseCode(String code, String msg) {
		this.setCode(code);
		this.setMsg(msg);
		this.setSuccess(false);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


}
