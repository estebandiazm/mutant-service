package com.marvel.mutanservice.configuration;

import com.marvel.mutanservice.entrypoints.dto.DnaValidationResponse;
import com.marvel.mutanservice.exeptions.DnaMalformedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DnaMalformedException.class)
    public ResponseEntity<Object> handleDnaInvalid(DnaMalformedException malformedException) {
        var response = new DnaValidationResponse()
                .withError(malformedException.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDnaInvalid(Exception exception) {
        log.error("Error", exception);
        var response = new DnaValidationResponse()
                .withError("Internal error, please contact with system administrator");
        return ResponseEntity.badRequest().body(response);
    }
}
