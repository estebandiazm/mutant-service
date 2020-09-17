package com.marvel.mutanservice.bussiness;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MutantDetectorImpl implements MutantDetector {

    @Value("${mutant.config.sequenceSize}")
    private int sequenceSize;
    @Value("${mutant.config.minimumSequencesMatched}")
    private int minimumSequencesMatched;
    private AtomicInteger matchCounter;

    public boolean isMutant(List<String> dna) {

        matchCounter = new AtomicInteger(0);

        buildScans().stream()
                .takeWhile(consumer -> matchCounter.get() < minimumSequencesMatched)
                .forEach(scan -> scan.accept(dna));

        return matchCounter.compareAndSet(minimumSequencesMatched, 0);
    }

    private List<Consumer<List<String>>> buildScans() {
        return Arrays.asList(
                this::executeSequenceScan,
                dna -> executeSequenceScan(buildVerticalAdn(dna)),
                dna -> executeSequenceScan(buildDiagonals(dna)),
                dna -> executeSequenceScan(buildDiagonals(buildVerticalAdn(dna)))
        );
    }

    private List<String> buildDiagonals(List<String> dna) {

        IntFunction<String> getDiagonal = row -> IntStream.range(0, dna.size() - Math.abs(row))
                .mapToObj(column ->
                        dna.get(row < 0 ? column : Math.abs(row) + column)
                                .toCharArray()[row < 0 ? Math.abs(row) + column : column]
                )
                .map(Object::toString)
                .collect(Collectors.joining());

        return IntStream.range(Math.negateExact(dna.size() - sequenceSize), dna.size())
                .takeWhile(row -> dna.size() - Math.abs(row) >= sequenceSize)
                .mapToObj(getDiagonal)
                .collect(Collectors.toList());
    }

    private List<String> buildVerticalAdn(List<String> dna) {
        List<String> dnaVerticallySorted = new ArrayList<>();

        dna.forEach(sequence -> IntStream.range(0, sequence.length())
                .forEach(index -> addCharacterToList(dnaVerticallySorted, sequence, index)));
        return dnaVerticallySorted;
    }

    private void addCharacterToList(List<String> dnaVerticallySorted, String sequence, int value) {
        char[] chars = sequence.toCharArray();
        if (dnaVerticallySorted.size() < chars.length) {
            dnaVerticallySorted.add(String.valueOf(sequence.charAt(value)));
        } else {
            dnaVerticallySorted
                    .set(value, dnaVerticallySorted.get(value).concat(String.valueOf(sequence.charAt(value))));
        }
    }

    private void executeSequenceScan(List<String> dna) {
        dna.stream()
                .takeWhile(sequence -> matchCounter.get() < minimumSequencesMatched)
                .forEach(this::executeSequenceScan);
    }

    private void executeSequenceScan(String sequence) {
        if (isMutantSequence(sequence)) {
            matchCounter.getAndIncrement();
        }
    }

    private boolean isMutantSequence(String sequence) {
        var chars = sequence.toCharArray();
        return sequenceMatch(chars, 0);
    }

    private boolean sequenceMatch(char[] chars, int index) {

        var sequenceSalt = sequenceSize - 1;
        var externalLimit = index + sequenceSalt;
        if (externalLimit < chars.length) {
            var charIndex = chars[index];
            if (charIndex == chars[externalLimit]) {
                return validateInternalSequence(chars, charIndex, index, externalLimit);
            }
            return sequenceMatch(chars, index + 1);
        }
        return false;
    }

    private boolean validateInternalSequence(char[] chars, char charIndex, int index, int externalLimit) {
        var allMatch = true;

        while (index < externalLimit && allMatch) {
            allMatch = charIndex == chars[++index];
        }
        return allMatch;
    }
}
