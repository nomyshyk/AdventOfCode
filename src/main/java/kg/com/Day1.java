package kg.com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    private static List<String> numSet = List.of("0", "1", "2", "3", "4", "5", "6" ,"7", "8", "9",
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private static List<String> ints = List.of("0","1", "2", "3", "4", "5", "6", "7" , "8", "9",
            "1", "2", "3", "4", "5", "6", "7" , "8", "9");
    // 54081
    // 54649
    public static long execute() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input_day1.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        List<Long> longs = reader.lines().map(v -> calculate(v)).toList();
        System.out.print(longs);
        Long result = longs.stream()
                .reduce((a, b) -> a + b).get();
        System.out.println();
        System.out.println(result);
        return result;
    }

    public static long calculate(String text) {
        int beginPosNum = Integer.MAX_VALUE;
        int endPosNum = Integer.MIN_VALUE;

        Map<String, Map<Integer, Integer>> positions = new HashMap<>();

        for (String substr : numSet) {
            if (text.contains(substr)) {
                positions.put(substr, Map.of(text.indexOf(substr), text.lastIndexOf(substr)));
            }
        }


        String textBegin="";
        String textEnd="";
        for (Map.Entry<String, Map<Integer, Integer>> entry : positions.entrySet()) {
            for (Map.Entry<Integer, Integer> pos : entry.getValue().entrySet()) {
                if (beginPosNum > pos.getKey()) {
                    beginPosNum = Math.min(beginPosNum, pos.getKey());
                    textBegin = entry.getKey();
                }

                if (endPosNum < pos.getValue()) {
                    endPosNum = Math.max(endPosNum, pos.getValue());
                    textEnd = entry.getKey();
                }
            }
        }

        if (beginPosNum == endPosNum && beginPosNum == -1) {
            return 0;
        }

        if (beginPosNum == endPosNum) {
            return Integer.parseInt(
                    transferToInt(textBegin) + transferToInt(textBegin)
            );
        }

        return Integer.parseInt(
                transferToInt(textBegin) + transferToInt(textEnd)
        );

    }

    public static String transferToInt(String text) {
        if("".equals(text)) {
            return "0";
        }
        int index = -1;
        for (int i=0; i < numSet.size(); i++) {
            if (text.equals(numSet.get(i))) {
                index = i;
                break;
            }
        }
        return ints.get(index);
    }
}
