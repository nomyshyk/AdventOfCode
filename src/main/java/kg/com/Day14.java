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

        int resultHor = 0;
        int resultVert = 0;

//        for (List<List<Integer>> list: inputList){
//
//        }
        //System.out.println(resultHor*100L + resultVert);
        return (resultHor*100L + resultVert);
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
        System.out.println(result);
        return result;
    }

    public List<String> order(List<String> unorderedList) {
        List<Pair<Integer, Integer>> stonePositions = new ArrayList<>();
        int lastStonePos = 0;
        for (int i = 0; i < unorderedList.size(); i++) {
            if (unorderedList.get(i).equals("#")) {
                stonePositions.add(Pair.of(lastStonePos, i));
                lastStonePos = i;
            }
        }

        // If empty use all range
        if (stonePositions.isEmpty()) {
            stonePositions.add(Pair.of(0, unorderedList.size() - 1));
        }


        List<Integer> countZerosInInterval = new ArrayList<>();

        for (Pair<Integer, Integer> stonePosition : stonePositions) {
            int cntZeros = 0;
            for (int i = stonePosition.getLeft(); i <= stonePosition.getRight(); i++) {
                if (unorderedList.get(i).equals("O")) {
                    cntZeros++;
                }
            }
            countZerosInInterval.add(cntZeros);
        }


        List<String> update = new ArrayList<>(unorderedList);

        for (int i = 0; i < unorderedList.size(); i++) {
            for (Pair<Integer, Integer> stonePosition : stonePositions) {

            }
        }
        return null;
    }

//    public static List<List<Integer>> rotateMatrix(List<List<Integer>> matrix) {
//        List<List<Integer>> rotatedMatrix = new ArrayList<>();
//        for(int i = 0; i < matrix.get(1).size(); i++){
//            List<Integer> ints = new ArrayList<>();
//            for (int j = 0; j < matrix.size(); j++) {
//                ints.add(matrix.get(j).get(i));
//            }
//            rotatedMatrix.add(ints);
//        }
//        return rotatedMatrix;
//    }

    static void clear() {
        inputList.clear();
    }
}

