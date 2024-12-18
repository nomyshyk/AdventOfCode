package kg.kg;

import kg.com.Day14;
import kg.com.FileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay14 {

    @BeforeEach
    public void init() {
    }

    @Test
    public void day14Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day14_p1_1.txt");
        assertEquals(Day14.executePart1(strList), 136L);
    }

//    @Test
//    public void day14Part1_Test_2() {
//        List<String> strList = FileLoader.inputLines("input_day14_p2_1.txt");
//        assertEquals(Day14.executePart1(strList), 37718);
//    }
}
