package edu.bheklilr;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution13 extends Solution<Integer> {

    private static int scanner(int height, int time) {
        int offset = time % ((height - 1) * 2);
        return (offset > height - 1)
                ? (2 * (height - 1) - offset)
                : (offset);
    }

    private Map<Integer, Integer> getFirewall() {
        return getInputLines("13ms")
                .map(line -> line.split(": "))
                .map(parts -> new Integer[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])})
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));
    }

    private Stream<Integer> firewallScans(Map<Integer, Integer> firewall, int delay) {
        return firewall.entrySet().stream()
                .map(entry -> scanner(entry.getValue(), entry.getKey() + delay));
    }

    private int tripPenalty(Map<Integer, Integer> firewall) {
        return tripPenalty(firewall, 0);
    }

    private int tripPenalty(Map<Integer, Integer> firewall, int delay) {
        return firewall.entrySet().stream()
                .filter(entry -> scanner(entry.getValue(), entry.getKey() + delay) == 0)
                .map(entry -> entry.getValue() * entry.getKey())
                .reduce(0, (x, y) -> x + y);
    }

    @Override
    Integer solvePart1() {
        return tripPenalty(getFirewall());
    }

    @Override
    Integer solvePart2() {
        Map<Integer, Integer> firewall = getFirewall();
        int delay = 0;
        while (true) {
            if (!firewallScans(firewall, delay).anyMatch(scan -> scan == 0)) return delay;
            delay++;
        }
    }
}
