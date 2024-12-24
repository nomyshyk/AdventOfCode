package kg.kg;

import kg.com.Day15;
import kg.com.FileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay15 {

    @BeforeEach
    public void init() {
    }

    @Test
    public void day15Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day15_p1_1.txt");
        assertEquals(Day15.executePart1(strList), 1320);
    }

    @Test
    public void day15Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day15_p1_2.txt");
        assertEquals(Day15.executePart1(strList), 510792);
    }

    @Test
    public void getSummaAscii_Test() {
        assertEquals(200, Day15.getSummaAscii('H',0 ));
        assertEquals(153, Day15.getSummaAscii('A',200 ));
        assertEquals(172, Day15.getSummaAscii('S',153 ));
        assertEquals(52, Day15.getSummaAscii('H',172 ));
    }

    @Test
    public void getSummaWord_Test() {
        assertEquals(52, Day15.getSummaWord("HASH"));
    }
}
