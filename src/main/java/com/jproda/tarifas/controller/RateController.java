package com.jproda.tarifas.controller;

import com.jproda.tarifas.api.exception.ResourceNotFoundException;
import com.jproda.tarifas.api.model.Rate;
import com.jproda.tarifas.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rate")
@AllArgsConstructor
public class RateController {
    private final RateService rateService;

    @GetMapping("{id}")
    public ResponseEntity<Rate> findById(@PathVariable long id){
        Rate Rate = rateService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found: " + id));
        return ResponseEntity.ok(Rate);
    }

    @PostMapping
    public Rate create(@RequestBody Rate rate){
        return rateService.create(rate);
    }


    @PutMapping("{id}")
    public ResponseEntity<Rate> update(@PathVariable long id, @RequestBody Rate rate){
        Rate RateSaved = rateService.update(id, rate)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found: " + id));
        return ResponseEntity.ok(RateSaved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        rateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
