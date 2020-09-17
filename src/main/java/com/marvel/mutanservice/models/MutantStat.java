package com.marvel.mutanservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MutantStat {
    @With private long countMutant;
    @With private long countHuman;
    @With private double radio;
}
