package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;

import java.util.List;

public interface MutantStatDao {

    List<DnaInfo> getMutantStats();
    void insertMutantStat(DnaInfo dnaInfo);
}
