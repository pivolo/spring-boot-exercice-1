package com.jproda.tarifas.service;

import com.jproda.tarifas.api.exception.NotFoundException;
import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.entity.RateEntity;
import com.jproda.tarifas.persistence.RateEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateEntityRepository rateEntityRepository;

    @Autowired
    private RateMapper mapper;


    @Override
    public Rate create(Rate rate) {
        RateEntity entity =rateEntityRepository.save(mapper.apiToEntity(rate));
        return mapper.entityToApi(entity);
    }

    @Override
    public Optional<Rate> findById(Long id) {
        Optional<RateEntity> entity = rateEntityRepository.findById(id);
        return entity.map(mapper::entityToApi);
    }

    @Override
    public void delete(Long id) {
        Optional<RateEntity> entity = rateEntityRepository.findById(id);
        entity.ifPresentOrElse(rateEntityRepository::delete,
                () ->new NotFoundException("Rate Id not Found" + id));
    }

    @Override
    public Optional<Rate> findBy(Date date, Long productId, Long brandId) {
        List<RateEntity> entityRates = rateEntityRepository.
                findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                        (brandId, productId, date, date);
        List<Rate> rates = entityRates.stream().map(mapper::entityToApi).collect(Collectors.toList());
        Optional<Rate> rate = entityRates.isEmpty() ? Optional.empty() : Optional.of(rates.get(0));
        return rate;
    }
}
