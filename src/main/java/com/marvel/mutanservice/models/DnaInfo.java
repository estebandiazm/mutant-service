package com.marvel.mutanservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaInfo {
    @With private String dna;
    @With private boolean isMutant;
}
