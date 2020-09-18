package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.MutantServiceApplication;
import com.marvel.mutanservice.configuration.FirestoreConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = {MutantServiceApplication.class, FirestoreConfigurationTest.class})
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