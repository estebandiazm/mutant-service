package com.marvel.mutanservice.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MutantStatResponse {
    @With
    @JsonProperty("count_mutant_dna")
    private long countMutant;
    @With
    @JsonProperty("count_human_dna")
    private long countHuman;
    @With private double radio;
}
