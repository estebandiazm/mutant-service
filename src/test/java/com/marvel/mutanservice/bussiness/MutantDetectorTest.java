package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.MutantServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = {MutantServiceApplication.class})
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

    @Test
    void isNotMutantOnlyOneDiagonalSequence() {
        List<String> dna = List.of("ATGCAA", "CAGTGC", "TTATGT", "AGAAGG", "ACCCTA", "TCACTG");
        boolean mutant = mutantValidator.isMutant(dna);
        Assertions.assertFalse(mutant);
    }

    @Test
    void isMutantTwoDiagonalSequences() {
        List<String> dna = List.of("AATGAG","TTGATC","AGCTAT","TCTAAT","ATATAC","AATTCT");
        boolean mutant = mutantValidator.isMutant(dna);
        Assertions.assertTrue(mutant);
    }
}