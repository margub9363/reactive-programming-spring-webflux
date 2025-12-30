package com.rahman.webflux_playground.sec03;

import com.rahman.webflux_playground.sec03.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=sec03")
public class CustomerServiceTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void allCustomers() {
        this.client.get()
                .uri("/customers")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDto.class)
                .value(list -> log.info("{}", list))
                .hasSize(10);
    }

    @Test
    public void paginatedCustomers() {
        this.client.get()
                .uri("/customers/paginated?page=3&size=2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(r -> log.info("{}", new String(r.getResponseBody())))
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(5)
                .jsonPath("$[1].id").isEqualTo(6);

    }

    @Test
    public void customerNotFound() {
        // get
        this.client.get()
                .uri("/customer/45")
                .exchange()
                .expectStatus().is4xxClientError();
//                .expectBody().isEmpty();

        // delete
        this.client.delete()
                .uri("/customer/11")
                .exchange()
                .expectStatus().is4xxClientError();
//                .expectBody().isEmpty();

        // put
        /*var dto = new CustomerDto(null, "noel", "noel@gmail.com");
        this.client.put()
                .uri("/customers/110")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is4xxClientError();*/
//                .expectBody().isEmpty();
    }
}
