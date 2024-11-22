package kg.kg;


import kg.com.Day12;
import kg.com.FileLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay12 {

    @Test
    public void day12Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day12_p1_1.txt");
        assertEquals(Day12.executePart1(strList), 21L);
    }

    @Test
    public void day12Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day12_p1_2.txt");
        assertEquals(Day12.executePart1(strList), 6981);
    }

    @Test
    public void day12Part2_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day12_p1_1.txt");
        assertEquals(Day12.executePart2(strList), 525152);
    }

    @Test
    public void day12Part2_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day12_p1_2.txt");
        assertEquals(Day12.executePart2(strList), 4546215031609L);
    }

    @Test
    public void replaceTest() {
        //List<String> strList = FileLoader.inputLines("input_day12_p1_1.txt");
        StringBuilder sb = new StringBuilder("??#??");
        assertEquals(sb.replace(1, 2, "#").toString(), "?##??");
    }

    @Test
    public void calcGroupsSizesTest() {
        //List<String> strList = FileLoader.inputLines("input_day12_p1_1.txt");

        assertEquals(Day12.calcGroupsSizes("####.###....#"), List.of(4,3,1));
        assertEquals(Day12.calcGroupsSizes("???.###"), List.of(3));
        assertEquals(Day12.calcGroupsSizes("????.######..#####."), List.of(6,5));
    }

    @Test
    public void generateFinalLineTest() {

        assertEquals(Day12.generateFinalLine("?###????????", List.of(3,2,1), 0), 10);
    }

    @Test
    public void testDynamic() {
        //Day12.dynamicSolution("##?.#?#", List.of(2), 0, 0);
        long l = Day12.dynamicSolution("?###????????", List.of(3,2,1));
        System.out.println(l);
    }
}
