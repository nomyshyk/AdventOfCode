package kg.com;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    static List<String> inputList = new ArrayList<>();
    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parseLine(strList);

        long sum = 0;
        for(String str : inputList) {
            sum += getSummaWord(str);
            //System.out.println(sum);
        }

        //long total = 0;
        System.out.println("result is " + sum);
        return sum;
    }

    public static Long executePart2(List<String> strList) {
        clear();
        //inputList = parse2DList(strList);
        return 0L;
    }

    static List<String> parseLine(List<String> strList) {
        return Arrays.stream(strList.get(0).split(",")).toList();
    }

    public static long getSummaAscii(char c, long val) {
        return ((val + c) * 17) % 256;
    }

    public static long getSummaWord(String word) {
        long sum = 0L;
        for(char c : word.toCharArray()) {
            sum = getSummaAscii(c, sum);
        }
        return sum;
    }

    static void clear() {
        inputList.clear();
    }
}

