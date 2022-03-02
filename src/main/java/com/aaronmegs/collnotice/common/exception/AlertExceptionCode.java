package com.aaronmegs.collnotice.common.exception;

public enum AlertExceptionCode implements BaseExceptionCode {
	CUSTOM_ERROR(1130000, ""), CUSTOM_WARING(1130001, ""), CUSTOM_SUCCESS(1130002, ""),
	UPDATE_DEFAULTRULE_ERROR(1130003, "更新默认告警规则失败"), CREATE_ALERTMANAGERULE_UNIQUENESS_ERROR(1130004, "创建告警失败！名称重复"),
	CREATE_ALERTMANAGERULE_ERROR(1130005, "创建告警规则失败！"), PARAM_ERROR(1130006, "操作结果:参数错误"),
	DELETE_DEAFULTRULE_ERROR(1130007, "删除默认告警规则失败"), UPDATE_ALERTMANAGERULE_UNIQUENESS_ERROR(1130008, "修改告警失败！名称重复"),
	UPDATE_ALERTMANAGERULE_ERROR(1130009, "修改告警失败！"), DELETE_ALERTMANAGERULE_ERROR(1130010, "删除告警失败！"),
	UP_ALERTMANAGERULE_ERROR(1130011, "启用告警失败！"), DOWN_ALERTMANAGERULE_ERROR(1130012, "禁用告警失败！"),
	SENDEMAIL_ERROR(1130013, "发送邮件失败！");
	private int code;
	private String msg;

	private AlertExceptionCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
