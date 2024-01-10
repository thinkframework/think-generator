package com.hdhxby.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 权限中心
 *
 * @author lixiaobin
 * @version 2.0, 03/08/21
 * @since 2.0
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringCloudApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AuthApplication.class);
        springApplication.setHeadless(false);
        springApplication.run(args);
    }
}
