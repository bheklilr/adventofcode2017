package edu.bheklilr;

import java.util.Calendar;

public class Main {

    private static final Solution[] SOLUTIONS = new Solution[] {
            new Solution1(),
            new Solution2(),
            new Solution3(),
            new Solution4(),
            new Solution5(),
            new Solution6(),
            new Solution7(),
            new Solution8(),
            new Solution9(),
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
