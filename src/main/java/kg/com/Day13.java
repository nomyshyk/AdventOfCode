package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Parse matrix
// One vertical and one horizontal
// Store in tuples
// Find middle and go up and down
// If found - count
public class Day13 {

    static List<List<List<Integer>>> inputList = new ArrayList<>();

    public static Long executePart1(List<String> strList) {
        clear();
        inputList = parse2DList(strList);
        System.out.println(inputList);
        return 0L;
    }

    static List<List<List<Integer>>> parse2DList(List<String> strList) {
        List<List<List<Integer>>> result = new ArrayList<>();
        List<List<Integer>> matrix = new ArrayList<>();
        for (String str : strList) {

            if (str.isEmpty()) {
                result.add(new ArrayList<>(matrix));
                matrix.clear();
                continue;
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                list.add(str.charAt(i) == '.' ? 0 : 1);
            }
            matrix.add(list);

        }
        result.add(new ArrayList<>(matrix));
        return result;
    }

    //Check if 2 lines equal
    static boolean isArraysEqual(List<Integer> lineA, List<Integer> lineB) {
        for (int i = 0; i < lineA.size(); i++) {
            if(!lineA.get(i).equals(lineB.get(i))) {
                return false;
            }
        }
        return true;
    }

    // 4 = 2
    // 5 = 2
    public static int findMiddle(List<List<Integer>> matrix) {
        return ((matrix.size() % 2 == 0) ? (matrix.size() / 2) : (matrix.size()/2 + 1)) - 1;
    }

    // 1 -> [0,2]
    public static List<Pair<Integer, Integer>> pairList(int curValue, int length) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = curValue; i < length && i >= 0; i++) {
            pairs.add(Pair.of(i-1, i+1));
        }
        return pairs;
    }

    static void clear() {
        //alreadyVisited.clear();
        inputList.clear();
    }
}

