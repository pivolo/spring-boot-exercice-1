package com.jproda.tarifas.api.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(){
        super();
    }
}
