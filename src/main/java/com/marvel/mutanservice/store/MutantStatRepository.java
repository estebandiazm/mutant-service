package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

public interface MutantStatRepository extends MongoRepository<DnaInfo, String> {

    @Async
    @Override <S extends DnaInfo> S save(S entity);
}
