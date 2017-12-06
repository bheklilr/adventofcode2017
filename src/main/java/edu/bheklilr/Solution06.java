package edu.bheklilr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution06 implements Solution {
    private static final Path INPUT_PATH = Paths.get("inputs/06ms.txt");

    private Bank getInput() {
        try (BufferedReader br = Files.newBufferedReader(INPUT_PATH)) {
            return new Bank(
                    Arrays.stream(br.readLine().split("\t"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            return new Bank(new ArrayList<>());
        }
    }
    @Override
    public void solve() {
        solvePart2(solvePart1());
    }

    private void solvePart2(List<Bank> banks) {
        Bank bankSeen = banks.get(banks.size() - 1);
        System.out.println("Part 2: " + (banks.lastIndexOf(bankSeen) - banks.indexOf(bankSeen)));
    }

    private List<Bank> solvePart1() {
        Bank bank = getInput();
        List<Bank> seen = new ArrayList<>();

        int iterations = 0;
        while (!seen.contains(bank)) {
            seen.add(bank);
            bank = bank.redistribute();

            iterations += 1;
            if (iterations > 100000)
                break;
        }
        seen.add(bank);
        System.out.println("Part 1: " + iterations);
        return seen;
    }

    private class Bank {
        private List<Integer> values;

        public Bank(List<Integer> vals) {
            values = vals;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Bank)) {
                return false;
            }
            Bank other = (Bank) o;
            if (this.values.size() != other.values.size())
                return false;

            for (int i = 0; i < this.values.size(); i++) {
                if (this.values.get(i) != other.values.get(i))
                    return false;
            }
            return true;
        }

        public Bank redistribute() {
            List<Integer> bank = new ArrayList(values);
            // Find the max
            int idxOfMax = 0;
            int maximum = 0;
            for (int i = 0; i < bank.size(); i++) {
                if (maximum < bank.get(i)) {
                    maximum = bank.get(i);
                    idxOfMax = i;
                }
            }
            // Now redistribute
            bank.set(idxOfMax, 0);
            int idx;
            for (int i = 1; i <= maximum; i++) {
                idx = (idxOfMax + i) % bank.size();
                bank.set(idx, bank.get(idx) + 1);
            }
            return new Bank(bank);
        }

        public void pprint() {
            System.out.println(values);
        }

        @Override
        public String toString() {
            return this.values.toString();
        }
    }
}
