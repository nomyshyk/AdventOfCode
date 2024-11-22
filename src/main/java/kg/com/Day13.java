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

        for (List<List<Integer>> list: inputList) {
            //int middle = findMiddle(list);

            rotateMatrix(list);

            int curMirrorSize = 0;
            for (int i = 0; i < list.size(); i++){
                List<Pair<Integer, Integer>> pairs = pairList(i, list.size());
                boolean mirrored = false;
                for (Pair<Integer, Integer> pair : pairs) {
                    boolean linesAreSimilar = isArraysEqual(list.get(pair.getLeft()), list.get(pair.getRight()));
                    if(!linesAreSimilar) {
                        mirrored = false;
                        break;
                    }
                    mirrored = true;
                }
                if (mirrored) {
                    curMirrorSize = Math.max (curMirrorSize, list.size() - pairs.get(0).getLeft());
                    System.out.println("wow that was a mirror! and size is " + curMirrorSize);
                }
            }
        }
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
        System.out.println(lineA + " and " + lineB + " are equal");
        return true;
    }

    // 4 = 2
    // 5 = 2
    public static int findMiddle(List<List<Integer>> matrix) {
        return ((matrix.size() % 2 == 0) ? (matrix.size() / 2) : (matrix.size()/2 + 1));
    }

    // 1 -> [0,2]
    public static List<Pair<Integer, Integer>> pairList(int curValue, int length) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 1; curValue+i < length && curValue-i+1 >= 0; i++) {
            pairs.add(Pair.of(curValue-i+1, curValue+i));
        }
        System.out.println(pairs);
        return pairs;
    }

    public List<Integer> generateIndexesToCheck(int middle, int length) {
        return null;
    }

    public static List<List<Integer>> rotateMatrix(List<List<Integer>> matrix) {
        List<List<Integer>> rotatedMatrix = new ArrayList<>();
        for(int i = 0; i < matrix.get(1).size(); i++){
            List<Integer> ints = new ArrayList<>();
            for (int j = 0; j < matrix.size(); j++) {
                ints.add(matrix.get(j).get(i));
            }
            rotatedMatrix.add(ints);
        }
        System.out.println("rotated");
        System.out.println(rotatedMatrix);
        return rotatedMatrix;
    }

    static void clear() {
        //alreadyVisited.clear();
        inputList.clear();
    }
}

