package com.marvel.mutanservice.bussiness;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MutantValidatorTest {


    @Autowired private MutantValidator mutantValidator;

    @Test
    void isMutant() {
        List<String> adn = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
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