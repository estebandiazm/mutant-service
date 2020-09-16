package com.marvel.mutanservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DnaMalformedException extends RuntimeException{
    public DnaMalformedException() {
    }

    public DnaMalformedException(String message) {
        super(message);
    }

    public DnaMalformedException(String message, Throwable cause) {
        super(message, cause);
    }
}
