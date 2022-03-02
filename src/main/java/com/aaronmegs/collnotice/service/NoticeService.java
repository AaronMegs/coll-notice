package com.aaronmegs.collnotice.service;

import com.aaronmegs.collnotice.common.exception.AlertExceptionCode;
import com.aaronmegs.collnotice.common.http.DataResponse;
import com.aaronmegs.collnotice.model.NotifyModel;
import com.aaronmegs.collnotice.utils.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;

@Service
@Slf4j
public class NoticeService {

//	@Resource
//	private EmailConfig emailConfig;

	public DataResponse sendEmail(NotifyModel record) {
		Email email = new Email();
		// 获取的邮箱信息
		// 发件人邮箱地址
		email.setMail_from(record.getSendUserMail());
//		email.setMail_from(StringUtils.isNotEmpty(record.getSendUserMail()) ? record.getSendUserMail()
//				: emailConfig.getUsername());
		// 获取并解析邮箱密码
		try {
			email.setPassword(record.getSendUserPassword());
//			email.setPassword(StringUtils.isNotEmpty(record.getSendUserPassword())
//					? AESUtil.desEncrypt(record.getSendUserPassword())
//					: emailConfig.getPassword());
		} catch (Exception e1) {
			log.error("发件人邮箱密码解析错误,原因:" + e1);
			e1.printStackTrace();
		}
		// 服务器信息
		email.setSend_server(record.getSendUserServer());
//		email.setSend_server(StringUtils.isNotEmpty(record.getSendUserServer()) ? record.getSendUserServer()
//				: emailConfig.getHost());
//		email.setServerPort();

		// 邮件消息和标题
		email.setMessage(record.getMessage());
		email.setTitle(record.getTitle());
		boolean flag = false;
		try {
			flag = email.SendMail(record.getAddresses(), record.getCcAddresses());
		} catch (AddressException e) {
            log.error(e.getMessage());
			return DataResponse.error(AlertExceptionCode.SENDEMAIL_ERROR);
		}

		if (flag) {
			return DataResponse.success("发送邮件成功！", null);
		} else {

			return DataResponse.error(AlertExceptionCode.SENDEMAIL_ERROR, "请查看日志");
		}
	}

}
