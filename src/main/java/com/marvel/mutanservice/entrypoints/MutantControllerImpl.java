package com.marvel.mutanservice.entrypoints;

import com.marvel.mutanservice.bussiness.DnaValidator;
import com.marvel.mutanservice.bussiness.MutantDetector;
import com.marvel.mutanservice.bussiness.MutantStatService;
import com.marvel.mutanservice.entrypoints.dto.DnaRequest;
import com.marvel.mutanservice.entrypoints.dto.DnaValidationResponse;
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
public class MutantControllerImpl implements MutantController {

    private final MutantDetector mutantDetector;
    private final DnaValidator dnaValidator;
    private final MutantStatService mutantStatService;

    @PostMapping
    public ResponseEntity<Object> validateMutant(@RequestBody DnaRequest dnaRequest) {

        List<String> dna = dnaRequest.getDna();
        dnaValidator.validateDna(dna);
        var mutant = mutantDetector.isMutant(dna);
        mutantStatService.createMutantStat(dna.toString(), mutant);

        var response = new DnaValidationResponse()
                .withIsMutant(mutant)
                .withMessage("Dna validation successful");

        return ResponseEntity.status(mutant ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(response);

    }
}


