package com.marvel.mutanservice.store;

import com.marvel.mutanservice.models.DnaInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MutantStatMongo implements MutantStatDao {

    private final MutantStatRepository repository;

    @Override public List<DnaInfo> getMutantStats() {
        return repository.findAll();
    }

    @Override
    public void insertMutantStat(DnaInfo dnaInfo) {
        repository.save(dnaInfo);
    }
}
