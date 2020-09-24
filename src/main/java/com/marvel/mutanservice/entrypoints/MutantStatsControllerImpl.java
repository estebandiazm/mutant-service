package com.marvel.mutanservice.entrypoints;

import com.marvel.mutanservice.bussiness.MutantStatService;
import com.marvel.mutanservice.entrypoints.dto.MutantStatResponse;
import com.marvel.mutanservice.models.MutantStat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class MutantStatsControllerImpl implements MutantStatsController {

    private final MutantStatService mutantStatService;

    @Override
    @GetMapping
    public ResponseEntity<Object> retrieveMutantStat() {
        MutantStat mutantStat = mutantStatService.retrieveMutantStats();
        return ResponseEntity.ok(
                new MutantStatResponse()
                        .withCountMutant(mutantStat.getCountMutant())
                        .withCountHuman(mutantStat.getCountHuman())
                        .withRadio(mutantStat.getRadio())
        );
    }
}
