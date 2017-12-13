package edu.bheklilr;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
class Solution12 extends Solution<Integer> {

    private static Set<Integer> findConnected(Integer to, int[][] graph) {
        Set<Integer> connected = new HashSet<>();
        return findConnected(to, graph, connected);
    }

    private static Set<Integer> findConnected(Integer to, int[][] graph, Set<Integer> connected) {
        for (int i = 0; i < graph[to].length; i++) {
            if (graph[to][i] == 1) {
                List<Integer> scanNext = new ArrayList<>();
                if (!connected.contains(i))
                    scanNext.add(i);
                connected.add(i);
                for (Integer next : scanNext) {
                    connected.addAll(findConnected(next, graph, connected));
                }
            }
        }
        return connected;
    }

    private static int connectedNodes(Integer to, int[][] graph) {
        return findConnected(to, graph).size();
    }

    private Map<Integer, List<Integer>> getInputAssociations() {
        return getInputLines("12ms")
                .collect(Collectors.toMap(
                        line -> Integer.parseInt(line.split(" <-> ")[0]),
                        line -> {
                            List<Integer> vals = new ArrayList<>();
                            for (String n : line.split(" <-> ")[1].split(", ")) {
                                vals.add(Integer.parseInt(n));
                            }
                            return vals;
                        }
                ));
    }

    private int[][] getInputGraph() {
        Map<Integer, List<Integer>> assocs = getInputAssociations();
        int maxSize = Collections.max(assocs.keySet()) + 1;
        int[][] graph = new int[maxSize][maxSize];
        for (Map.Entry<Integer, List<Integer>> entry : assocs.entrySet()) {
            for (Integer assoc : entry.getValue()) {
                graph[entry.getKey()][assoc] = 1;
            }
            graph[entry.getKey()][entry.getKey()] = 1;
        }
        return graph;
    }

    @Override
    Integer solvePart1() {
        return connectedNodes(0, getInputGraph());
    }

    @Override
    Integer solvePart2() {
        int numGroups = 0;
        int[][] graph = getInputGraph();
        Set<Integer> nodesToSearch = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            nodesToSearch.add(i);
        }
        while (nodesToSearch.size() > 0) {
            nodesToSearch.removeAll(findConnected(nodesToSearch.iterator().next(), graph));
            numGroups++;
        }
        return numGroups;
    }
}
