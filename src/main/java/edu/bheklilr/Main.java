package edu.bheklilr;

import org.reflections.Reflections;

import java.util.*;

class Main {

    private static final Map<Integer, Solution> SOLUTIONS;

    static {
        SOLUTIONS = new HashMap<>();
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<? extends Solution>> solutions = reflections.getSubTypesOf(Solution.class);
        for (Class<? extends Solution> s : solutions) {
            int day = Integer.parseInt(s.getSimpleName().replace("Solution", ""));
            try {
                SOLUTIONS.put(day, s.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int day = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern")).get(Calendar.DAY_OF_MONTH);
        try {
            day = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            // pass
        }
        List<Integer> daysToRun = new ArrayList<>();
        if (day <= 0) {
            Object[] keys = SOLUTIONS.keySet().toArray();
            Arrays.sort(keys);
            for (Object key : keys) {
                daysToRun.add((Integer) key);
            }
        } else {
            daysToRun.add(day);
        }

        for (int dayToRun : daysToRun) {
            if (SOLUTIONS.containsKey(dayToRun)) {
                Solution s = SOLUTIONS.get(dayToRun);
                System.out.format("Solution for day %d\n", dayToRun);
                System.out.println("  Part 1: " + s.solvePart1().toString());
                System.out.println("  Part 2: " + s.solvePart2().toString());
            } else {
                System.out.println("No solution for day " + dayToRun);
            }
        }
    }
}
