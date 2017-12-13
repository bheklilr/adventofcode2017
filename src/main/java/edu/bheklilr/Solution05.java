package edu.bheklilr;

import java.util.List;
import java.util.stream.Collectors;

class Solution05 extends Solution<Long> {
    private static int[] getInput() {
        List<Integer> list = getInputLines("05ms")
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int[] input = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            input[i] = list.get(i);
        }
        return input;
    }

    public Long solvePart1() {
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

    public Long solvePart2() {
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
