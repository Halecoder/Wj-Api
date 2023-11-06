package com.hl;

import com.hl.client.WJApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@ComponentScan
@Configuration
@ConfigurationProperties(prefix = "wj.api.client")
public class WjApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public WJApiClient wjApiClient() {
        return new WJApiClient(accessKey, secretKey);
    }
}
