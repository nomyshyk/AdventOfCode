package kg.kg;


import kg.com.Day11;
import kg.com.FileLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay11 {

    @Test
    public void day11Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day11_p1_1.txt");
        assertEquals(Day11.executePart1(strList, 1), 374L);
    }

    @Test
    public void day11Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day11_p1_2.txt");
        assertEquals(Day11.executePart1(strList, 1), 9918828L);
    }

    @Test
    public void day11Part2_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day11_p1_1.txt");
        assertEquals(Day11.executePart1(strList, 9), 1030L);
    }

    @Test
    public void day11Part2_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day11_p1_1.txt");
        assertEquals(Day11.executePart1(strList, 99), 8410L);
    }

    @Test
    public void day11Part2_Test_3() {
        List<String> strList = FileLoader.inputLines("input_day11_p1_1.txt");
        assertEquals(Day11.executePart1(strList, 999999), 82000210L);
    }

    @Test
    public void day11Part2_Test_4() {
        List<String> strList = FileLoader.inputLines("input_day11_p1_2.txt");
        assertEquals(Day11.executePart1(strList, 999999), 692506533832L);
    }
}
