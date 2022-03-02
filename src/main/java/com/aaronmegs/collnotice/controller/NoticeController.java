package com.aaronmegs.collnotice.controller;

import com.aaronmegs.collnotice.common.exception.AlertExceptionCode;
import com.aaronmegs.collnotice.common.http.DataResponse;
import com.aaronmegs.collnotice.model.NotifyModel;
import com.aaronmegs.collnotice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author aaronmegs
 * @create 2022/3/2
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/sendMail")
    @ResponseBody
    public DataResponse sendMail(@RequestBody NotifyModel model) {
        if (model == null || !StringUtils.hasLength(model.getSendUserMail()) || !StringUtils.hasLength(model.getSendUserPassword()) || !StringUtils.hasLength(model.getSendUserServer())) {
            return DataResponse.error(AlertExceptionCode.PARAM_ERROR,"请求参数为空: 邮件服务器配置参数为空");
        }
        if (ObjectUtils.isEmpty(model.getMessage()) || !StringUtils.hasLength(model.getTitle())) {
            return DataResponse.error(AlertExceptionCode.PARAM_ERROR,"请求参数为空: 发送的邮件内容或标题为空");
        }
        if (ObjectUtils.isEmpty(model.getAddresses())) {
            return DataResponse.error(AlertExceptionCode.PARAM_ERROR,"请求参数为空: 收件人参数为空");
        }
        /*
        // 发件人邮箱配置
        model.setSendUserMail("xxx@xxx.com");
        model.setSendUserPassword("password");
        model.setSendUserServer("smtp.exmail.qq.com");

        // 收件人信息
        model.setMessage("hello test");
        model.setTitle("test mail");
        ArrayList<String> address = new ArrayList<>(5);
        address.add("xxx@xxx.com");
        ArrayList<String> ccAddress = new ArrayList<>(5);
        ccAddress.add("xxx@xxx.com");
        model.setAddresses(address);
        model.setCcAddresses(ccAddress);
         */
        return noticeService.sendEmail(model);
    }
}
