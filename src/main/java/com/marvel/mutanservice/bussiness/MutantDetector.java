package com.marvel.mutanservice.bussiness;

import java.util.List;

public interface MutantDetector {

    boolean isMutant(List<String> dna);
}
