package edu.bheklilr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution10 extends Solution<Integer> {

    private static Integer[] INPUT = {165, 1, 255, 31, 87, 52, 24, 113, 0, 91, 148, 254, 158, 2, 73, 153};
    private static Integer[] RANGE = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
            64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
            96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127,
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159,
            160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191,
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223,
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255
    };

    @Override
    Integer solvePart1() {
        CircularArrayList<Integer> list = new CircularArrayList<>();
        list.addAll(Arrays.asList(RANGE));

        int position = 0;
        int skip = 0;

        for (int length : INPUT) {
            List<Integer> selection = new ArrayList<>();
            selection.addAll(list.subList(position, position + length));
            Collections.reverse(selection);
            for (int i = 0; i < length; i++) {
                list.set(i + position, selection.get(i));
            }
            position = (position + length + skip) % list.size();
            skip++;
        }
        return list.get(0) * list.get(1);
    }

    @Override
    Integer solvePart2() {
        CircularArrayList<Integer> list = new CircularArrayList<>();
        list.addAll(Arrays.asList(RANGE));

        List<Integer> lengths = new ArrayList<>();
        final byte[] input = "165,1,255,31,87,52,24,113,0,91,148,254,158,2,73,153".getBytes();
        for (int i = 0; i < input.length; i++) {
            lengths.add((int) input[i]);
        }
        lengths.addAll(Arrays.asList(17, 31, 73, 47, 23));

        final int rounds = 64;

        int position = 0;
        int skip = 0;

        for (int round = 0; round < rounds; round++) {
            for (int length : lengths) {
                List<Integer> selection = new ArrayList<>();
                selection.addAll(list.subList(position, position + length));
                Collections.reverse(selection);
                for (int i = 0; i < length; i++) {
                    list.set(i + position, selection.get(i));
                }
                position = (position + length + skip) % list.size();
                skip++;
            }
        }

        List<Integer> dense = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int accum = 0;
            for (int j = 0; j < 16; j++) {
                accum ^= list.get(16 * i + j);
            }
            dense.add(accum);
        }
        for (Integer c : dense) {
            System.out.format("%02x", c);
        }
        System.out.println();
        return 0;
    }

    static class CircularArrayList<E> extends ArrayList<E> {
        @Override
        public E set(int i, E e) {
            return super.set(i % this.size(), e);
        }

        @Override
        public E get(int index) {
            return super.get(index % this.size());
        }

        @Override
        public List<E> subList(int start, int stop) {
            List<E> sub = new CircularArrayList<>();
            for (int i = start; i < stop; i++) {
                sub.add(this.get(i));
            }
            return sub;
        }
    }
}
