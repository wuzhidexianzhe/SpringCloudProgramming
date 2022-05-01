package com.example.gatewayserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*
这仅仅是一个测试示范类
 */
@ConfigurationProperties(prefix = "my-gateway")
public class UriConfiguration {
    private String httpbin = "lb://base-server:8084";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
