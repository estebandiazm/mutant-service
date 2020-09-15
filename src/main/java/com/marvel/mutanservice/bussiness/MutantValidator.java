package com.marvel.mutanservice.bussiness;

import java.util.List;

public class MutantValidator {

    private int sequenceSize = 5;

    public boolean isMutant(List<String> adn) {
        return adn.stream()
                .anyMatch(this::find);
    }

    private boolean find(String sequence) {
        char[] chars = sequence.toCharArray();
        return sequenceMatch(chars, 0);
    }

    private boolean sequenceMatch(char[] chars, int index) {

        int sequenceSalt = sequenceSize - 1;
        int externalLimit = index + sequenceSalt;
        if (externalLimit < chars.length) {
            char charIndex = chars[index];
            if (charIndex == chars[externalLimit]) {
                return validateInternalSequence(chars, charIndex, index, externalLimit);
            }
            return sequenceMatch(chars, index + 1);
        }
        return false;
    }

    private boolean validateInternalSequence(char[] chars, char charIndex, int index, int externalLimit) {
        boolean allMatch = true;

        while (index < externalLimit && allMatch) {
            allMatch = charIndex == chars[++index];
        }
        return allMatch;
    }
}
