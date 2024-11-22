package kg.kg;


import kg.com.Day9;
import kg.com.FileLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay9 {

    @Test
    public void day9Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day9_1.txt");
        assertEquals(Day9.executePart1(strList), 114L);
    }

    @Test
    public void day9Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day9_2.txt");
        assertEquals(Day9.executePart1(strList), 1916822650L);
    }

    @Test
    public void day9Part2_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day9_1.txt");
        assertEquals(Day9.executePart2(strList), 2L);
    }

    @Test
    public void day9Part2_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day9_2.txt");
        assertEquals(Day9.executePart2(strList), 966);
    }
}
