package edu.bheklilr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution05 implements Solution {
    private static final Path INPUT_PATH = Paths.get("inputs/05ms.txt");

    private static List<Integer> getInput() {
        try (BufferedReader br = Files.newBufferedReader(INPUT_PATH)) {
            return br.lines().map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void solve() {
        System.out.println("Part 1: " + solvePart1());
        System.out.println("Part 2: " + solvePart2());
    }

    private long solvePart1() {
        long steps = 0;
        int position = 0;
        List<Integer> input = getInput();
        while (position >= 0 && position < input.size()) {
            int currentJump = input.get(position);
            input.set(position, currentJump + 1);
            position += currentJump;
            steps += 1;
        }
        return steps;
    }

    private long solvePart2() {
        long steps = 0;
        int position = 0;
        List<Integer> input = getInput();
        while (position >= 0 && position < input.size()) {
            int currentJump = input.get(position);
            if (currentJump >= 3)
                input.set(position, currentJump - 1);
            else
                input.set(position, currentJump + 1);
            position += currentJump;
            steps += 1;
        }
        return steps;
    }
}
