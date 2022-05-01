package com.example.baseserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BaseController {
    /*
     * @Description: 断路器响应服务的一个基础测试类
     * @Author: zsj
     * @Date: 2022/5/1 22:53
     * @param: []
     * @return: []
     **/
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

    /*
     * @Description: 响应服务的一个基础测试类
     * @Author: zsj
     * @Date: 2022/5/1 22:53
     * @param: []
     * @return: []
     **/
    @RequestMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello world");
    }
}
