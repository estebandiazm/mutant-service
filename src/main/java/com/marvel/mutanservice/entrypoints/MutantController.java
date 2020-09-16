package com.marvel.mutanservice.entrypoints;

import com.marvel.mutanservice.bussiness.DnaValidator;
import com.marvel.mutanservice.bussiness.MutantDetector;
import com.marvel.mutanservice.entrypoints.dto.DnaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mutant")
public class MutantController {

    private final MutantDetector mutantDetector;
    private final DnaValidator dnaValidator;

    @PostMapping
    public ResponseEntity validateMutant(@RequestBody DnaRequest dnaRequest) {

        List<String> dna = dnaRequest.getDna();
        dnaValidator.validateDna(dna);
        boolean mutant = mutantDetector.isMutant(dna);

        return ResponseEntity.status(mutant ? HttpStatus.OK : HttpStatus.FORBIDDEN).build();

    }
}


