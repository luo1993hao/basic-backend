package com.basicbackend.infrastructure.configuration.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AliSmsConfiguration {
    private final AliSmsproperties aliSmsproperties;

    @Bean
    public IAcsClient iAcsClient() {
        DefaultProfile defaultProfile = DefaultProfile.getProfile("cn-hangzhou", aliSmsproperties.getAccessKeyId()
                , aliSmsproperties.getAccessKeySecret());
        return new DefaultAcsClient(defaultProfile);
    }
}
