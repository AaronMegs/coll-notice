package com.aaronmegs.collnotice.model;

import lombok.Data;

import java.util.List;

@Data
public class NotifyModel {

	/**
	 * 发件人地址
	 */
	private String sendUserMail;

	/**
	 * 发件人密码
	 */
	private String sendUserPassword;

	/**
	 * 发件人邮件服务器
	 */
	private String sendUserServer;

	/**
	 * 邮件标题
	 */
	private String title;

	/**
	 * 邮件内容
	 */
	private Object message;

	/**
	 * 收件邮箱
	 */
	private List<String> addresses;

	/**
	 * 抄送邮箱
	 */
	private List<String> ccAddresses;

}
