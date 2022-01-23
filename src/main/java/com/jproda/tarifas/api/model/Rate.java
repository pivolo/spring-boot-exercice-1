package com.jproda.tarifas.api.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rate {
    private Long id;
    private Long brandId;
    private Long productId;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;
    private String currencyCode;
}
