package com.basicbackend.infrastructure.configuration.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliSmsproperties {
    private String accessKeyId;
    private String accessKeySecret;
}
