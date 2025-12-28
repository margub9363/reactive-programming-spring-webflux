package com.rahman.webflux_playground.sec01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("traditional")
@Slf4j
public class TraditionalWebController {

    private final RestClient restClient = RestClient.builder()
            .requestFactory(new JdkClientHttpRequestFactory())
            .baseUrl("http://localhost:7070").build();


    @GetMapping("products")
    public List<Product> getProducts() {
        log.info("Request Received on Traditional Web controller*****************");
        var list = this.restClient.get().uri("/demo01/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });
        log.info("received response: {}", list);
        return list;
//        return Collections.emptyList();
    }
}
