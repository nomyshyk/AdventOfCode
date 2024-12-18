package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    static List<List<String>> inputList = new ArrayList<>();
    static int maxPos = 0;

    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        long total = 0;
        List<List<String>> rotatedMatrix = rotateMatrix(inputList);
        for (List<String> list: rotatedMatrix){
            total += order(list);
        }

        System.out.printf("Total is %s", total);
        return total;
    }

    public static Long executePart2(List<String> strList) {
        clear();
        inputList = parse2DList(strList);
        return 0L;
    }

    static List<List<String>> parse2DList(List<String> strList) {
        List<List<String>> result = new ArrayList<>();
        for (String str : strList) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                list.add(String.valueOf(str.charAt(i)));
            }
            result.add(list);
        }
        //System.out.println(result);
        return result;
    }

    public static Long order(List<String> unorderedList) {
        List<Pair<Integer, Integer>> stonePositions = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        int lastStonePos = 0;
        int cnt0 = 0;
        for (int i = 0; i < unorderedList.size(); i++) {
            if (unorderedList.get(i).equals("O")){
               cnt0++;
            }

            if (unorderedList.get(i).equals("#") || (i == unorderedList.size()-1)) {
                //System.out.printf("new pair %s and %s%n", lastStonePos, i);
                stonePositions.add(Pair.of(((lastStonePos==0 && stonePositions.isEmpty()) ? 0 : lastStonePos + 1), i));
                lastStonePos = i;
                counts.add(cnt0);
                cnt0 = 0;
            }
        }

        // If empty use all range
        if (stonePositions.isEmpty()) {
            stonePositions.add(Pair.of(0, unorderedList.size() - 1));
            counts.add(0);
        }

        long total = 0L;
        for (int i = 0; i < stonePositions.size(); i++) {
            total += calculateTotal(stonePositions.get(i), counts.get(i), unorderedList.size());
        }
        return total;
    }

    //Counts
    public static long calculateTotal(Pair<Integer, Integer> border, int amtOfZeros, int totalLength) {
        Integer begin = border.getLeft();

        long total = 0;

        for (int i = begin; i < (begin + amtOfZeros); i++) {
            total += (totalLength - i);
        }
        return total;
    }

    public static List<List<String>> rotateMatrix(List<List<String>> matrix) {
        List<List<String>> rotatedMatrix = new ArrayList<>();
        for(int i = 0; i < matrix.get(1).size(); i++){
            List<String> newStr = new ArrayList<>();
            for (int j = 0; j < matrix.size(); j++) {
                newStr.add(matrix.get(j).get(i));
            }
            rotatedMatrix.add(newStr);
        }
        return rotatedMatrix;
    }

    static void clear() {
        inputList.clear();
    }
}

