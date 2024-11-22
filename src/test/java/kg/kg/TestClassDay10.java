package kg.kg;


import kg.com.Day10;
import kg.com.Day9;
import kg.com.FileLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay10 {

    @Test
    public void day10Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day10_1.txt");
        assertEquals(Day10.executePart1(strList), 4L);
    }

    @Test
    public void day10Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day10_2.txt");
        assertEquals(Day10.executePart1(strList), 4L);
    }

    @Test
    public void day10Part1_Test_3() {
        List<String> strList = FileLoader.inputLines("input_day10_3.txt");
        assertEquals(Day10.executePart1(strList), 8L);
    }

    @Test
    public void day10Part1_Test_4() {
        List<String> strList = FileLoader.inputLines("input_day10_4.txt");
        assertEquals(Day10.executePart1(strList), 7086L);
    }

    @Test
    public void day10Part2_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day10_1p2.txt");
        assertEquals(Day10.executePart2(strList), 4L);
    }

//    @Test
//    public void day10Part2_Test_2() {
//        List<String> strList = FileLoader.inputLines("input_day10_2p2.txt");
//        assertEquals(Day10.executePart2(strList), 8L);
//    }

    @Test
    public void day10Part2_Test_3() {
        List<String> strList = FileLoader.inputLines("input_day10_3p2.txt");
        assertEquals(Day10.executePart2(strList), 10L);
    }

    @Test
    public void day10Part2_Test_4() {
        List<String> strList = FileLoader.inputLines("input_day10_4.txt");
        assertEquals(Day10.executePart2(strList), 317L);
    }
}
