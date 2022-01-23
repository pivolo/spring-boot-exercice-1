package com.jproda.tarifas.persistence;

import com.jproda.tarifas.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {

}
