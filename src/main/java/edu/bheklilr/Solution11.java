package edu.bheklilr;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution11 extends Solution<Integer> {

    private static List<Direction> getInputDirections() {
        return Direction.parseStream(getInputLines("11ms").flatMap(line -> Arrays.stream(line.split(","))));
    }

    @Override
    Integer solvePart1() {
        Location l = new Location();
        List<Direction> input = getInputDirections();
        for (Direction d : input) l.move(d);
        return l.distanceFromOrigin();
    }

    @Override
    Integer solvePart2() {
        Location l = new Location();
        int maxDistance = 0;
        List<Direction> input = getInputDirections();
        for (Direction d : input) {
            l.move(d);
            maxDistance = Math.max(maxDistance, l.distanceFromOrigin());
        }
        return maxDistance;
    }

    enum Direction {
        N,
        S,
        NW,
        NE,
        SW,
        SE;

        static Optional<Direction> parse(String input) {
            input = input.toLowerCase().trim();
            switch (input) {
                case "n":
                    return Optional.of(N);
                case "s":
                    return Optional.of(S);
                case "nw":
                    return Optional.of(NW);
                case "ne":
                    return Optional.of(NE);
                case "sw":
                    return Optional.of(SW);
                case "se":
                    return Optional.of(SE);
                default:
                    return Optional.empty();
            }
        }

        static List<Direction> parseStream(Stream<String> input) {
            return input
                    .map(Direction::parse)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    static class Location {
        int x = 0;
        int y = 0;

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        void move(Direction d) {
            switch (d) {
                case N:
                    y++;
                    break;
                case S:
                    y--;
                    break;
                case NW:
                    if (Math.abs(x) % 2 == 0) y++;
                    x--;
                    break;
                case NE:
                    if (Math.abs(x) % 2 == 0) y++;
                    x++;
                    break;
                case SW:
                    if (Math.abs(x) % 2 == 1) y--;
                    x--;
                    break;
                case SE:
                    if (Math.abs(x) % 2 == 1) y--;
                    x++;
                    break;
            }
        }

        int distanceFromOrigin() {
            Direction towardsXAxis;
            if (x > 0) {
                if (y > 0) towardsXAxis = Direction.SW;
                else towardsXAxis = Direction.NW;
            } else {
                if (y > 0) towardsXAxis = Direction.SE;
                else towardsXAxis = Direction.NE;
            }
            Location l = new Location();
            l.x = this.x;
            l.y = this.y;

            int i = 0;
            while (l.y != 0 && l.x != 0) {
                l.move(towardsXAxis);
                i++;
            }

            return i + Math.abs(l.x) + Math.abs(l.y);
        }
    }
}
