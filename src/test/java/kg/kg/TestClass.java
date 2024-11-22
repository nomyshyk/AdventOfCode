package kg.kg;


import kg.com.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClass {

    @Test
    public void day1Verify() throws FileNotFoundException {
        Day1.execute();
    }

    @Test
    public void day1CalulateTest() throws FileNotFoundException {

        List<Map<String, Integer>> testCases =
                List.of(
                        Map.of("two1nine", 29),
                        Map.of("eightwothree", 83),
                        Map.of("abcone2threexyz", 13),
                        Map.of("xtwone3four", 24),
                        Map.of("4nineeightseven2", 42),
                        Map.of("zoneight234", 14),
                        Map.of("7pqrstsixteen", 76)
                        );

        for (Map<String, Integer> cases : testCases) {
            for(Map.Entry<String, Integer> entry: cases.entrySet()) {
                assertTrue(Day1.calculate(entry.getKey()) == entry.getValue());
            }
        }
    }

    @Test
    public void day5Test() throws FileNotFoundException {
        Day5.execute();
    }

    @Test
    public void day6Test() throws FileNotFoundException {
        Day6.execute();
    }

    @Test
    public void day7Test_2() throws FileNotFoundException {
        assertEquals(Day7.executePart2(), 253253225);
    }

    @Test
    public void day7Test_1() throws FileNotFoundException {
        assertEquals(Day7.executePart1(), 253638586);
    }
}
