package com.jproda.tarifas.service;

import com.jproda.tarifas.IntegrationTestBase;
import com.jproda.tarifas.api.model.Rate;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RateServiceImplTI extends IntegrationTestBase {

    @Autowired
    RateService rateService;

    @Test
    void create() {
        Rate rate = Rate.builder().currencyCode("EUR").brandId(1l).price(BigDecimal.ONE)
                .startDate(new Date()).endDate(new Date()).build();
        Rate actual = rateService.create(rate);
        assertNotNull(actual);
        assertNotNull(actual.getId());
    }

    @Test
    void givingExistingRate_findById_returnRate() {
        Optional<Rate> actual = rateService.findById(1l);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get().getId());
    }

    @Test
    void givingNotExistingRate_findById_returnEmpty() {
        Optional<Rate> actual = rateService.findById(1000l);
        assertTrue(actual.isEmpty());
    }

    @Test
    void givenExistingRate_delete_notFountAfter() {
        Optional<Rate> actualBeforeDelete = rateService.findById(3l);
        assertTrue(actualBeforeDelete.isPresent());
        rateService.delete(3l);
        Optional<Rate> actualAfterDelete = rateService.findById(3l);
        assertTrue(actualAfterDelete.isEmpty());
    }
    @Test
    void givenExistingParams_findBy_returnRate() {
        Date date = Date.from(LocalDate.of(2022, 1, 2).
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        Optional<Rate> actual = rateService.findBy(date, 2l, 1l);
        assertTrue(actual.isPresent());
    }
}