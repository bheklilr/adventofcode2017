package edu.bheklilr;

import org.reflections.Reflections;

import java.util.*;

public class Main {

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
        int millisecond = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern")).get(Calendar.DAY_OF_MONTH);
        try {
            millisecond = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            // use current day
        }
        millisecond--;
        if (SOLUTIONS.containsKey(millisecond)) {
            Solution s = SOLUTIONS.get(millisecond);
            System.out.format("Solution for %dms\n", millisecond + 1);
            System.out.println("  Part 1: " + s.solvePart1().toString());
            System.out.println("  Part 2: " + s.solvePart2().toString());
        } else {
            System.out.println("No solution for millisecond " + (millisecond + 1));
        }
    }
}
