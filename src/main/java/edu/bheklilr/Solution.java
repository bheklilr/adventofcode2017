package edu.bheklilr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class Solution<T> {
    static Stream<String> getInputLines(String name) {
        Path inputPath = Paths.get("inputs/" + name + ".txt");
        try (BufferedReader br = new BufferedReader(Files.newBufferedReader(inputPath))) {
            // Since lines is lazy, this has to be fully read,
            // converted to a list, then back to a stream
            return br.lines().collect(Collectors.toList()).stream();
        } catch (IOException e) {
            e.printStackTrace();
            Stream.Builder<String> streamBuilder = Stream.builder();
            return streamBuilder.build();
        }
    }

    abstract T solvePart1();

    abstract T solvePart2();
}
