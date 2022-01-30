package com.jproda.tarifas.api.exception;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class ErrorMessage {
    private final int statusCode;
    private final Date timestamp;
    private final String message;
}
