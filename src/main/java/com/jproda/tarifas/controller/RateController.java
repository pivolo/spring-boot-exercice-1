package com.jproda.tarifas.controller;

import com.jproda.tarifas.api.RateApi;
import com.jproda.tarifas.api.exception.ResourceNotFoundException;
import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.service.RateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@AllArgsConstructor
@Slf4j
public class RateController implements RateApi {
    private final RateService rateService;
    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    @Override
    public Mono<Rate> findById(long id){
        return Mono.fromCallable(() -> rateService.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Rate not found: " + id)))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Rate> findBy(Date date, long productId, long brandId){
        return Mono.fromCallable(() -> rateService.findBy(date, productId, brandId)
                        .orElseThrow(() -> new ResourceNotFoundException("Rate not found")))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Rate> create(Rate rate){

        return Mono.fromCallable(() -> rateService.create(rate)).subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Rate> update(long id, BigDecimal price){

        return Mono.fromCallable(()-> rateService.update(id, price)
                    .orElseThrow(() -> new ResourceNotFoundException("Rate not found: " + id)))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Void> delete(long id){
        return Mono.fromRunnable(() -> rateService.delete(id)).subscribeOn(jdbcScheduler).then();
    }
}
