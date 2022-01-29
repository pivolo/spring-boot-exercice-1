package com.jproda.tarifas.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RateEntityRepository extends JpaRepository<RateEntity, Long> {

    public List<RateEntity>
        findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual
            (Long brandId, Long productId, Date startDate, Date endDate);

}
