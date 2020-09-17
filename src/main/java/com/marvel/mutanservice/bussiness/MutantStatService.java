package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.models.MutantStat;
import com.marvel.mutanservice.models.DnaInfo;
import com.marvel.mutanservice.store.MutantStatDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MutantStatService {

    private final MutantStatDao mutantStatDao;

    public MutantStat retieveMutantStats() {
        List<DnaInfo> mutantStats = mutantStatDao.getMutantStats();
        var mutants = mutantStats.stream()
                .filter(DnaInfo::isMutant)
                .count();

        var adnTotal = (long) mutantStats.size();
        var countHuman = adnTotal - mutants;
        var ratio = countHuman == 0 ? 1 : mutants / (double) countHuman;
        return new MutantStat()
                .withCountMutant(mutants)
                .withCountHuman(countHuman)
                .withRadio(ratio);
    }

    public void createMutantStat(String dnaKey, boolean isMutant) {

        try {
            DnaInfo dnaInfo = new DnaInfo()
                    .withDna(dnaKey)
                    .withMutant(isMutant);

            mutantStatDao.insertMutantStat(dnaInfo);
        } catch (IOException | ExecutionException | InterruptedException e) {
            log.error("Error storing mutant stat {} {}", dnaKey, isMutant);
        }
    }
}
