package edu.bheklilr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Solution05 implements Solution {
    private static final Path INPUT_PATH = Paths.get("inputs/05ms.txt");

    private static int[] getInput() {
        try (BufferedReader br = Files.newBufferedReader(INPUT_PATH)) {
            List<Integer> list = br.lines().map(Integer::parseInt).collect(Collectors.toList());
            int[] input = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                input[i] = list.get(i);
            }
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
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
        int[] input = getInput();
        while (position >= 0 && position < input.length) {
            int currentJump = input[position];
            input[position] = currentJump + 1;
            position += currentJump;
            steps += 1;
        }
        return steps;
    }

    private long solvePart2() {
        long steps = 0;
        int position = 0;
        int[] input = getInput();
        while (position >= 0 && position < input.length) {
            int currentJump = input[position];
            if (currentJump >= 3)
                input[position] = currentJump - 1;
            else
                input[position] = currentJump + 1;
            position += currentJump;
            steps += 1;
        }
        return steps;
    }
}
