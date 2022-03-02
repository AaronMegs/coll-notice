package com.aaronmegs.collnotice.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 * @author
 */
public class Email {

	Log log = LogFactory.getLog(this.getClass());

	// 发件人地址
	private String mail_from;

	public void setMail_from(String mailFrom) {
		mail_from = mailFrom;
	}

	public String getMail_from() {
		return mail_from;
	}

	// 发件人密码
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	// 收件地址
	private String mail_to;

	public void setMail_to(String mailTo) {
		mail_to = mailTo;
	}

	public String getMail_to() {
		return mail_to;
	}

	// 主题
	private String title;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	// 内容
	private Object message;

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	// 发件人邮件服务器 如：ceiec-electric.com 191.0.0.6
	private String send_server;

	public void setSend_server(String sendServer) {
		send_server = sendServer;
	}

	public String getSend_server() {
		return send_server;
	}

	private String serverPort;

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * 单个发送 setMail_to(String mailTo)
	 *
	 * @return 是否正确发送
	 * @throws AddressException
	 */
	public boolean SendMail() throws AddressException {
		try {
			int n = this.mail_from.indexOf('@');
			int m = this.mail_from.length();
			// 取邮件的服务器
			String server = this.mail_from.substring(n + 1, m);
			// 建立邮件会话
			Properties pro = new Properties();
			pro.put("mail.smtp.host", "smtp." + server);
			pro.put("mail.smtp.auth", "true");
			Session sess = Session.getInstance(pro);
			sess.setDebug(true);
			// 新建一个消息对象
			MimeMessage message = new MimeMessage(sess);
			// 设置发件人
			InternetAddress from_address = new InternetAddress(this.mail_from);
			message.setFrom(from_address);
			// 设置收件人
			InternetAddress to_address = new InternetAddress(this.mail_to);
			message.setRecipient(Message.RecipientType.TO, to_address);
			// 设置主题
			message.setSubject(title);
			// 设置内容
			message.setContent(this.message, "text/html;charset=UTF-8");
			// 设置发送时间
			message.setSentDate(new Date());
			// 发送邮件
			message.saveChanges(); // 保存邮件信息
			// 设置发送的格式
			Transport transport = sess.getTransport("smtp");
			if (this.send_server == null) {
				transport.connect("191.0.0.6", this.mail_from, password);
			} else {
				transport.connect(this.send_server, this.mail_from, password);
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info("向" + this.mail_to + "发送邮件成功");
			return true;
		} catch (Exception e) {
			log.info("发送邮件失败，原因可能是" + e.toString());
			return false;
		}

	}

	/**
	 * 群发
	 *
	 * @param addresses 地址list
	 * @return
	 * @throws AddressException
	 */
	public boolean SendMail(List<String> addresses) throws AddressException {
		try {
			int n = this.mail_from.indexOf('@');
			int m = this.mail_from.length();
			// 取邮件的服务器
			String server = this.mail_from.substring(n + 1, m);
			// 建立邮件会话
			Properties pro = new Properties();
			pro.put("mail.smtp.host", "smtp." + server);
			pro.put("mail.smtp.auth", "true");
			Session sess = Session.getInstance(pro);
			sess.setDebug(true);
			// 新建一个消息对象
			MimeMessage message = new MimeMessage(sess);
			// 设置发件人
			InternetAddress from_address = new InternetAddress(this.mail_from);
			message.setFrom(from_address);
			// 设置收件人，多个
			final int num = addresses.size();
			InternetAddress to_address[] = new InternetAddress[num];
			for (int i = 0; i < num; i++) {
				to_address[i] = new InternetAddress(addresses.get(i));
			}
			message.setRecipients(Message.RecipientType.TO, to_address);
			// 设置主题
			message.setSubject(title);
			// 设置内容
			message.setContent(this.message, "text/html;charset=UTF-8");
			// 设置发送时间
			message.setSentDate(new Date());
			// 发送邮件
			message.saveChanges(); // 保存邮件信息
			// 设置发送的格式
			Transport transport = sess.getTransport("smtp");
			if (this.send_server == null) {
				transport.connect("191.0.0.6", this.mail_from, password);
			} else {
				transport.connect(this.send_server, this.mail_from, password);
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info("发送邮件成功");
			return true;
		} catch (Exception e) {
			log.info("发送邮件失败，原因可能是" + e.getMessage());
			return false;
		}

	}

	/**
	 * 多人发送并抄送
	 *
	 * @param addresses   收件邮箱（多个）
	 * @param ccAddresses 抄送邮箱（多个）
	 * @return
	 * @throws AddressException
	 */
	public boolean SendMail(List<String> addresses, List<String> ccAddresses) throws AddressException {
//		if (0 == ccAddresses.size()) { // 20170428 whh
//			return SendMail(addresses);
//		}
		try {
			int n = this.mail_from.indexOf('@');
			int m = this.mail_from.length();
			// 取邮件的服务器
			String server = this.mail_from.substring(n + 1, m);
			// 建立邮件会话
			Properties pro = new Properties();
			pro.put("mail.smtp.host", "smtp." + server);
			pro.put("mail.smtp.auth", "true");
			pro.put("mail.smtp.port", StringUtils.isNotBlank(this.serverPort) ? this.serverPort : "25");
			Session sess = Session.getInstance(pro);
			sess.setDebug(false);
			// 新建一个消息对象
			MimeMessage message = new MimeMessage(sess);
			// 设置发件人
			InternetAddress from_address = new InternetAddress(this.mail_from);
			message.setFrom(from_address);
			// 设置收件人，多个
			final int num = addresses != null ? addresses.size() : 0;
			InternetAddress to_address[] = new InternetAddress[num];
			for (int i = 0; i < num; i++) {
				to_address[i] = new InternetAddress(addresses.get(i));
			}
			message.setRecipients(Message.RecipientType.TO, to_address);
			// 设置抄送人
			final int ccSize = ccAddresses != null ? ccAddresses.size() : 0;
			if (ccSize > 0) {
				InternetAddress cc_Address[] = new InternetAddress[ccSize];
				for (int i = 0; i < ccSize; i++) {
					cc_Address[i] = new InternetAddress(ccAddresses.get(i));
				}
				message.setRecipients(Message.RecipientType.CC, cc_Address);
			}

			// 设置主题
			message.setSubject(title);
			// 设置内容
//			message.setContent(this.message, "text/html;charset=UTF-8");
            // GS 设置内容格式支持换行和空格
			message.setContent("<pre>" +this.message + "</pre>", "text/html;charset=UTF-8");
			// 设置发送时间
			message.setSentDate(new Date());
			// 发送邮件
			message.saveChanges(); // 保存邮件信息
			// 不被当作垃圾邮件的关键代码--Begin ，如果不加这些代码，发送的邮件会自动进入对方的垃圾邮件列表
			message.addHeader("X-Priority", "3");
			message.addHeader("X-MSMail-Priority", "Normal");
			message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869"); // 本文以outlook名义发送邮件，不会被当作垃圾邮件
			message.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
			message.addHeader("ReturnReceipt", "1");
			// 不被当作垃圾邮件的关键代码--end
			// 设置发送的格式
			Transport transport = sess.getTransport("smtp");
			if (this.send_server == null) {
				transport.connect("191.0.0.6", this.mail_from, password);
			} else {
				transport.connect(this.send_server, this.mail_from, password);
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info("发送邮件成功");
			return true;
		} catch (Exception e) {
			log.info("发送邮件失败，原因可能是" + e.getMessage());
			return false;
		}

	}

//	/**
//	 * 多人发送并抄送 增加：2019-06-14 whh
//	 *
//	 * @param addresses   收件邮箱（多个）
//	 * @param ccAddresses 抄送邮箱（多个）
//	 * @param is          附件
//	 * @return
//	 * @throws AddressException
//	 */
//	public boolean SendMail(List<String> addresses, List<String> ccAddresses, HSSFWorkbook workBook,
//			String workBookName) throws AddressException {
//		try {
//			int n = this.mail_from.indexOf('@');
//			int m = this.mail_from.length();
//			// 取邮件的服务器
//			String server = this.mail_from.substring(n + 1, m);
//			// 建立邮件会话
//			Properties pro = new Properties();
//			pro.put("mail.smtp.host", "smtp." + server);
//			pro.put("mail.smtp.auth", "true");
//			Session sess = Session.getInstance(pro);
//			sess.setDebug(true);
//			// 新建一个消息对象
//			MimeMessage message = new MimeMessage(sess);
//			// 设置发件人
//			InternetAddress from_address = new InternetAddress(this.mail_from);
//			message.setFrom(from_address);
//			// 设置收件人，多个
//			final int num = addresses.size();
//			InternetAddress to_address[] = new InternetAddress[num];
//			for (int i = 0; i < num; i++) {
//				to_address[i] = new InternetAddress(addresses.get(i));
//			}
//			message.setRecipients(Message.RecipientType.TO, to_address);
//			// 设置抄送人
//			final int ccSize = ccAddresses.size();
//			if (ccSize > 0) {
//				InternetAddress cc_Address[] = new InternetAddress[ccSize];
//				for (int i = 0; i < ccSize; i++) {
//					cc_Address[i] = new InternetAddress(ccAddresses.get(i));
//				}
//				message.setRecipients(Message.RecipientType.CC, cc_Address);
//			}
//
//			// 附件
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			workBook.write(baos);
//			baos.flush();
//			byte[] bt = baos.toByteArray();
//			InputStream is = new ByteArrayInputStream(bt, 0, bt.length);
//			baos.close();
//
//			/* 添加正文内容 */
//			Multipart multipart = new MimeMultipart();
//			BodyPart contentPart = new MimeBodyPart();
////            contentPart.setText(this.message);
////            contentPart.setHeader("Content-Type", "text/html;charset=UTF-8");
//			contentPart.setContent(this.message, "text/html;charset=UTF-8");
//			multipart.addBodyPart(contentPart);
//
//			/* 添加附件 */
//			MimeBodyPart fileBody = new MimeBodyPart();
//			DataSource source = new ByteArrayDataSource(is, "application/msexcel");
//			fileBody.setDataHandler(new DataHandler(source));
//			// 中文乱码问题
//			fileBody.setFileName(MimeUtility.encodeText(workBookName + ".xls"));
//			multipart.addBodyPart(fileBody);
//
//			// 设置主题
//			message.setSubject(title);
//			// 设置内容
//			message.setContent(multipart);
//			// 设置发送时间
//			message.setSentDate(new Date());
//			// 发送邮件
//			message.saveChanges(); // 保存邮件信息
//			// 设置发送的格式
//			Transport transport = sess.getTransport("smtp");
//			if (this.send_server == null) {
//				transport.connect("191.0.0.6", this.mail_from, password);
//			} else {
//				transport.connect(this.send_server, this.mail_from, password);
//			}
//			transport.sendMessage(message, message.getAllRecipients());
//			transport.close();
//			log.info("发送邮件成功");
//			return true;
//		} catch (Exception e) {
//			log.info("发送邮件失败，原因可能是" + e.getMessage());
//			return false;
//		}
//
//	}

}