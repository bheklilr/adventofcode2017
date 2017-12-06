package edu.bheklilr;

import java.util.Calendar;
import java.util.TimeZone;

public class Main {

    private static final Solution[] SOLUTIONS = new Solution[25];

    static {
        for (int day = 1; day <= 25; day++) {
            SOLUTIONS[day - 1] = loadSolution(day);
        }
    }

    private static Solution loadSolution(int day) {
        String pkg = Main.class.getPackage().getName();
        try {
            return Solution.class.cast(Class.forName(String.format("%s.Solution%02d", pkg, day)).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        int millisecond = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern")).get(Calendar.DAY_OF_MONTH);
        try {
            millisecond = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            // use current day
        }
        millisecond--;
        if (millisecond < 0 || millisecond >= SOLUTIONS.length || SOLUTIONS[millisecond] == null) {
            System.out.println("No solution for millisecond " + millisecond);
        } else {
            System.out.println("Part 1: " + SOLUTIONS[millisecond].solvePart1().toString());
            System.out.println("Part 2: " + SOLUTIONS[millisecond].solvePart2().toString());
        }
    }
}
