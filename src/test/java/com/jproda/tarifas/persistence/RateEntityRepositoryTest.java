package com.jproda.tarifas.persistence;

import com.jproda.tarifas.PostgreSQLTestBase;
import com.jproda.tarifas.entity.RateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@DataJpaTest
@Transactional(propagation = NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RateEntityRepositoryTest extends PostgreSQLTestBase {
    //System under test
    @Autowired
    RateEntityRepository sut;

    @Test
    public void findAll_test(){
        List<RateEntity> allRates = sut.findAll();
        assertNotNull(allRates);
        assertTrue(!allRates.isEmpty());
    }


}