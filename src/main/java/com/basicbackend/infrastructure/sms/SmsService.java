package com.basicbackend.infrastructure.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {
    private final IAcsClient client;

    public void sendSms(String templateCode) {
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        // 接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", "156xxxxxxxx");
        // 短信签名名称。请在控制台签名管理页面签名名称一列查看（必须是已添加、并通过审核的短信签名）。
        request.putQueryParameter("SignName", "阿里云通信");
        // 短信模板ID
        request.putQueryParameter("TemplateCode", templateCode);
        // 短信模板变量对应的实际值，JSON格式。
        request.putQueryParameter("TemplateParam", "{\"code\":\"8888\"}");
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            if (!Objects.equals(commonResponse.getHttpStatus(), HttpStatus.OK.value())) {
                throw new SmsException("send sms error");
            }
        } catch (ClientException e) {
            log.error("send sms error,cause by:{}", e.getMessage());
            throw new SmsException("send sms error");

        }
    }
}
