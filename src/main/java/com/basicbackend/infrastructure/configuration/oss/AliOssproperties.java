package com.basicbackend.infrastructure.configuration.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOssproperties {
    private String bucketName;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}
