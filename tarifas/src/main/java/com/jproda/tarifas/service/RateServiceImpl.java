package com.jproda.tarifas.service;

import com.jproda.tarifas.api.RateApi;
import com.jproda.tarifas.api.exception.ResourceNotFoundException;
import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.persistence.RateEntity;
import com.jproda.tarifas.persistence.RateEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class RateServiceImpl implements RateApi {

    private final Scheduler jdbcScheduler;
    private final RateEntityRepository rateEntityRepository;
    private final RateMapper mapper;


    @Override
    public Mono<Rate> findById(long id){
        return Mono.fromCallable(() -> doFindById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Rate not found: " + id)))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Rate not found")))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Rate> findBy(Date date, long productId, long brandId){
        return Mono.fromCallable(() -> doFindBy(date, productId, brandId)
                        .orElseThrow(() -> new ResourceNotFoundException("Rate not found")))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Rate not found")))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Rate> create(Rate rate){

        return Mono.fromCallable(() -> doCreate(rate)).subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Rate> update(long id, BigDecimal price){

        return Mono.fromCallable(()-> doUpdate(id, price)
                        .orElseThrow(() -> new ResourceNotFoundException("Rate not found: " + id)))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Void> delete(long id){
        return Mono.fromRunnable(() -> doDelete(id)).subscribeOn(jdbcScheduler).then();
    }

    protected Rate doCreate(Rate rate) {

        RateEntity entity =rateEntityRepository.save(mapper.apiToEntity(rate));
        return mapper.entityToApi(entity);
    }

    protected Optional<Rate> doUpdate(Long id, BigDecimal price) {
        Optional<RateEntity> entity =rateEntityRepository.findById(id);
        entity.ifPresent(value -> rateEntityRepository.save(setEntityValue(value, price)));
        return entity.map(mapper::entityToApi);
    }

    private RateEntity setEntityValue(RateEntity entity, BigDecimal price) {
        entity.setPrice(price);
        return entity;
    }

    public Optional<Rate> doFindById(Long id) {
        Optional<RateEntity> entity = rateEntityRepository.findById(id);
        return entity.map(mapper::entityToApi);
    }


    protected void doDelete(Long id) {
        Optional<RateEntity> entity = rateEntityRepository.findById(id);
        entity.ifPresentOrElse(rateEntityRepository::delete,
                () ->new ResourceNotFoundException("Rate Id not Found" + id));
    }

    public Optional<Rate> doFindBy(Date date, Long productId, Long brandId) {
        List<RateEntity> entityRates = rateEntityRepository.
                findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                        (brandId, productId, date, date);
        List<Rate> rates = entityRates.stream().map(mapper::entityToApi).collect(Collectors.toList());
        Optional<Rate> rate = entityRates.isEmpty() ? Optional.empty() : Optional.of(rates.get(0));
        return rate;
    }
}
