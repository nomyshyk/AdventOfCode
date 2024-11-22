package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;

public class Day8 {

    static Queue<Character> queue;

    public static Long executePart1(List<String> strList) {
        String directions = getDirections(strList);
        Map<String, Pair<String, String>> map = parseMap(strList, 1);

        queue = new LinkedList<>();

        char[] chars = directions.toCharArray();
        for( char val : chars) {
            queue.add(val);
        }

        Pair curPosition = map.get("AAA");
        Long counter = 0L;

        boolean foundZZZ = false;
        while(!foundZZZ) {
            Character directive = queue.poll();
            String eval = (String) (directive.equals('L') ? curPosition.getLeft() : curPosition.getRight());
            counter++;
            if(eval.equals("ZZZ")) {
                foundZZZ = true;
            } else {
                curPosition = map.get(eval);
                queue.add(directive);
            }
        }
        System.out.println(counter);
        return counter;
    }

    public static BigInteger executePart2(List<String> strList) {
        String directions = getDirections(strList);
        Map<String, Pair<String, String>> map = parseMap(strList, 1);

        queue = new LinkedList<>();

        char[] chars = directions.toCharArray();

        List<String> aStartKeys = pairListInit(map).getLeft();
        BigInteger totalResult = BigInteger.ONE;

        for (String startA : aStartKeys) {
            BigInteger counter = BigInteger.ZERO;
            System.out.println("From String="+startA);
            queue.clear();
            for( char val : chars) {
                queue.add(val);
            }
            Pair<String, String> curPosition = map.get(startA);

            boolean foundZ = false;
            while(!foundZ) {
                Character directive = queue.poll();
                String eval = (directive.equals('L') ? curPosition.getLeft() : curPosition.getRight());
                counter = counter.add(BigInteger.ONE);
                if(eval.endsWith("Z")) {
                    //System.out.println("To String="+eval);
                    foundZ = true;
                } else {
                    curPosition = map.get(eval);
                    queue.add(directive);
                }
            }

            //System.out.println(counter);
            System.out.println("lcm="+lcm(totalResult, counter));
            totalResult = lcm(totalResult, counter);
        }
        System.out.println(totalResult);
        return totalResult;
    }

    public static BigInteger lcm( BigInteger a, BigInteger b ) {
        BigInteger mult = a.multiply(b);
        BigInteger gcd = a.gcd(b);
        return mult.divide(gcd);
    }

    static Pair<List<String>, List<String>> pairListInit(Map<String, Pair<String, String>> map) {
        List<String> pairsA = new ArrayList<>();
        List<String> pairsZ = new ArrayList<>();
        for (Map.Entry<String, Pair<String, String>> entry : map.entrySet()) {
            if(entry.getKey().endsWith("A")) {
                pairsA.add(entry.getKey());
            } else if (entry.getKey().endsWith("Z")) {
                pairsZ.add(entry.getKey());
            }
        }

        return Pair.of(pairsA, pairsZ);
    }

    static Map<String, Pair<String, String>> parseMap(List<String> strList, int fromIdx) {
        Map<String, Pair<String, String>> mapPair = new LinkedHashMap<>();
        for(int i = fromIdx+1; i < strList.size(); i++) {
            String[] split = strList.get(i)
                    .replace("(", "")
                    .replace(")", "")
                    .replaceAll("\\s+", "")
                    .split("=");

            String[] pairs = split[1].split(",");
            mapPair.put(split[0], Pair.of(pairs[0], pairs[1]));
        }

        System.out.println(mapPair);
        return mapPair;
    }

    static String getDirections(List<String> strList) {
        String instructrions = "";
        int delimeterEmpryLineIdx = 0;
        for(int i = 0; i < strList.size(); i++) {
            if (strList.get(i).trim().length()==0) {
                delimeterEmpryLineIdx = i;
                break;
            }
            instructrions = strList.get(i);
        }
        return instructrions;
    }
}

