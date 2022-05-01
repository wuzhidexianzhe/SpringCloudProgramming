package com.example.gatewayserver;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest()
class GatewayServerApplicationTests {

    private WebTestClient webClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:8083").build();


    /*
     * @Description: 单元测试
     * @Author: zsj
     * @Date: 2022/5/1 22:09
     * @param: []
     * @return: []
     **/
    @Test
    void contextLoads() {

        webClient
                .get().uri("/get")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("world");

        webClient
                .get().uri("/delay/3")
                .header("Host", "www.circuitbreaker.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(
                        response -> assertThat(response.getResponseBody()).isEqualTo("fallback".getBytes()));
    }

}
