package com.marvel.mutanservice.entrypoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.mutanservice.entrypoints.dto.DnaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MutantControllerTest {

    @Autowired MutantController mutantController;
    @Autowired private MockMvc mockMvc;

    @Test void contextLoad() {
        assertNotNull(mutantController);
    }

    @Test void validateMutantSuccess() throws Exception {

        DnaRequest request = new DnaRequest();
        request.setDna(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        this.mockMvc.perform(
                post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request))
        ).andExpect(status().isOk());
    }

    @Test void validateMutantFailIsHuman() throws Exception {

        DnaRequest request = new DnaRequest();
        request.setDna(List.of("CTGCGA","CAGTGC","TTATGT","AGAAGG","ACCCTA","TCACTG"));
        this.mockMvc.perform(
                post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
        ).andExpect(status().isForbidden());
    }

    @Test void validateMutantFailBadRequest() throws Exception {

        DnaRequest request = new DnaRequest();
        request.setDna(List.of("CTGCGAA","CAGTGC","TTATGT","AGAAGG","ACCCTA","TCACTG"));
        this.mockMvc.perform(
                post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
        ).andExpect(status().isBadRequest());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}