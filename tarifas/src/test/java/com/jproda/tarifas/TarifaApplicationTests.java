package com.jproda.tarifas;

import com.jproda.tarifas.api.exception.ResourceNotFoundException;
import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.service.RateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TarifaApplicationTests extends IntegrationTestBase {

    private static final long RATE_ID_OK = 1;
    private static final long RATE_ID_KO = 10;

    @Autowired
    private WebTestClient client;

    @MockBean
    private RateServiceImpl rateApi;

    @BeforeEach
    void setUp() {

        when(rateApi.findById(RATE_ID_OK))
                .thenReturn(Mono.just(new Rate(
                        RATE_ID_OK, 1l,1l,new Date(), new Date(),
                        new BigDecimal(1000), "EUR")
                ));
        when(rateApi.findById(RATE_ID_KO))
                .thenReturn(Mono.error(new ResourceNotFoundException("Id "  + RATE_ID_KO)));

    }

    @Test
    void contextLoads() {
    }

    @Test
    void givenIdOK_findRateById() {
        getAndVerifyRate(RATE_ID_OK, OK)
                .jsonPath("$.id").isEqualTo(RATE_ID_OK);
    }

    @Test
    void givenIdKO_findRateById() {
        getAndVerifyRate(RATE_ID_KO, HttpStatus.NOT_FOUND);
    }

    private WebTestClient.BodyContentSpec getAndVerifyRate(long rateId, HttpStatus expectedStatus) {
        return client.get()
                .uri("api/rate/" + rateId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }
}
