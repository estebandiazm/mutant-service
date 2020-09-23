package com.marvel.mutanservice.bussiness;

import com.marvel.mutanservice.exeptions.DnaMalformedException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class DnaValidator {

    private Map<String, Function<List<String>, Boolean>> validations;

    @PostConstruct
    private void setup() {
        validations = new HashMap<>();
        validations.put("DNA invalid, verify elements size or values content", this::contentAndSizeValidation);
        validations.put("DNA not present", this::isNotPresent);
    }

    public void validateDna(List<String> dna) {
        validations.forEach((key, validation) -> {
            if (validation.apply(dna)) {
                throw new DnaMalformedException(key);
            }
        } );
    }

    private boolean contentAndSizeValidation(List<String> dna) {
        return !dna.stream()
                .allMatch(s -> s.matches(String.format("^[ATCG]{%d}$", dna.size())));
    }

    private boolean isNotPresent(List<String> dna) {
        return Objects.isNull(dna);
    }
}
