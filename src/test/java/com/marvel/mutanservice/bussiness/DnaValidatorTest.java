package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.MutantServiceApplication;
import com.marvel.mutanservice.configuration.FirestoreConfigurationTest;
import com.marvel.mutanservice.exeptions.DnaMalformedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {MutantServiceApplication.class})
class DnaValidatorTest {

    @Autowired private DnaValidator dnaValidator;

    @Test
    void validateDnaSuccess() {
        List<String> dna = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        assertDoesNotThrow(() -> dnaValidator.validateDna(dna));
    }

    @Test
    void validateDnaFailSmallSizeSequences() {
        List<String> dna = List.of("ATG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        assertThrows(DnaMalformedException.class, () -> dnaValidator.validateDna(dna));
    }

    @Test
    void validateDnaFailBigSizeSequences() {
        List<String> dna = List.of("ATGCGAA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        assertThrows(DnaMalformedException.class, () -> dnaValidator.validateDna(dna));
    }

    @Test
    void validateDnaFailInvalidCharacter() {
        List<String> dna = List.of("ATGCAI","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        assertThrows(DnaMalformedException.class, () -> dnaValidator.validateDna(dna));
    }

    @Test
    void validateDnaFailNotPresent() {
        assertThrows(DnaMalformedException.class, () -> dnaValidator.validateDna(null));
    }
}