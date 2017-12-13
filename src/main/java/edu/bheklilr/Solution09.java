package edu.bheklilr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class Solution09 extends Solution<Long> {


    @Override
    Long solvePart1() {
        try (InputStream stream = Files.newInputStream(Paths.get("inputs/09ms.txt"))) {
            long score = 0;
            long groupLevel = 0;
            boolean inGarbage = false;

            char b;
            while (stream.available() > 0) {
                b = (char) stream.read();
                switch (b) {
                    case '{':
                        if (!inGarbage) {
                            groupLevel++;
                        }
                        break;
                    case '<':
                        inGarbage = true;
                        break;
                    case '>':
                        inGarbage = false;
                        break;
                    case '!':
                        if (inGarbage) {
                            // Increment the stream pointer
                            //noinspection ResultOfMethodCallIgnored
                            stream.read();
                        }
                        break;
                    case '}':
                        if (!inGarbage) {
                            score += groupLevel;
                            groupLevel -= 1;
                        }
                        break;
                }
            }
            return score;
        } catch (IOException e) {
            e.printStackTrace();
            return (long) 0;
        }
    }

    @Override
    Long solvePart2() {
        try (InputStream stream = Files.newInputStream(Paths.get("inputs/09ms.txt"))) {
            long garbageChars = 0;
            boolean inGarbage = false;
            long score = 0;
            long depth = 1;

            char b;
            while (stream.available() > 0) {
                b = (char) stream.read();
                if (b == '!') //noinspection ResultOfMethodCallIgnored
                    stream.read();
                else if (inGarbage && b != '>') garbageChars++;
                else if (b == '<') inGarbage = true;
                else if (b == '>') inGarbage = false;
                else if (b == '{') score += depth++;
                else if (b == '}') depth--;
            }
            return garbageChars;
        } catch (IOException e) {
            e.printStackTrace();
            return (long) 0;
        }
    }
}
