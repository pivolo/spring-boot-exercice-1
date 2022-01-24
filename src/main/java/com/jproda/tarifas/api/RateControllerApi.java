package com.jproda.tarifas.api;

import com.jproda.tarifas.api.model.Rate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequestMapping("/api/rate")
@Tag(name = "RateControllerApi", description = "REST API for Rates information.")
public interface RateControllerApi {


    @Operation(
            summary = "${api.rates.findById}",
            description = "${api.rates.findById.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })

    @GetMapping("{id}")
    Mono<Rate> findById(@PathVariable long id);

    @PostMapping
    Mono<Rate> create(@RequestBody Rate rate);

    @PutMapping("{id}")
    Mono<Rate> update(@PathVariable long id, @RequestParam BigDecimal price);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Mono<Void> delete(@PathVariable long id);
}
