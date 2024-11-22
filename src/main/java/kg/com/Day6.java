package kg.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    public static void execute() {
        List<String> strList = FileLoader.inputLines("input_day6_2.txt");
        //List<Tuple2> tuple2s = parsePart1(strList);
        List<Tuple2> tuple2s = parsePart2(strList);
        //countPart1(tuple2s);
        countPart1(tuple2s);
        System.out.println(strList);
    }

    public static List<Tuple2> parsePart1(List<String> inputList) {
        List<Tuple2> result = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            List<String> strings = Arrays.stream(inputList.get(i)
                    .substring(inputList.get(i).indexOf(":") + 1)
                    .replaceAll("\\s+", " ").split(" ")).filter(f -> !"".equals(f)).toList();
            for (int j = 0; j < strings.size(); j ++) {
                if (i == 0) {
                    result.add(new Tuple2(Long.valueOf(strings.get(j)), 0L));
                } else {
                    result.get(j).distance = Long.valueOf(strings.get(j));
                }
            }
        }

        System.out.println(result);
        return result;
    }

    public static List<Tuple2> parsePart2(List<String> inputList) {
        List<Tuple2> result = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            List<String> strings = Arrays.stream(inputList.get(i)
                    .substring(inputList.get(i).indexOf(":") + 1)
                    .replaceAll("\\s+", "").split(" ")).filter(f -> !"".equals(f)).toList();
            for (int j = 0; j < strings.size(); j ++) {
                if (i == 0) {
                    result.add(new Tuple2(Long.valueOf(strings.get(j)), 0L));
                } else {
                    result.get(j).distance = Long.valueOf(strings.get(j));
                }
            }
        }

        System.out.println(result);
        return result;
    }

    public static Long countPart1(List<Tuple2> table){
        long totalCount = 1;
        for (Tuple2 tuple2 : table) {
            long bidToBeatRecord = 0;
            int count = 0;
            for ( long i = 1; i <= tuple2.time; i++) {
                bidToBeatRecord = (tuple2.time - i) * i;
                if (bidToBeatRecord > tuple2.distance) {
                    //recordBeaters.add(bidToBeatRecord);
                    count++;
                };
            }
            System.out.println("count " + count);
            totalCount *= count;
        }
        System.out.println(totalCount);
        return totalCount;
    }
}

class Tuple2 {
    Long time;
    Long distance;

    public Tuple2(Long time, Long distance) {
        this.time = time;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "time=" + time +
                ", distance=" + distance +
                '}';
    }
}
