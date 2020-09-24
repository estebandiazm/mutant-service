package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.MutantServiceApplication;
import com.marvel.mutanservice.models.MutantStat;
import com.marvel.mutanservice.store.MutantStatDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {MutantServiceApplication.class})
class MutantStatServiceTest {

    @Autowired private MutantStatService mutantStatService;
    @MockBean private MutantStatDao mutantStatDao;

    @Test void retrieveMutantStatsSuccess() {

        Mockito.when(mutantStatDao.getHumansCount()).thenReturn(Mono.just(1L));
        Mockito.when(mutantStatDao.getMutantsCount()).thenReturn(Mono.just(1L));

        MutantStat mutantStat = mutantStatService.retrieveMutantStats();

        Assertions.assertEquals(1, mutantStat.getCountMutant());
        Assertions.assertEquals(1, mutantStat.getCountHuman());
        Assertions.assertEquals(1.0, mutantStat.getRadio());

    }

    @Test void createMutantStatSuccessful() {
        Assertions.assertDoesNotThrow(() -> mutantStatService.createMutantStat("[DNA_KEY]", true));
    }
}