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

public class Solution06 implements Solution<Integer> {
    private static final Path INPUT_PATH = Paths.get("inputs/06ms.txt");

    private List<Bank> banks;

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
    public Integer solvePart2() {
        Bank bankSeen = banks.get(banks.size() - 1);
        return banks.lastIndexOf(bankSeen) - banks.indexOf(bankSeen);
    }

    @Override
    public Integer solvePart1() {
        Bank bank = getInput();
        banks = new ArrayList<>();

        int iterations = 0;
        while (!banks.contains(bank)) {
            banks.add(bank);
            bank = bank.redistribute();

            iterations += 1;
            if (iterations > 100000)
                break;
        }
        banks.add(bank);
        return iterations;
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
