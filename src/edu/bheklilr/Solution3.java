package edu.bheklilr;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Solution3 implements Solution {

    private static final int INPUT = 265149;

    @Override
    public void solve() {
        System.out.println("Part 1: " + solvePart1());
        System.out.println("Part 2: " + solvePart2());
    }

    private class Pair<L, R> {
        private final L left;
        private final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }

        @Override
        public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair other = (Pair) o;
            return getLeft() == other.getLeft() && getRight() == other.getRight();
        }
    }

    private int solvePart2() {
        int _sideLength = (int) Math.ceil(Math.sqrt((double) INPUT));
        if (_sideLength % 2 == 0) {
            _sideLength++;
        }
        final int sideLength = _sideLength;
        int[][] grid = new int[sideLength][sideLength];
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                grid[i][j] = 0;
            }
        }

        // (0, 0) is grid[0][0], so we're starting in the middle
        final int origin = sideLength / 2;
        int x = origin;
        int y = origin;

        grid[x][y] = 1;
        x++;

        char direction = 'u';
        int lastValue = 1;

        Function<Integer, Integer> clip =
                _x -> Math.max(Math.min(_x, sideLength - 1), 0);
        BiFunction<Integer, Integer, Pair<Integer, Integer>> mkpair =
                (_x, _y) -> new Pair<>(clip.apply(_x), clip.apply(_y));

        while (lastValue < INPUT) {
            // Determine the sum at (x, y)
            int localSum = getLocalSum(grid, getAdjacentIdxs(x, y, mkpair));
            grid[x][y] = localSum;
            lastValue = localSum;
            System.out.format("%s (%d, %d): %d\n", direction, x - origin, y - origin, lastValue);

            // Update the current position, checking if a direction change is needed
            switch (direction) {
                case 'u':
                    if (grid[x - 1][y] == 0) direction = 'l';
                    break;
                case 'd':
                    if (grid[x + 1][y] == 0) direction = 'r';
                    break;
                case 'l':
                    if (grid[x][y - 1] == 0) direction = 'd';
                    break;
                case 'r':
                    if (grid[x][y + 1] == 0) direction = 'u';
                    break;
            }
            switch (direction) {
                case 'u':
                    y++;
                    break;
                case 'd':
                    y--;
                    break;
                case 'l':
                    x--;
                    break;
                case 'r':
                    x++;
                    break;
            }
        }
        return lastValue;
    }

    private int getLocalSum(int[][] grid, Set<Pair<Integer, Integer>> idxs) {
        int localSum = 0;
        for (Pair<Integer, Integer> idx : idxs) {
            localSum += grid[idx.getLeft()][idx.getRight()];
        }
        return localSum;
    }

    @NotNull
    private Set<Pair<Integer, Integer>> getAdjacentIdxs(int x, int y, BiFunction<Integer, Integer, Pair<Integer, Integer>> mkpair) {
        Set<Pair<Integer, Integer>> idxs = new HashSet<>();
        // This is about as much code as using 2 for loops
        idxs.add(mkpair.apply(x - 1, y - 1));
        idxs.add(mkpair.apply(x + 0, y - 1));
        idxs.add(mkpair.apply(x + 1, y - 1));
        idxs.add(mkpair.apply(x - 1, y + 0));
        idxs.add(mkpair.apply(x + 1, y + 0));
        idxs.add(mkpair.apply(x - 1, y + 1));
        idxs.add(mkpair.apply(x + 0, y + 1));
        idxs.add(mkpair.apply(x + 1, y + 1));
        return idxs;
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
