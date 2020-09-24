package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.models.MutantStat;
import com.marvel.mutanservice.models.DnaInfo;
import com.marvel.mutanservice.store.MutantStatDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MutantStatService {

    private final MutantStatDao mutantStatDao;

    public MutantStat retrieveMutantStats() {

       return  mutantStatDao.getMutantsCount()
                .zipWith(mutantStatDao.getHumansCount())
                .map(objects -> {
                    long mutantCount = objects.getT1();
                    long humanCount = objects.getT2();
                    var ratio = humanCount == 0 ? 1 : mutantCount / (double) humanCount;
                    return new MutantStat()
                            .withCountMutant(mutantCount)
                            .withCountHuman(humanCount)
                            .withRadio(ratio);
                }).block();
    }

    public void createMutantStat(String dnaKey, boolean isMutant) {
        DnaInfo dnaInfo = new DnaInfo()
                .withDna(dnaKey)
                .withMutant(isMutant);

        mutantStatDao.insertMutantStat(dnaInfo);
    }
}
