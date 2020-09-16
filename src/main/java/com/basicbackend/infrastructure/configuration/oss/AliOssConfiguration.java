package com.basicbackend.infrastructure.configuration.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AliOssConfiguration {
    private final AliOssproperties aliOssproperties;

    @Bean
    public OSSClient ossClient(AliOssproperties aliOssproperties) {
        CredentialsProvider credsProvider = new DefaultCredentialProvider(
                aliOssproperties.getAccessKeyId(),
                aliOssproperties.getAccessKeySecret());
        return new OSSClient(aliOssproperties.getEndpoint(), credsProvider, null);
    }
}
