package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

    static List<List<String>> inputList = new ArrayList<>();
    static Map<String, Integer> cacheHash = new HashMap<>();

    static List<List<List<String>>> listOfMatrices = new ArrayList<>();
    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        long total = 0;
        List<List<String>> rotatedMatrix = cntClockRotate(inputList);
        for (List<String> list: rotatedMatrix){
            List<String> s = splitOrderMerge(list);
            total += counter(s);
        }

        System.out.printf("Total is %s", total);
        return total;
    }

    public static Long counter(List<String> list) {
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

        String hashOfMatrix;
        int rotationN = 0;
        List<List<String>> updatedMatrix = new ArrayList<>(inputList);
        int firstAppearedAt = -1;

        int times = 1000000000;

        while (rotationN < times) {
            updatedMatrix = new ArrayList<>(rotate(updatedMatrix));
            ++rotationN;
            //System.out.printf("Cycle %s \n", rotationN);
            hashOfMatrix = generUniqueCode(updatedMatrix);

            if(cacheHash.get(hashOfMatrix) != null) {
                firstAppearedAt = cacheHash.getOrDefault(hashOfMatrix, 0);
                break;
            } else {
                cacheHash.put(hashOfMatrix, rotationN);
                listOfMatrices.add(new ArrayList<>(updatedMatrix));
            }
        }

        System.out.printf("It copied after %s cycles, firstAppear at %s \n", rotationN, firstAppearedAt);

        int additionalCycles =
                firstAppearedAt == -1 ? rotationN :
                (((times - firstAppearedAt) % (rotationN - firstAppearedAt))) + firstAppearedAt;
        System.out.printf("Offset is %s \n", additionalCycles);

        updatedMatrix = listOfMatrices.get(additionalCycles - 1);

        //printMatrix(updatedMatrix);

        long total = 0;
        int len = updatedMatrix.size();
        for (List<String> list: updatedMatrix){
            total += list.stream().filter("O"::equals).count() * (len--);
            //System.out.println(list + " " + (len+1) + " = " + (list.stream().filter("O"::equals).count() * (len+1)));
        }

        System.out.printf("Total is %s", total);
        return total;
    }

    static List<List<String>> rotate(List<List<String>> matrix) {
        List<List<String>> origMatrix = new ArrayList<>(matrix);
        List<List<String>> updatedMatrix = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {

            origMatrix = cntClockRotate(origMatrix);

            for (List<String> list: origMatrix){
                updatedMatrix.add(splitOrderMerge(list));
            }

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

    public static long calculateTotal(Pair<Integer, Integer> border, int amtOfZeros, int totalLength) {
        Integer begin = border.getLeft();

        long total = 0;

        for (int i = begin; i < (begin + amtOfZeros); i++) {
            total += (totalLength - i);
        }
        return total;
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

    public static String generUniqueCode(List<List<String>> matrix1) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i< matrix1.size(); i++) {
            for (int j = 0 ; j< matrix1.get(i).size(); j++){
                stringBuilder.append(matrix1.get(i).get(j)).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    static void clear() {
        inputList.clear();
    }
}

