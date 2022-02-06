package com.jproda.currencies.api;

import com.jproda.currencies.model.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrenciesDelegateImpl implements V1ApiDelegate {
    private static List<Currency> getCurrenciesData(){
        Currency euro = new Currency();
        euro.setCode("EUR");
        euro.setDecimals(2);
        euro.setSymbol("€");
        Currency dollar = new Currency();
        dollar.setCode("DOL");
        dollar.setDecimals(2);
        dollar.setSymbol("$");
        return Arrays.asList(euro, dollar);
    }
    @Override
    public ResponseEntity<List<Currency>> getCurrencies() {


        return ResponseEntity.ok().body(getCurrenciesData());
    }

    @Override
    public ResponseEntity<Currency> getCurrencyByCode(String currencyCode) {
        return ResponseEntity.ok().body(
                getCurrenciesData().stream().filter(currency -> currency.getCode().equals(currencyCode))
                        .findFirst().orElseThrow(RuntimeException::new));
    }
}
