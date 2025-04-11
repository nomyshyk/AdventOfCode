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
        inputList = parseLine(strList);
        Map<Long, LinkedHashMap<String, Integer>> linkedHashMap = new LinkedHashMap();
        long sum = 0;
        for(String str : inputList) {
            ParsedVal parsedVal = splitCode(str);
            long boxNum = getSummaWord(parsedVal.code);
            // EQUAL case
            if(parsedVal.oper.equals(Oper.EQUAL)) {
                LinkedHashMap<String, Integer> boxLenses = linkedHashMap.get(boxNum);
                if(boxLenses == null) {
                    // when box is empty just add lense
                    boxLenses = new LinkedHashMap<>();
                    boxLenses.put(parsedVal.code, parsedVal.number);
                    linkedHashMap.put(boxNum, boxLenses);
                } else {
                    //when some value in the box
                    //then look if is already presented
                   boxLenses.put(parsedVal.code, parsedVal.number);
                   linkedHashMap.put(boxNum, boxLenses);
                }
            } else {
                // if MINUS then delete
                LinkedHashMap<String, Integer> boxLenses = linkedHashMap.get(boxNum);
                if(boxLenses != null) {
                    //when some value in the box
                    //then remove that value
                    boxLenses.remove(parsedVal.code);
                    linkedHashMap.put(boxNum, boxLenses);
                }
            }
        }
        System.out.println(linkedHashMap);
        long result = countPart2Total(linkedHashMap);
        System.out.println(result);
        return result;
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

    static ParsedVal splitCode(String str) {
        String[] split = str.split("=");
        if(split.length == 1) {
            return new ParsedVal(str.split("-")[0], 0, Oper.MINUS);
        }
        return new ParsedVal(split[0], Integer.parseInt(split[1]), Oper.EQUAL);
    }

    static long countPart2Total(Map<Long, LinkedHashMap<String, Integer>> map) {
        long lensesMulty = 0L;
        for(Map.Entry<Long, LinkedHashMap<String, Integer>> boxes : map.entrySet()) {
            long boxIdx = boxes.getKey() + 1;
            int idx = 0;
            for(Map.Entry<String, Integer> lenses : boxes.getValue().entrySet()) {
                lensesMulty += (++idx) * lenses.getValue() * boxIdx;
            }
        }
        return lensesMulty;
    }

    static void clear() {
        inputList.clear();
    }
}

class ParsedVal {
    public ParsedVal(String code, int number, Oper oper) {
        this.code = code;
        this.number = number;
        this.oper = oper;
    }

    String code;
    int number;
    Oper oper;
}

enum Oper {
    EQUAL, MINUS
}

