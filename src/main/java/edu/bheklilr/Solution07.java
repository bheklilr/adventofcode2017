package edu.bheklilr;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Solution07 implements Solution<String> {
    private static Path INPUT_PATH = Paths.get("inputs/07ms.txt");

    private static List<Entry> getEntries() {
        try (BufferedReader br = new BufferedReader(Files.newBufferedReader(INPUT_PATH))) {
            return br.lines().map(Entry::parseLine).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static Tower findUnbalancingTower(Tower node) {
        if (node.isBalanced()) {
            throw new ValueException("Node " + node.getName() + " is balanced");
        }
        for (Tower child : node.getChildren()) {
            if (!child.isBalanced()) {
                return findUnbalancingTower(child);
            }
        }
        return node;
    }

    @Override
    public String solvePart1() {
        List<Entry> entries = getEntries();
        Map<String, Boolean> hasParent = new HashMap<>();
        for (Entry entry : entries) {
            hasParent.putIfAbsent(entry.name, false);
            for (String child : entry.children) {
                hasParent.put(child, true);
            }
        }
        for (Map.Entry<String, Boolean> entry : hasParent.entrySet()) {
            if (!entry.getValue()) {
                return entry.getKey();
            }
        }
        return "None";
    }

    @Override
    public String solvePart2() {
        List<Entry> entries = getEntries();
        Map<String, List<String>> childMap = new HashMap<>();
        Map<String, Tower> flatNodes = new HashMap<>();
        for (Entry e : entries) {
            flatNodes.put(e.name, new Tower(e.name, e.weight));
        }
        for (Entry e : entries) {
            Tower node = flatNodes.get(e.name);
            for (String childName : e.children) {
                node.addChild(flatNodes.get(childName));
            }
        }
        Tower bottom = flatNodes.get(solvePart1());
        Tower parentOfUnbalancingTower = findUnbalancingTower(bottom);
        List<Integer> childTotalWeights = parentOfUnbalancingTower.getChildrenTotalWeights();
        Integer minWeight = Collections.min(childTotalWeights);
        Integer maxWeight = Collections.max(childTotalWeights);
        Integer diff = maxWeight - minWeight;
        for (Tower child : parentOfUnbalancingTower.getChildren()) {
            if (child.getTotalWeight().equals(maxWeight)) {
                return String.format("%d", child.getWeight() - diff);
            }
        }
        return "0";
    }

    static class Entry {
        public final String name;
        public final Integer weight;
        public final List<String> children;

        public Entry(String name, Integer weight, List<String> children) {
            this.name = name;
            this.weight = weight;
            this.children = children;
        }

        public static Entry parseLine(String line) {
            String name;
            Integer weight;
            List<String> children = new ArrayList<>();

            String[] parts = line.split(" -> ");
            String[] parentInfo = parts[0].split(" ");
            name = parentInfo[0];
            weight = Integer.parseInt(parentInfo[1].replace("(", "").replace(")", ""));
            if (parts.length > 1) {
                String[] childrenNames = parts[1].split(", ");
                children = Arrays.asList(childrenNames);
            }
            return new Entry(name, weight, children);
        }
    }

    static class Tower {
        private String name;
        private Integer weight;
        private List<Tower> children;

        public Tower(String name, Integer weight) {
            this.name = name;
            this.weight = weight;
            this.children = new ArrayList<>();
        }

        private static void recursiveToString(StringBuilder sb, int level, Tower tower) {
            for (int i = 0; i < level; i++) sb.append("\t");
            sb.append(tower.getName());
            sb.append(" (");
            sb.append(tower.getWeight());
            sb.append(") - (");
            sb.append(tower.getTotalWeight());
            sb.append("): ");
            sb.append(tower.isBalanced() ? "balanced" : "unbalanced");
            sb.append(":\n");
            for (Tower child : tower.getChildren()) {
                recursiveToString(sb, level + 1, child);
            }
        }

        public String getName() {
            return name;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public List<Tower> getChildren() {
            return children;
        }

        public void addChild(Tower child) {
            this.children.add(child);
        }

        public Integer getTotalWeight() {
            return getChildrenTotalWeights().stream().reduce(getWeight(), (x, y) -> x + y);
        }

        public List<Integer> getChildrenTotalWeights() {
            return getChildren().stream().map(t -> t.getTotalWeight()).collect(Collectors.toList());
        }

        public boolean isBalanced() {
            return (new HashSet<Integer>(getChildrenTotalWeights())).size() < 2;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            recursiveToString(sb, 0, this);
            return sb.toString();
        }
    }
}
