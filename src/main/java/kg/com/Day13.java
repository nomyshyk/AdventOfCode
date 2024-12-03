package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Parse matrix
// One vertical and one horizontal
// Dont need numbers - 01 list is OK
// Count overall differences - not line vs line but total
public class Day13 {

    static List<List<List<Integer>>> inputList = new ArrayList<>();
    static int maxPos = 0;

    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        int resultHor = 0;
        int resultVert = 0;

        for (List<List<Integer>> list: inputList){

            List<Integer> integers = noBinaryPairs(list, 0);
            if(!integers.isEmpty()) {
                resultHor += integers.get(0);
                System.out.println("resHor=" + resultHor);
                continue;
            }

            List<List<Integer>> rotatedMatrix = rotateMatrix(list);
            List<Integer> verInts = noBinaryPairs(rotatedMatrix, 0);
            if(!verInts.isEmpty()) {
                resultVert += verInts.get(0);
                System.out.println("resVert=" + resultVert);
            }

        }
        return (resultHor*100L + resultVert);
    }

    public static Long executePart2(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        int resultHor = 0;
        int resultVert = 0;

        for (List<List<Integer>> list: inputList) {

            List<Integer> integers = noBinaryPairs(list, 0);

            List<Integer> integerUpdated = noBinaryPairs(list, 1);
            Optional<Integer> first = integerUpdated.stream().filter(p -> p.equals(integers.isEmpty() ? -1 : integers.get(0)))
                    .findFirst();
            if(first.isPresent()) {
                resultHor += first.get();
                System.out.println("resultHor=" + resultHor);
                continue;
            }

            // vertical
            List<List<Integer>> rotatedMatrix = rotateMatrix(list);
            List<Integer> integersVert = noBinaryPairs(rotatedMatrix, 0);

            List<Integer> integerUpdatedVert = noBinaryPairs(rotatedMatrix, 1);
            Optional<Integer> firstVert = integerUpdatedVert.stream()
                    .filter(p -> p.equals(integersVert.isEmpty() ? -1 : integersVert.get(0)))
                    .findFirst();
            if(firstVert.isPresent()) {
                resultVert += firstVert.get();
                System.out.println("resultVert=" + resultVert);
            }
        }


        return (resultHor*100L + resultVert);
    }

    public static int countLineDiff(List<Integer> listA, List<Integer> listB, int allowedDiffAmt) {
        int diffAmt = 0;
        for (int i = 0; i < listA.size(); i++) {
            if(!listA.get(i).equals(listB.get(i))){
                diffAmt++;
            }
            if(diffAmt > allowedDiffAmt) {
                break;
            }
        }
        return diffAmt;
    }

    public static List<Integer> noBinaryPairs(List<List<Integer>> list, int allowedDiffAmt) {
        List<Integer> allCombies = new ArrayList<>();
        //int curMirrorSize = 0;
        int allowedErrCnt = 0;
        outer:
        for (int i = 0; i <= list.size(); i++){
            boolean found = false;
            int curMirrorSize = 0;
            int begin = Integer.MAX_VALUE;
            int end = 0;
            
            List<Pair<Integer, Integer>> pairs = pairList(i, list.size());

            for (Pair<Integer, Integer> pair : pairs) {
                allowedErrCnt += countLineDiff(list.get(pair.getLeft()), list.get(pair.getRight()), allowedDiffAmt);
                //TODO this should be updated
                if(allowedErrCnt > allowedDiffAmt) {
                    begin = Integer.MAX_VALUE;
                    end = 0;
                    found = false;
                    allowedErrCnt = 0;
                    break;
                }

                begin = Math.min(begin, Math.min(pair.getLeft(), pair.getRight()));
                end = Math.max(end, Math.max(pair.getLeft(), pair.getRight()));
                found = true;
            }
            if(found) {
                curMirrorSize = Math.max(curMirrorSize, pairs.get(0).getLeft()+1);
                allCombies.add(curMirrorSize);
            }

        }
        return allCombies;
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

    // 1 -> [0,2]
    public static List<Pair<Integer, Integer>> pairList(int curValue, int length) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 1; curValue+i < length && curValue-i+1 >= 0; i++) {
            pairs.add(Pair.of(curValue-i+1, curValue+i));
        }
        System.out.println(pairs);
        return pairs;
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
        return rotatedMatrix;
    }

    public static int updateGivenPositionOfInt(int value, int pos) {
        System.out.println(Integer.toBinaryString(value ^ (1 << pos)));
        return value ^ (1 << pos);
    }

    public static List<Integer> listOfCombinations(int value, int totalLength) {
        System.out.println(Integer.toBinaryString(value));
        List<Integer> numbersList = new ArrayList<>();
        for(int i = 0; i <= totalLength; i++) {
            numbersList.add(updateGivenPositionOfInt(value, i));
        }
        System.out.println(numbersList);
        return numbersList;
    }

    static void clear() {
        inputList.clear();
    }
}

