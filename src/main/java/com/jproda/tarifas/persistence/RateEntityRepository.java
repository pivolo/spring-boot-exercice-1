package com.jproda.tarifas.persistence;

import com.jproda.tarifas.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateEntityRepository extends JpaRepository<RateEntity, Long> {

}
