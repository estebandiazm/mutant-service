package com.marvel.mutanservice.entrypoints;

import com.marvel.mutanservice.bussiness.MutantStatService;
import com.marvel.mutanservice.models.MutantStat;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MutantStatsControllerTest {

    @Autowired private MutantStatsController mutantStatsController;
    @Autowired private MockMvc mockMvc;
    @MockBean private MutantStatService mutantStatService;

    @Test void contextLoad() {
        assertNotNull(mutantStatsController);
    }

    @Test void validateMutantSuccess() throws Exception {

        Mockito.when(mutantStatService.retrieveMutantStats())
        .thenReturn(new MutantStat()
                .withCountHuman(1)
                .withCountMutant(1)
                .withRadio(1.0));
        this.mockMvc.perform(
                get("/stats")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}