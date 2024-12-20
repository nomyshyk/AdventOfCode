package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

    static List<List<String>> inputList = new ArrayList<>();
    public static Map<String, List<List<Pair<Integer, Integer>>>> cacheOfWallIntervals = new HashMap<>();

    public static void populateCacheForAllSides() {
        cacheOfWallIntervals.putIfAbsent("WEST", cacheStoneIntervals(inputList));

        List<List<String>> north = rotateMatrix(inputList);
        cacheOfWallIntervals.putIfAbsent("NORTH", cacheStoneIntervals(north));

        List<List<String>> east = reflectMatrix(inputList);
        cacheOfWallIntervals.putIfAbsent("EAST", cacheStoneIntervals(east));

        List<List<String>> south = reflectMatrix(north);
        cacheOfWallIntervals.putIfAbsent("SOUTH", cacheStoneIntervals(south));

        System.out.println(cacheOfWallIntervals);
    }

    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        long total = 0;
        //int cnt = 0;
        List<List<String>> rotatedMatrix = rotateMatrix(inputList);
        for (List<String> list: rotatedMatrix){
            //total += order(list);
            List<String> s = splitOrderMerge(list);
            total += counter(s);
            System.out.println(total);
        }

        System.out.printf("Total is %s", total);
        return total;
    }

    public static Long counter(List<String> list) {
//        return list.stream().filter("O"::equals)
//                .count() * index;
        long result = 0L;
        for(int i = 0; i < list.size(); i++) {
            if("O".equals(list.get(i))) {
                result += list.size()-i;
            }
        }
        return result;
    }
    public static Long executePart2(List<String> strList) {
        clear();
        inputList = parse2DList(strList);
        //populateCacheForAllSides();
        //List<List<String>> rotatedMatrix = rotateMatrix(inputList);

        List<List<String>> updatedMatrix = rotate(inputList);

        //if(cacheOfWallIntervals.)
//        for (List<String> list: rotatedMatrix){
//            updatedMatrix.add(newMatrix(list));
//        }
//
//        int hashCode = updatedMatrix.hashCode();

        // should be done at the end
        long total = 0;
        for (List<String> list: updatedMatrix){
            total += order(list);
        }

        return total;
    }

    static List<List<String>> rotate(List<List<String>> matrix) {
        //System.out.println(rotateMatrix(matrix));

        // read north
        List<List<String>> updatedMatrix = new ArrayList<>();
        //System.out.println(matrix);
        for (List<String> list: rotateMatrix(matrix)){
            updatedMatrix.add(newMatrix(list, false));
        }

        // read west
        System.out.println(updatedMatrix);
//        List<List<String>> north = rotateMatrix(updatedMatrix);
//        updatedMatrix.clear();
//        for (List<String> list: north){
//            updatedMatrix.add(newMatrix(list, false));
//        }
//
//        System.out.println("First rotation");
//        System.out.println(updatedMatrix);

//        List<List<String>> east = reflectMatrix(inputList);
//        updatedMatrix.clear();
//        for (List<String> list: east){
//            updatedMatrix.add(newMatrix(list));
//        }
//
//
//        List<List<String>> south = reflectMatrix(north);
//        updatedMatrix.clear();
//        for (List<String> list: south){
//            updatedMatrix.add(newMatrix(list));
//        }

        return updatedMatrix;
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

    public static List<String> splitOrderMerge(List<String> unorderedList) {
        return Arrays.stream(Arrays.stream(String.join("", unorderedList)
                        .split("#", -1))
                .toList()
                .stream()
                .map(Day14::sortStr) // order
                .collect(Collectors.joining("#"))
                .split("")).toList();
    }

    public static String sortStr(String unsorted) {
        return String.join("", Arrays.stream(unsorted.split(""))
                .sorted(Comparator.reverseOrder())
                .toList());
    }

    public static List<String> newMatrix(List<String> unorderedList, boolean isReverse) {
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

        List<String> newArr = new ArrayList();
        //long total = 0L;
        for (int i = 0; i < stonePositions.size(); i++) {
            newArr.addAll(generateNewLine(stonePositions.get(i), counts.get(i), isReverse));
        }
        return newArr;
    }

//    public List<String> sort(List<String> unordered, boolean isReverse) {
//        for(int i = 1; i<unordered.size(); i++) {
//            if(unordered.get(i-1).equals(".") && unordered.get(i).equals("O")){
//
//            }
//        }
//    }

    public static List<List<Pair<Integer, Integer>>> cacheStoneIntervals(List<List<String>> matrix) {
        List<List<Pair<Integer, Integer>>> cachedMatrix = new ArrayList<>();
        for(int ii = 0; ii< matrix.size(); ii++) {
            List<Pair<Integer, Integer>> stonePositions = new ArrayList<>();
            int lastStonePos = 0;
            for(int j =0; j < matrix.get(ii).size(); j++) {
                if (matrix.get(ii).get(j).equals("#") || (j == matrix.get(ii).size()-1)) {
                    //System.out.printf("new pair %s and %s%n", lastStonePos, i);
                    stonePositions.add(Pair.of(((lastStonePos==0 && stonePositions.isEmpty()) ? 0 : lastStonePos + 1), j));
                    lastStonePos = j;
                }
            }
            if (stonePositions.isEmpty()) {
                stonePositions.add(Pair.of(0, matrix.get(ii).size() - 1));
            }
            cachedMatrix.add(stonePositions);
        }
        return cachedMatrix;
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

    public static List<String> generateNewLine(Pair<Integer, Integer> border, int amtOfZeros, boolean isReverse) {
        List<String> arr = new ArrayList<>();

        for (int i = 0; i < border.getRight() - border.getLeft() + 1; i++) {
            if(i == 0 || i == (border.getRight() - border.getLeft() + 1)) {
                arr.add("#");
            } else if(i <= amtOfZeros) {
                arr.add("O");
            } else {
                arr.add(".");
            }
        }
        return arr;
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

    public static List<List<String>> reflectMatrix(List<List<String>> matrix) {
        List<List<String>> rotatedMatrix = new ArrayList<>();
        for(int i = 0; i < matrix.size(); i++){
            List<String> newStr = new ArrayList<>();
            for (int j = 0; j < matrix.get(i).size(); j++) {
                newStr.add(matrix.get(i).get(matrix.get(0).size()-1 - j));
            }
            rotatedMatrix.add(newStr);
        }
        return rotatedMatrix;
    }

    public static List<List<String>> clockwiseRotateMatrix(List<List<String>> matrix) {
        List<List<String>> rotatedMatrix = new ArrayList<>();
        for(int i = 0; i < matrix.size(); i++){
            List<String> newStr = new ArrayList<>();
            for (int j = 0; j < matrix.get(i).size(); j++) {
                //newStr.add(matrix.get(j).get(matrix.get(0).size()-1 - i)); --anti-clockwise
                newStr.add(matrix.get(j).get(matrix.get(0).size()-1 - i));
            }
            rotatedMatrix.add(newStr);
        }
        return rotatedMatrix;
    }



    static void clear() {
        inputList.clear();
    }
}

