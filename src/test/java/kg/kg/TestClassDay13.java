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
        assertEquals(Day13.executePart1(strList), 4);
    }

    @Test
    public void findMiddleTest() {
        assertEquals(Day13.findMiddle(two55), 3);
        assertEquals(Day13.findMiddle(two44), 2);
        assertEquals(Day13.findMiddle(two54), 3);
    }

    @Test
    public void pairListTest() {
        assertEquals(Day13.pairList(1, 7), 0);
    }
}
