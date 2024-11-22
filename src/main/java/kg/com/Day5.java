package kg.com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day5 {

    public static List<String> execute() {
        List<String> strList = FileLoader.inputLines("input_day5_2.txt");

        Map<String, List<List<Long>>> parsedMap = mapParser(strList);
        //List<Long> seeds = seedsParserPart1(strList);
        List<LongTuple2> longTuple2s = seedsParserPart2(strList);

        part2(longTuple2s, parsedMap);

        //List<Long> transform = part1(seeds, parsedMap);
        //System.out.println(transform);
        //Long minVal = transform.stream().min(Comparator.comparing(Long::valueOf)).get();
        //System.out.println(minVal);
        return strList;
    }

    public static Map<String, List<List<Long>>> mapParser(List<String> input) {
        Map<String, List<List<Long>>> parsedMap = new LinkedHashMap<>();
        List<List<Long>> destSourceList = new ArrayList<>();
        String curKey = null;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(" map:")) {
                curKey = input.get(i);
            } else if (input.get(i).isBlank()) {
                //
                if (curKey != null) {
                    parsedMap.put(curKey, new ArrayList<>(destSourceList));
                    destSourceList.clear();
                    curKey = null;
                }
            } else if (input.get(i).contains("seeds:")) {
                continue;
            } else if (i == input.size() - 1) {
                List<Long> integers = Arrays.stream(input.get(i).split(" ")).map(Long::parseLong).toList();
                destSourceList.add(integers);
                parsedMap.put(curKey, new ArrayList<>(destSourceList));

            } else {
                List<Long> integers = Arrays.stream(input.get(i).split(" ")).map(Long::parseLong).toList();
                destSourceList.add(integers);
            }
        }
        return parsedMap;
    }

    public static List<Long> seedsParserPart1(List<String> input) {
        for (String str : input) {
            if (str.contains("seeds: ")) {
                List<Long> integers = Arrays.stream(str.replace("seeds: ", "")
                                .split(" "))
                        .map(Long::parseLong)
                        .toList();
                return integers;
            } else break;
        }
        return null;
    }

    public static List<LongTuple2> seedsParserPart2(List<String> input) {
        List<LongTuple2> seedPairs = new ArrayList<>();
        for (String str : input) {
            if (str.contains("seeds: ")) {
                List<Long> integers = Arrays.stream(str.replace("seeds: ", "")
                                .split(" "))
                        .map(Long::parseLong)
                        .toList();
                for (int i = 0; i < integers.size(); i += 2) {
                    seedPairs.add(new LongTuple2(integers.get(i), integers.get(i + 1) + integers.get(i)));
                }
                return seedPairs;
            } else break;
        }
        return null;
    }

    public static List<Long> part1(List<Long> seeds, Map<String, List<List<Long>>> maps) {

        Long[] updatedSeeds = seeds.toArray(new Long[seeds.size()]);


        for (int i = 0; i < updatedSeeds.length; i++) {

            for (Map.Entry<String, List<List<Long>>> entry : maps.entrySet()) {
                lab1:
                for (var list : entry.getValue()) {

                    if (updatedSeeds[i] >= list.get(1) &&
                            updatedSeeds[i] <= (list.get(1) + list.get(2))) {
                        updatedSeeds[i] = updatedSeeds[i] - (list.get(1) - list.get(0));
                        break lab1;
                    }
                }
            }
        }
        return Arrays.asList(updatedSeeds);
    }

    public static Long part2(List<LongTuple2> seeds, Map<String, List<List<Long>>> maps) {
        long val;
        long res = 0L;
        LinkedList<Map.Entry<String, List<List<Long>>>> list2 = new LinkedList<>(maps.entrySet());

        outer:
        for (long l = 0; l <= Long.MAX_VALUE; l++) {

            Iterator<Map.Entry<String, List<List<Long>>>> itr = list2.descendingIterator();
            val = l;
            while(itr.hasNext()) {
                Map.Entry<String, List<List<Long>>> entry = itr.next();

                for (var list : entry.getValue()) {
                    if (val >= list.get(0) &&
                            val < (list.get(0) + list.get(2))) {
                        val = val + (list.get(1) - list.get(0));
                        break;
                    }
                }
            }
            for (LongTuple2 lt: seeds) {
                if(lt.begins <= val && lt.ends-1 >= val) {
                    res = l;
                    System.out.println("Exit point is " + val);
                    System.out.println("We-ve found it " + res);
                    break outer;
                }
            }
        }
        return res;
    }

//    public static List<LongTuple2> splitIntervals(LongTuple2 rangeA, LongTuple2 rangeB) {
//        List<LongTuple2> result = new ArrayList<>();
//        if (rangeA.begins > rangeB.ends || rangeB.begins > rangeA.ends) {
//            result.add(new LongTuple2(rangeA.begins, rangeA.ends));
//            return result;
//        }
//        long startOverlapRange = Math.max(rangeA.begins, rangeB.begins);
//        long endOverlapRange = Math.min(rangeA.ends, rangeB.ends);
//
//        if (rangeA.begins < rangeB.begins) {
//            Long begin = Math.min(rangeB.begins, rangeA.begins);
//            Long end = startOverlapRange - 1;
//            result.add(new LongTuple2(begin, end));
//        }
//
//        result.add(new LongTuple2(startOverlapRange, endOverlapRange));
//
//        if (rangeA.ends > rangeB.ends) {
//            Long begin = endOverlapRange + 1;
//            Long end = Math.max(rangeB.ends, rangeA.ends);
//            result.add(new LongTuple2(begin, end));
//        }
//
//        return result;
//    }

}
