package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MutantStatDao {

    List<DnaInfo> getMutantStats();
    void insertMutantStat(DnaInfo dnaInfo) throws IOException, ExecutionException, InterruptedException;
}
