package kg.kg;


import kg.com.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClassDay8 {

    @Test
    public void day8Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day8_1.txt");
        assertEquals(Day8.executePart1(strList), 2);
    }

    @Test
    public void day8Part1Test_2() {
        List<String> strList = FileLoader.inputLines("input_day8_2.txt");
        assertEquals(Day8.executePart1(strList), 6);
    }

    @Test
    public void day8Part1Test_3() {
        List<String> strList = FileLoader.inputLines("input_day8_3.txt");
        assertEquals(Day8.executePart1(strList), 16697);
    }

    @Test
    public void day8Part2_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day8_2_1.txt");
        assertEquals(Day8.executePart2(strList), BigInteger.valueOf(6));
    }

    @Test
    public void day8Part2_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day8_3.txt");
        assertEquals(Day8.executePart2(strList), BigInteger.valueOf(10668805667831L));

    }

    @Test
    public void day8Part2_Test_gcm() {
        assertEquals(Day8.lcm(BigInteger.valueOf(42), BigInteger.valueOf(60)), BigInteger.valueOf(420));
    }
}
