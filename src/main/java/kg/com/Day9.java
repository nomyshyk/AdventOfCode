package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;

public class Day9 {

    public static Long executePart1(List<String> strList) {

        List<List<Long>> inputList = parseMap(strList);
        long summa = 0L;
        for(List<Long> outerList : inputList) {
            System.out.println("outer" + outerList);
            long res = calculateNext(outerList);
            summa += (res + outerList.get(outerList.size()-1));
        }
        System.out.println("result="+summa);
        return summa;
    }

    public static Long executePart2(List<String> strList) {
        List<List<Long>> inputList = parseMap(strList);
        long summa = 0L;
        for(List<Long> outerList : inputList) {
            System.out.println("outer" + outerList);
            long res = calculatePrevious(outerList);
            //System.out.println("prom="+res);
            summa += (outerList.get(0)-res);
        }
        System.out.println("result="+summa);
        return summa;
    }

    static List<List<Long>> parseMap(List<String> strList) {
        List<List<Long>> result = new ArrayList<>();
        for (String str : strList) {
            List<Long> inLineList = new ArrayList<>();
            String[] split = str.split(" ");
            for (String nums : split) {
                inLineList.add(Long.valueOf(nums));
            }
            result.add(new ArrayList<>(inLineList));
        }
        return result;
    }

    static Long calculateNext(List<Long> list) {
        List<Long> vals = new ArrayList<>();
        long diff = 0L;
        for (int i = 1; i < list.size(); i++) {
            long val = list.get(i) - list.get(i-1);
            vals.add(val);
            diff += val;
        }

        long outp = 0L;
        if (diff != 0) {
            outp = calculateNext(vals) + (vals.get(vals.size()-1));
        }

        return outp;
    }

    static Long calculatePrevious(List<Long> list) {
        List<Long> vals = new ArrayList<>();
        long diff = 0L;
        for (int i = 1; i < list.size(); i++) {
            long val = list.get(i) - list.get(i-1);
            vals.add(val);
            diff += val;
        }

        long outp = 0L;
        if (diff != 0) {
            outp = (vals.get(0)) - calculatePrevious(vals);
            //System.out.println("="+ outp);
        }

        return outp;
    }
}

