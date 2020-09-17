package com.marvel.mutanservice.bussiness;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MutantDetectorTest {


    @Autowired private MutantDetectorImpl mutantValidator;

    @Test
    void isMutant() {
        List<String> adn = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CACCTA","TCACTG");
        boolean mutant = mutantValidator.isMutant(adn);
        Assertions.assertTrue(mutant);
    }

    @Test
    void isNotMutant() {
        List<String> adn = List.of("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");
        boolean mutant = mutantValidator.isMutant(adn);
        Assertions.assertFalse(mutant);
    }
}