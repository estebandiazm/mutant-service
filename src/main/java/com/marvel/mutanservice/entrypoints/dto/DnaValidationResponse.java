package com.marvel.mutanservice.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DnaValidationResponse {
    @With private String message;
    @With private Boolean isMutant;
    @With private String error;
}
