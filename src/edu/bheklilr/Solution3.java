package edu.bheklilr;

public class Solution3 implements Solution {

    private static final int INPUT = 265149;

    @Override
    public void solve() {
        System.out.println("Part 1: " + solvePart1());
        System.out.println("Part 2: " + solvePart2());
    }

    private int solvePart2() {
        int sideLength = (int) Math.ceil(Math.sqrt((double) INPUT));
        if (sideLength % 2 == 0) {
            sideLength++;
        }
        int[][] grid = new int[sideLength][sideLength];
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                grid[i][j] = 0;
            }
        }

        // (0, 0) is grid[0][0], so we're starting in the middle
        int x = sideLength / 2;
        int y = x;

        grid[x][y] = 1;

        char direction = 'r';
        int currSideLength = 1;

        int lastValue = 1;

        while (lastValue < INPUT) {
            // TODO: The rest of the problem
        }

        return lastValue;
    }

    private int solvePart1() {
        // As the square grows, the number in the bottom right corner will always be a square number equal
        // to the side length squared.  This greatly constrains where our number will be
        int sideLength = (int) Math.ceil(Math.sqrt((double) INPUT));
        if (sideLength % 2 == 0) {
            sideLength++;
        }
        int current = sideLength * sideLength;
        // Our number will always be on the outer side, so now it will be reasonably efficient to just
        // loop over the outer side to find it's position
        int x = sideLength / 2;
        int y = -x;
        // This is a dumb solution, but will work.  There are 4 variants of this loop, one for each direction.
        // MOVE LEFT
        for (int i = 0; i < sideLength; i++) {
            if (current == INPUT) {
                return Math.abs(x) + Math.abs(y);
            }
            x--;
            current--;
        }
        // MOVE UP
        for (int i = 0; i < sideLength; i++) {
            if (current == INPUT) {
                return Math.abs(x) + Math.abs(y);
            }
            y++;
            current--;
        }
        // MOVE RIGHT
        for (int i = 0; i < sideLength; i++) {
            if (current == INPUT) {
                return Math.abs(x) + Math.abs(y);
            }
            x++;
            current--;
        }
        // MOVE DOWN
        for (int i = 0; i < sideLength - 1; i++) {
            if (current == INPUT) {
                return Math.abs(x) + Math.abs(y);
            }
            y--;
            current--;
        }
        return -1;
    }
}
