package kg.kg;


import kg.com.Day12;
import kg.com.Day13;
import kg.com.FileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay13 {

    List<List<Integer>> two55 = new ArrayList<>();
    List<List<Integer>> two44 = new ArrayList<>();
    List<List<Integer>> two54 = new ArrayList<>();

    @BeforeEach
    public void init() {

        for(int i = 0; i < 5; i++){
            two55.add(List.of(0,1,2,3,4));
        }

        for(int i = 0; i < 4; i++){
            two44.add(List.of(0,1,2,3));
        }

        for(int i = 0; i < 5; i++){
            two54.add(List.of(0,1,2,3));
        }
    }

    @Test
    public void day13Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day13_p1_1.txt");
        assertEquals(Day13.executePart1(strList), 405L);
    }

    @Test
    public void day13Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day13_p2_1.txt");
        assertEquals(Day13.executePart1(strList), 37718);
    }

    @Test
    public void day13Part1_Test_3() {
        List<String> strList = FileLoader.inputLines("input_day13_p3.txt");
        assertEquals(Day13.executePart1(strList), 1400);
    }

    @Test
    public void day13Part2_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day13_p1_1.txt");
        assertEquals(Day13.executePart2(strList), 400L);
    }

    @Test
    public void day13Part2_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day13_p2_1.txt");
        assertEquals(Day13.executePart2(strList), 40995); //36902, 36999
    }

    @Test
    public void day13Part2_Test_3() {
        List<String> strList = FileLoader.inputLines("input_day13_p3.txt");
        assertEquals(Day13.executePart2(strList), 12); //36891, 36902, 36999, 40995
    }

    @Test
    public void pairListTest() {
        assertEquals(Day13.pairList(3, 7), 0);
    }

    @Test
    public void checkBitwiseEqualTest() {
        assertEquals(true, Day13.isBitwiseEqual(120, 120));
        assertEquals(false, Day13.isBitwiseEqual(15, 8));
    }

    @Test
    public void ifPowerOf2() {
        assertEquals(true, Day13.isPowerOfTwo(0));
    }

    @Test
    public void updateGivenPosTest() {
        assertEquals(1, Day13.updateGivenPositionOfInt(2, 1));
    }

    @Test
    public void listOfCombinationsTest() {
        assertEquals(1, Day13.listOfCombinations(12, 8 ));
    }

    @Test
    public void countLineOnTopTest() {
        //assertEquals(1, Day13.countLineOnTop(List.of(12,12,200), 2 ).getLeft());
        assertEquals(2, Day13.countLineOnTop(List.of(12,200,200,12), 4 ).getLeft());
    }
}
