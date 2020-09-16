package com.marvel.mutanservice.entrypoints.dto;

import lombok.Data;

import java.util.List;

@Data
public class DnaRequest {

    private List<String> dna;
}
