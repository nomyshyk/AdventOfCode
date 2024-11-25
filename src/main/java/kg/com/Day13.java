package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

// Parse matrix
// One vertical and one horizontal
// Store in tuples
// Find middle and go up and down
// If found - count
public class Day13 {

    static List<List<List<Integer>>> inputList = new ArrayList<>();
    static int maxPos = 0;

    public static Long executePart1(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        int resultHor = 0;
        int resultVert = 0;

        for (List<List<Integer>> list: inputList){
            List<Integer> bitwiseList = bitwiseList(list);
            int val = countLineOnTop(bitwiseList);
            resultHor += val;
            System.out.println("resHor=" + resultHor);
            if (val > 0) {
                continue;
            }

            List<List<Integer>> rotatedMatrix = rotateMatrix(list);
            resultVert += countLineOnTop(bitwiseList(rotatedMatrix));
            System.out.println("resultVert=" + resultVert);
        }


        return (resultHor*100L + resultVert);
    }

    public static Long executePart2(List<String> strList) {

        clear();
        inputList = parse2DList(strList);

        int resultHor = 0;
        int resultVert = 0;

        for (List<List<Integer>> list: inputList) {
            List<Integer> bitwiseList = bitwiseList(list);
            boolean combFound = false;
            outer:
            for (int i = 0; i < bitwiseList.size(); i++) {
                List<Integer> combinations = listOfCombinations(bitwiseList.get(i), list.get(0).size());
                List<Integer> validCombinations = validCombinations(bitwiseList, combinations);

                inner:
                for (int j = 0; j < validCombinations.size(); j++) {
                    List<Integer> updatedMatrix = replaceValueInMatrix(bitwiseList, i, validCombinations.get(j));

                    int val = countLineOnTop(updatedMatrix);
                    if (val > 0) {
                        resultHor += val;
                        System.out.println("resHor=" + resultHor);
                        //System.out.println("newNo = " + Integer.toBinaryString(validCombinations.get(j)) + ", line=" + i);
                        combFound = true;
                        break outer;
                    }
                }

            }

            if (combFound) {
                continue;
            }

            boolean vertFound = false;
            // vertical
            List<List<Integer>> rotatedMatrix = rotateMatrix(list);
            List<Integer> rotList = bitwiseList(rotatedMatrix);
            outer2:
            for (int i = 0; i < rotList.size(); i++) {
                List<Integer> combinations = listOfCombinations(rotList.get(i), rotatedMatrix.get(0).size());
                List<Integer> validCombinations = validCombinations(rotList, combinations);

                inner:
                for (int j = 0; j < validCombinations.size(); j++) {
                    List<Integer> updatedMatrix = replaceValueInMatrix(rotList, i, validCombinations.get(j));

                    int val = countLineOnTop(updatedMatrix);
                    if (val > 0) {
                        resultVert += val;
                        System.out.println("resVert=" + resultVert);
                        vertFound = true;
                        break outer2;
                    }
                }

            }

            if(!vertFound) {
                System.out.println();
            }
        }


        return (resultHor*100L + resultVert);
    }

    public static List<Integer> replaceValueInMatrix(List<Integer> bitwiseList, int idxToReplace, int intToReplace) {
        List<Integer> values = new ArrayList<>(bitwiseList);
        values.set(idxToReplace, intToReplace);
        return values;
    }

    public static int countLineOnTop(List<Integer> list) {

        int curMirrorSize = 0;
        for (int i = 0; i < list.size(); i++){
            List<Pair<Integer, Integer>> pairs = pairList(i, list.size());
            boolean mirrored = false;

            for (Pair<Integer, Integer> pair : pairs) {
                boolean isEqual = isBitwiseEqual(list.get(pair.getLeft()), list.get(pair.getRight()));
                if(!isEqual) {
                    mirrored = false;
                    break;
                }
                mirrored = true;
            }
            if (mirrored) {
                curMirrorSize = Math.max(curMirrorSize, pairs.get(0).getLeft()+1);
                System.out.println("wow that was a mirror! and size is " + curMirrorSize);
                break;
            }
        }
        return curMirrorSize;
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

    public static boolean isBitwiseEqual(int numA, int numB) {
        //System.out.println(numA ^ numB);
        return (numA ^ numB) == 0;
    }

    public static boolean isPowerOfTwo(int num)
    {
        if(num == 0) {
            return false;
        }
        return ((num != 1) && (num & (num - 1)) ==0);
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

    public static long countResult(List<Integer> maxValues) {
        int middle = maxValues.size()/2;
        long totalHoriz = 0L;
        long totalVert = 0L;
        for (int i=0; i<maxValues.size()/2; i++){
            long rows = maxValues.get(i);
            long columns = maxValues.get(i+middle);
            System.out.println(rows + " vs " + columns);
            if (rows > columns) {
                totalHoriz += rows;
            } else {
                totalVert += columns;
            };
        }
        return totalHoriz*100 + totalVert;
    }

    public static int listToBitInt(List<Integer> listOfZeroAndOnes) {
        StringBuilder sb = new StringBuilder();
        listOfZeroAndOnes.forEach(sb::append);
        //System.out.println("sb = " + sb);
        //System.out.println("int val = " + value);
        return Integer.parseInt(sb.toString(), 2);
    }

    public static List<Integer> bitwiseList(List<List<Integer>> inputList ) {
        List<Integer> bitwiseIntList = new ArrayList<>();
        inputList.forEach( p ->
                bitwiseIntList.add(listToBitInt(p))
        );
        return bitwiseIntList;
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

    public static List<Integer> validCombinations(List<Integer> listOfLinesInMatrix, List<Integer> listOfCombinations) {
        List<Integer> vals = new ArrayList<>();
        for (Integer lineVal : listOfLinesInMatrix) {
            for(Integer combination : listOfCombinations) {
                if(lineVal.equals(combination)){
                    vals.add(combination);
                }
            }
        }
        return vals;
    }

    static void clear() {
        inputList.clear();
    }
}

