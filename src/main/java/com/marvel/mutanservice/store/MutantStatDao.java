package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;
import reactor.core.publisher.Mono;

public interface MutantStatDao {

    Mono<Long> getMutantsCount();
    Mono<Long> getHumansCount();

    void insertMutantStat(DnaInfo dnaInfo);
}
