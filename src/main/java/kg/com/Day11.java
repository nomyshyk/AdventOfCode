package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Day11 {

    static List<List<Character>> inputList = new ArrayList<>();
    static Set<Pair<Integer, Integer>> alreadyVisited = new LinkedHashSet<>();

    public static Long executePart1(List<String> strList, int nFactor) {
        clear();
        inputList = parse2DList(strList);

        Pair<List<Integer>, List<Integer>> listOfEmptyRowsColumns = emptyRowNColumns(inputList);

        List<Pair<Integer, Integer>> addressesOfGalaxies = getAddressesOfGalaxies(inputList);

        Set<Pair<Integer, Integer>> visitedPairs = new LinkedHashSet<>();
        long distanceTotal = 0;
        System.out.println("Amt of galaxies:" + addressesOfGalaxies.size());
        long total = 0L;
        for(int i = 1; i < addressesOfGalaxies.size(); i++) {
            total += i;
        }
        System.out.println(total);
        int countCombinations = 0;
        for (int i = 0; i < addressesOfGalaxies.size(); i++) {
            for (int j = i + 1; j < addressesOfGalaxies.size(); j++) {
                if(visitedPairs.contains(Pair.of(i, j)) || visitedPairs.contains(Pair.of(j, i))) {
                    continue;
                }
                visitedPairs.add(Pair.of(i, j));
                distanceTotal += distance(addressesOfGalaxies.get(i), addressesOfGalaxies.get(j), listOfEmptyRowsColumns, nFactor);
                //System.out.println(++countCombinations + " : " + addressesOfGalaxies.get(i) + "-" +addressesOfGalaxies.get(j) + " = " + distanceTotal);
            }
        }
        System.out.println("result:" + distanceTotal);
        return distanceTotal;
    }

    public static Long executePart2(List<String> strList) {
        return 0L;
    }


    static Long distance(Pair<Integer, Integer> pointA, Pair<Integer, Integer> pointB, Pair<List<Integer>,
            List<Integer>> listOfEmptyRowsColumns, int nFactor) {
        List<Integer> rows = listOfEmptyRowsColumns.getLeft();
        List<Integer> cols = listOfEmptyRowsColumns.getRight();

        Pair<Integer, Integer> updPointA = Pair.of(pointA);
        Pair<Integer, Integer> updPointB = Pair.of(pointB);

        for(Integer row : rows) {
            if((pointA.getLeft() > row && pointB.getLeft() < row)) {
                updPointA = Pair.of(updPointA.getLeft() + nFactor, updPointA.getRight());
            }
            if((pointA.getLeft() < row && pointB.getLeft() > row)) {
                updPointB = Pair.of(updPointB.getLeft() + nFactor, updPointB.getRight());
            }
        }

        for(Integer col : cols) {
            if((pointA.getRight() > col && pointB.getRight() < col)) {
                updPointA = Pair.of(updPointA.getLeft(), updPointA.getRight() + nFactor);
            }
            if((pointA.getRight() < col && pointB.getRight() > col)) {
                updPointB = Pair.of(updPointB.getLeft(), updPointB.getRight() + nFactor);
            }
        }


        return (long) (Math.abs(updPointA.getRight() - updPointB.getRight()) + Math.abs(updPointA.getLeft() - updPointB.getLeft()));
    }
    static List<Pair<Integer, Integer>> getAddressesOfGalaxies(List<List<Character>> inputList) {
        List<Pair<Integer, Integer>> galaxiesList = new ArrayList<>();
        for (int i = 0; i<inputList.size(); i++) {
            for (int j = 0; j < inputList.get(i).size(); j++) {
                if (inputList.get(i).get(j).equals('#')) {
                    galaxiesList.add(Pair.of(i, j));
                }
            }
        }
        return galaxiesList;
    }
    static Pair<List<Integer>, List<Integer>> emptyRowNColumns(List<List<Character>> inputList) {
        List<Integer> rowsEmptyList = new ArrayList<>();
        for (int i = 0; i<inputList.size(); i++) {
            boolean isEmpty = true;
            for (int j = 0; j < inputList.get(i).size(); j++) {
                if (inputList.get(i).get(j).equals('#')) {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                rowsEmptyList.add(i);
            }
        }

        List<Integer> columnsEmptyList = new ArrayList<>();
        int k = 0;
        for (int i = 0; i<inputList.size(); i++) {
            boolean isEmpty = true;
            for (int j = 0; j < inputList.get(i).size(); j++) {
                if (inputList.get(j).get(i).equals('#')) {
                    isEmpty = false;
                    break;
                }
                k = i;
            }
            if (isEmpty) {
                columnsEmptyList.add(k);
            }
        }

        return Pair.of(rowsEmptyList, columnsEmptyList);
    }

    static List<List<Character>> parse2DList(List<String> strList) {
        List<List<Character>> result = new ArrayList<>();
        for (String str : strList) {
            List<Character> innerList = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                innerList.add(str.charAt(i));
            }
            result.add(new ArrayList<>(innerList));
        }
        return result;
    }

    static void clear() {
        alreadyVisited.clear();
        inputList.clear();
    }
}

