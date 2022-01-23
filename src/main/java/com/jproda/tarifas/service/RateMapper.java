package com.jproda.tarifas.service;

import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.entity.RateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RateMapper {

    Rate entityToApi(RateEntity entity);
    RateEntity apiToEntity(Rate api);
}
