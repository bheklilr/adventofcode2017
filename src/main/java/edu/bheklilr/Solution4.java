package edu.bheklilr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution4 implements Solution {
    private static final Path INPUT_PATH = Paths.get("inputs/04ms.txt");

    @Override
    public void solve() {
        try (BufferedReader br = new BufferedReader((new FileReader(INPUT_PATH.toFile())))) {
            int part1 = 0, part2 = 0;
            for (String line : (Iterable<String>) br.lines()::iterator) {
                if (hasUniqueWords(line)) {
                    part1++;
                    if (hasNoAnagrams(line))
                        part2++;
                }
            }
            System.out.println("Part 1: " + part1);
            System.out.println("Part 2: " + part2);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
