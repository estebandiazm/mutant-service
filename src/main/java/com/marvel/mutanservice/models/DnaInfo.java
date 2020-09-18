package com.marvel.mutanservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaInfo {
    @With @Id
    private String dna;
    @With private boolean isMutant;
}
