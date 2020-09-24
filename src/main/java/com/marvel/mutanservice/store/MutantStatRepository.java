package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MutantStatRepository extends ReactiveMongoRepository<DnaInfo, String> {
    Mono<Long> countByMutantTrue();
    Mono<Long> countByMutantFalse();
}
