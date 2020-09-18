package com.marvel.mutanservice.entrypoints;

import com.marvel.mutanservice.models.MutantStat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface MutantStatsController {
    @Operation(summary = "Retrieve mutant stats")
    @ApiResponse(responseCode = "200", description = "when DNA is Mutant",
            content = @Content(schema = @Schema(implementation = MutantStat.class),
                    examples = @ExampleObject(value = "{\n\t\"count_mutant_dna\":40,\n\t\"count_human_dna\":100, \n\t\"ratio\":0.4\n}")))
    ResponseEntity retrieveMutantStat();
}
