package com.jproda.tarifas.service;

import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.persistence.RateEntity;
import com.jproda.tarifas.persistence.RateEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateServiceImplTest {
    @Mock
    RateEntityRepository rateEntityRepository;

    @Mock
    RateMapper rateMapper;

    @InjectMocks
    private RateServiceImpl sut;



    @Test
    void givenID_findById_returRate() {
        //Preparacion test:
        RateEntity rateEntity = RateEntity.builder().id(1l).brandId(2l).price(new BigDecimal(1000)).build();
        when(rateEntityRepository.findById(1l)).thenReturn(Optional.of(rateEntity));
        Rate rate = new Rate();
        rate.setId(1l);
        rate.setBrandId(2l);
        when(rateMapper.entityToApi(rateEntity)).thenReturn(rate);

        //Test:
        Optional<Rate> actual = sut.findById(1l);
        assertTrue(actual.isPresent());
        assertEquals(2l, actual.get().getBrandId());

    }

    @Test
    void create() {
        //Preparacion test:
        Rate rate = new Rate();
        rate.setId(1l);
        rate.setBrandId(2l);
        rate.setPrice(new BigDecimal(1000));
        RateEntity rateEntity = RateEntity.builder().id(1l)
                .brandId(2l).price(new BigDecimal(1000)).build();
        when(rateMapper.apiToEntity(rate)).thenReturn(rateEntity);

        //Test:
        sut.create(rate);
        verify(rateEntityRepository, times(1)).save(rateEntity);
    }


}