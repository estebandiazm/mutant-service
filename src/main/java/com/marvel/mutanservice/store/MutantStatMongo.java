package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MutantStatMongo implements MutantStatDao {

    private final MutantStatRepository repository;

    @Override public Mono<Long> getMutantsCount() {
        return repository.countByMutantTrue();
    }

    @Override public Mono<Long> getHumansCount() {
        return repository.countByMutantFalse();
    }

    @Override
    public void insertMutantStat(DnaInfo dnaInfo) {
        repository.save(dnaInfo);
    }
}
