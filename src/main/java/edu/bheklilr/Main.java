package edu.bheklilr;

import java.util.Calendar;

public class Main {

    private static final Solution[] SOLUTIONS = new Solution[] {
            new Solution01(),
            new Solution02(),
            new Solution03(),
            new Solution04(),
            new Solution05(),
            new Solution06(),
            new Solution07(),
            new Solution08(),
            new Solution09(),
            new Solution10(),
            new Solution11(),
            new Solution12(),
            new Solution13(),
            new Solution14(),
            new Solution15(),
            new Solution16(),
            new Solution17(),
            new Solution18(),
            new Solution19(),
            new Solution20(),
            new Solution21(),
            new Solution22(),
            new Solution23(),
            new Solution24(),
            new Solution25(),
    };

    public static void main(String[] args) {
        int millisecond = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        try {
            millisecond = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            // use current day
        }
	    SOLUTIONS[millisecond - 1].solve();
    }
}
