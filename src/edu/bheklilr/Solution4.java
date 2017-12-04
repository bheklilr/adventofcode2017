package edu.bheklilr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution4 implements Solution {
    private static final Path INPUT_PATH = Paths.get("inputs/04ms.txt");

    @Override
    public void solve() {
        solvePart1();
        solvePart2();
    }

    private void solvePart2() {
        try (BufferedReader br = new BufferedReader((new FileReader(INPUT_PATH.toFile())))) {
            System.out.println(passphrasesWithNoAnagrams(passphrasesWithUniqueWords(br.lines())).count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solvePart1() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_PATH.toFile()))) {
            System.out.println(passphrasesWithUniqueWords(br.lines()).count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stream<String> passphrasesWithUniqueWords(Stream<String> passphrases) {
        return passphrases.filter(this::hasUniqueWords);
    }

    private Stream<String> passphrasesWithNoAnagrams(Stream<String> passphrases) {
        return passphrases.filter(this::hasNoAnagrams);
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
