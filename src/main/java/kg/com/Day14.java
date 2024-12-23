package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

    static List<List<String>> inputList = new ArrayList<>();
    static Map<Integer, Integer> cacheHash = new HashMap<>();
    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        long total = 0;
        //printMatrix(cntClockRotate(inputList));
        List<List<String>> rotatedMatrix = cntClockRotate(inputList);
        //System.out.println("res");
        //printMatrix(rotatedMatrix);
        for (List<String> list: rotatedMatrix){
            List<String> s = splitOrderMerge(list);
            total += counter(s);
            //System.out.println(s);
            //System.out.println(total);
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
                result += i+1;
            }
        }
        return result;
    }
    public static Long executePart2(List<String> strList) {
        clear();
        inputList = parse2DList(strList);

        //printMatrix(clockwiseRotateMatrix(inputList));
//        //Should be eq
//        System.out.println("should be eq");
//        printMatrix(lists);
//
//        List<List<String>> updatedMatrix = rotate(inputList);

        int hashOfMatrix = 0;
        boolean notInCache = false;
        int rotationN = 0;
        List<List<String>> updatedMatrix = new ArrayList<>(inputList);

        while (!notInCache) {
            updatedMatrix = rotate(updatedMatrix);
            hashOfMatrix = updatedMatrix.hashCode();
            if(cacheHash.get(hashOfMatrix) != null) {
                notInCache = true;
            } else {
                cacheHash.put(hashOfMatrix, ++rotationN);
            }
        }

        System.out.printf("It copied after %s cycles \n", rotationN);
        int additionalCycles = (1000000000 % rotationN);
        System.out.printf("Rotate additional %s times \n", additionalCycles);

        for(int i = 0; i < additionalCycles; i++) {
            updatedMatrix = rotate(updatedMatrix);
        }

        long total = 0;
        for (List<String> list: updatedMatrix){
            //List<String> s = splitOrderMerge(list);
            total += counter(list);
            System.out.println(total);
        }

        return total;
    }

    static List<List<String>> rotate(List<List<String>> matrix) {
        List<List<String>> origMatrix = new ArrayList<>(matrix);
        List<List<String>> updatedMatrix = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.printf("prepare rotation #%s \n", i);
            printMatrix(origMatrix);
            origMatrix = cntClockRotate(origMatrix);
            System.out.printf("after rotation #%s \n", i);
            printMatrix(origMatrix);
            for (List<String> list: origMatrix){
                updatedMatrix.add(splitOrderMerge(list));
            }
            System.out.printf("after tilt #%s \n", i);
            printMatrix(updatedMatrix);
            origMatrix = new ArrayList<>(updatedMatrix);
            updatedMatrix.clear();
        }

        return origMatrix;
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
                .sorted(Comparator.naturalOrder())
                .toList());
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

    public static List<List<String>> cntClockRotate(List<List<String>> matrix) {
        List<List<String>> rotatedMatrix = new ArrayList<>();
        for(int i = 0; i < matrix.size(); i++){
            List<String> newStr = new ArrayList<>();
            for (int j = 0; j < matrix.get(i).size(); j++) {
//                String old = matrix.get(i).get(j);
//                int newI = matrix.get(0).size()-1 - j;
//                int newJ = i;
                newStr.add(matrix.get(matrix.get(0).size()-1 - j).get(i)); // cnt-clockwise
                //newStr.add(matrix.get(matrix.get(0).size()-1 - i).get(matrix.get(0).size()-1 - j)); // reflect
            }
            rotatedMatrix.add(newStr);
        }
        return rotatedMatrix;
    }

    public static void printMatrix(List<List<String>> matrix) {
        matrix.stream().forEach(
                p -> {
                    p.stream().forEach( f -> {
                        System.out.printf("%s", f);;
                    });
                    System.out.println();
                }
        );
    }

    static void clear() {
        inputList.clear();
    }
}

