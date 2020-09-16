package com.marvel.mutanservice.configuration;

import com.marvel.mutanservice.exeptions.DnaMalformedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DnaMalformedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleDnaInvalid(DnaMalformedException malformedException) {
        return ResponseEntity.badRequest().body(malformedException);
    }
}
