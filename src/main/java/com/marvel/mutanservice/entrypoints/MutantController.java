package com.marvel.mutanservice.entrypoints;

import com.marvel.mutanservice.entrypoints.dto.DnaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface MutantController {

    @Operation(summary = "Evaluate if DAN is mutant or not")
    @ApiResponse(responseCode = "200", description = "when DNA is Mutant")
    @ApiResponse(responseCode = "403", description = "when DNA is Human")
    @RequestBody(
            content = @Content(
                    examples = {
                            @ExampleObject(
                                    name = "DNA Mutant",
                                    value = "{\n\t\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n}"
                            ),
                            @ExampleObject(
                                    name = "DNA Human",
                                    value = "{\n\t\"dna\":[\"TTGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"GCCCTA\",\"TCACTG\"]\n}"
                            )
                    }
            )
    )
    ResponseEntity validateMutant(DnaRequest dnaRequest);
}
