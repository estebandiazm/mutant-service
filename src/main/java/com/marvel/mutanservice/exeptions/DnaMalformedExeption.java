package com.marvel.mutanservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DnaMalformedExeption extends RuntimeException{
    public DnaMalformedExeption() {
    }

    public DnaMalformedExeption(String message) {
        super(message);
    }

    public DnaMalformedExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
