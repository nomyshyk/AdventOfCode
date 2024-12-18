package kg.kg;

import kg.com.Day14;
import kg.com.FileLoader;
import org.apache.commons.lang3.tuple.Pair;
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

    @Test
    public void day14Part1_Test_2() {
        List<String> strList = FileLoader.inputLines("input_day14_p1_2.txt");
        assertEquals(Day14.executePart1(strList), 103614L);
    }

    @Test
    public void calculateZerosTest() {

        assertEquals(Day14.calculateTotal(Pair.of(0, 0), 0, 10), 0);
        assertEquals(Day14.calculateTotal(Pair.of(0, 5), 0, 10), 0);
        assertEquals(Day14.calculateTotal(Pair.of(0, 5), 1, 10), 10);
        assertEquals(Day14.calculateTotal(Pair.of(0, 5), 2, 10), 19);
        assertEquals(Day14.calculateTotal(Pair.of(0, 5), 3, 10), 27);

        assertEquals(Day14.calculateTotal(Pair.of(1, 5), 1, 10), 9);
        assertEquals(Day14.calculateTotal(Pair.of(1, 5), 2, 10), 17);
        assertEquals(Day14.calculateTotal(Pair.of(0, 5), 5, 10), 10+9+8+7+6);
    }

    @Test
    public void testOrder() {
        assertEquals(Day14.order(List.of("O", ".", "#", ".", ".")), 5);
        assertEquals(Day14.order(List.of("O", "O", "#", ".", ".")), 9);
        assertEquals(Day14.order(List.of(".", "O", "#", ".", ".")), 5);
        assertEquals(Day14.order(List.of(".", ".", "#", ".", "O")), 2);
        assertEquals(Day14.order(List.of(".", ".", "#", "#", "O")), 1);
        assertEquals(Day14.order(List.of("#", "#", "#", "#", "O")), 1);
        assertEquals(Day14.order(List.of("O", "O", "O", "O", "O")), 5+4+3+2+1);
        assertEquals(Day14.order(List.of(".", ".", ".", ".", ".")), 0);
        assertEquals(Day14.order(List.of(".", ".", "#", ".", ".")), 0);
        assertEquals(Day14.order(List.of("O", "#", "O", "#", "O")), 9);
        assertEquals(Day14.order(List.of("O", ".", ".", ".", "#")), 5);
        assertEquals(Day14.order(List.of("#", ".", ".", ".", "O")), 4);
    }

//    @Test
//    public void day14Part1_Test_2() {
//        List<String> strList = FileLoader.inputLines("input_day14_p2_1.txt");
//        assertEquals(Day14.executePart1(strList), 37718);
//    }
}
