package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.exeptions.DnaMalformedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DnaValidator {

    public void validateDna(List<String> dna) {
        var isValid = dna.stream()
                .allMatch(s -> s.matches("^[ATCG]{6}$"));
        if (!isValid) {
            throw new DnaMalformedException("DNA invalid");
        }
    }
}
