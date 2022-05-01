package com.example.gatewayserver;

import com.example.gatewayserver.config.UriConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@EnableConfigurationProperties(UriConfiguration.class)
@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }


    /*
     * @Description: 配置request/response，或者是获取路由句柄。也可以配置predicates和filters.也可以在配置文件里代替
     * @Author: zsj
     * @Date: 2022/5/1 18:09
     * param: [routeLocatorBuilder]
     * return: [org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder]
     **/
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder, UriConfiguration uriConfiguration){
        String httpUri = uriConfiguration.getHttpbin();
        return routeLocatorBuilder.routes()
                .route("base-server",p->p.path("/hello")
                        .uri("lb://base-server:8084"))
                .route("error-test",p->p.host("*.circuitbreaker.com")
                        .filters(f ->f.circuitBreaker(config -> config
                                .setName("mycmd")
                                .setFallbackUri("forward:/fallback")))
                        .uri("lb://base-server:8084"))
                .build();
    }
}
