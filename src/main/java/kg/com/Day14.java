package kg.com;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    static List<List<List<Integer>>> inputList = new ArrayList<>();
    static int maxPos = 0;

    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        int resultHor = 0;
        int resultVert = 0;

        for (List<List<Integer>> list: inputList){

        }
        System.out.println(resultHor*100L + resultVert);
        return (resultHor*100L + resultVert);
    }

    public static Long executePart2(List<String> strList) {
        clear();
        inputList = parse2DList(strList);
        return 0L;
    }

    static List<List<List<Integer>>> parse2DList(List<String> strList) {
        List<List<List<Integer>>> result = new ArrayList<>();
        List<List<Integer>> matrix = new ArrayList<>();
        int cntX = 0;
        int cntY = 0;
        for (String str : strList) {
            if (str.isEmpty()) {
                result.add(new ArrayList<>(matrix));
                matrix.clear();
                continue;
            }
            cntX = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                list.add(str.charAt(i) == '.' ? 0 : 1);
                cntX++;
            }
            matrix.add(list);
            cntY++;
        }
        result.add(new ArrayList<>(matrix));
        maxPos = Math.max(cntX, cntY);
        return result;
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

