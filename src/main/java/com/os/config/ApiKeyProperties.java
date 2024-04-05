package com.os.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApiKeyProperties {

    @Value("${api-key.clientKey}")
    private String clientKey;

    @Value("${api-key.secretKey}")
    private String secretKey;


}
