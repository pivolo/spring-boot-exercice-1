package com.jproda.tarifas.persistence;

import com.jproda.tarifas.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RateEntityRepository extends JpaRepository<RateEntity, Long> {

    public List<RateEntity>
        findByBrandIdAndAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Long brandId, Long productId, Date Date, Date date);

}
