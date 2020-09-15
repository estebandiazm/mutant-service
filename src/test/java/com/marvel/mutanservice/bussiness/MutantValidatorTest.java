package com.marvel.mutanservice.bussiness;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MutantValidatorTest {


    private MutantValidator mutantValidator = new MutantValidator();

    @Test
    void isMutant() {
        List<String> adn = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCCTA","TCACTG");
        boolean mutant = mutantValidator.isMutant(adn);
        Assertions.assertTrue(mutant);
    }
}