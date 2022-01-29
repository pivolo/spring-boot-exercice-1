package com.jproda.tarifas.service;

import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.persistence.RateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper {

    Rate entityToApi(RateEntity entity);
    RateEntity apiToEntity(Rate api);
}
