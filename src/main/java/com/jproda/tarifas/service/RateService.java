package com.jproda.tarifas.service;

import com.jproda.tarifas.api.model.Rate;

import java.util.Date;
import java.util.Optional;

/**
 * Permitir crear una tarifa nueva
 * Permitir recuperar una tarifa por id, con los precios debidamente formateados y mostrando el
 *          código y símbolo de la moneda.
 * Permitir modificar el precio de una tarifa
 * Permitir borrar una tarifa por id
 * Permitir a partir de una fecha, el identificador del producto y el identificador de la marca,
 *          recuperar la tarifa a aplicar con los precios correctamente formateados con los decimales
 *          proporcionados por el servicio de monedas
 */
public interface RateService {
    Rate create(Rate rate);
    Optional<Rate> findById(Long id);
    void delete(Long id);
    Optional<Rate> findBy(Date date, Long productId, Long brandId);
}
