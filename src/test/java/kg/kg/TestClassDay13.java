package kg.kg;


import kg.com.Day12;
import kg.com.Day13;
import kg.com.FileLoader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassDay13 {

    @Test
    public void day13Part1_Test_1() {
        List<String> strList = FileLoader.inputLines("input_day13_p1_1.txt");
        assertEquals(Day13.executePart1(strList), 405L);
    }

    @Test
    public void day13Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day13_p2_1.txt");
        assertEquals(Day13.executePart1(strList), 6981);
    }

    @Test
    public void findMiddleTest() {
        List<List<Integer>> twoD = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            twoD.add(List.of(0,1,2,3,4));
        }
        assertEquals(Day13.findMiddle(twoD), 3);
    }
}
