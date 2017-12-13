package edu.bheklilr;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution04 extends Solution<Integer> {
    private int part1 = 0;
    private int part2 = 0;
    private boolean hasBeenSolved = false;

    @Override
    public Integer solvePart1() {
        if (!hasBeenSolved)
            solve();
        return part1;
    }

    @Override
    public Integer solvePart2() {
        if (!hasBeenSolved)
            solve();
        return part2;
    }

    private void solve() {
        for (String line : (Iterable<String>) getInputLines("04ms")::iterator) {
            if (hasUniqueWords(line)) {
                part1++;
                if (hasNoAnagrams(line))
                    part2++;
            }
        }
        hasBeenSolved = true;
    }

    private boolean hasNoAnagrams(String passphrase) {
        String[] words = passphrase.split(" ");
        Set<String> sortedWords = Arrays.stream(words).map(word -> {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }).collect(Collectors.toSet());
        return words.length == sortedWords.size();
    }

    private boolean hasUniqueWords(String passphrase) {
        String[] words = passphrase.split(" ");
        Set<String> uniqueWords = Arrays.stream(words).collect(Collectors.toSet());
        return words.length == uniqueWords.size();
    }
}
