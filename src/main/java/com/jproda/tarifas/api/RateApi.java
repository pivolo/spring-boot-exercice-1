package com.jproda.tarifas.api;

import com.jproda.tarifas.api.model.Rate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

@RequestMapping("/api/rate")
@Tag(name = "RateApi", description = "REST API for Rates information.")
public interface RateApi {


    @Operation(
            summary = "${api.rates.findById}",
            description = "${api.rates.findById.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    })

    @GetMapping("{id}")
    Mono<Rate> findById(@PathVariable long id);

    //yyyy-MM-dd
    @Operation(
            summary = "${api.rates.findById}",
            description = "${api.rates.findById.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    })
    @GetMapping("/search")
    Mono<Rate> findBy(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date date, @RequestParam long productId, @RequestParam  long brandId);

    @Operation(
            summary = "${api.rates.findById}",
            description = "${api.rates.findById.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    })
    @PostMapping
    Mono<Rate> create(@RequestBody Rate rate);

    @Operation(
            summary = "${api.rates.findById}",
            description = "${api.rates.findById.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    })
    @PutMapping("{id}")
    Mono<Rate> update(@PathVariable long id, @RequestParam BigDecimal price);

    @Operation(
            summary = "${api.rates.findById}",
            description = "${api.rates.findById.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Mono<Void> delete(@PathVariable long id);
}
