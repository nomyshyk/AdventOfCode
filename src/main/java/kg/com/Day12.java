package kg.com;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

record Input (String condition, List<Integer> groups) {

}

public class Day12 {

    static List<Pair<String, List<Integer>>> inputList = new ArrayList<>();
    static Map<String, List<Integer>> cache = new HashMap<>();
    static Map<String, List<Integer>> cacheRaw = new HashMap<>();
    static Map<String, Triple<List<Integer>, Integer, List<Integer>>> cacheGlobal = new HashMap<>();
    static Pair<Integer, Integer> rawCache = null;

    public static Long executePart1(List<String> strList) {
        clear();
        inputList = parse2DList(strList);
        //System.out.println(inputList);
        long totalCnt = 0;
        for (int i = 0; i < inputList.size(); i++) {
            //System.out.println("ONE -> " + calcGroupsSizes(inputList.get(i).getLeft()));

            totalCnt += //generateFinalLine(inputList.get(i).getLeft(), inputList.get(i).getRight(), 0);
                    dynamicSolution(inputList.get(i).getLeft(), inputList.get(i).getRight());
            System.out.println(i + " -> " + totalCnt);
            cache.clear();
            rawCache = null;
            //cacheRaw.clear();
        }
        // Read input
        System.out.println(totalCnt);
        return totalCnt;
    }

    public static Long executePart2(List<String> strList) {
        clear();
        inputList = parse2DListX5(strList);
        System.out.println(inputList);
        long totalCnt = 0;
        for (int i = 0; i < inputList.size(); i++) {
            totalCnt += //generateFinalLine(inputList.get(i).getLeft(), inputList.get(i).getRight(), 0);
                    dynamicSolution(inputList.get(i).getLeft(), inputList.get(i).getRight());
            System.out.println(i + " -> " + totalCnt);
            cache.clear();
        }
        // Read input
        System.out.println(totalCnt);
        return totalCnt;
    }


    static List<Pair<String, List<Integer>>> parse2DList(List<String> strList) {
        List<Pair<String, List<Integer>>> result = new ArrayList<>();
        for (String str : strList) {
            String[] splited = str.split(" ");

            List<Integer> ints = new ArrayList<>();
            String[] valuesInt = splited[1].split(",");
            for (String val : valuesInt) {
                ints.add(Integer.parseInt(val));
            }

            result.add(Pair.of(splited[0], ints));
        }
        return result;
    }

    static List<Pair<String, List<Integer>>> parse2DListX5(List<String> strList) {
        List<Pair<String, List<Integer>>> result = new ArrayList<>();
        for (String str : strList) {
            String[] splited = str.split(" ");

            List<Integer> ints = new ArrayList<>();
            String[] valuesInt = splited[1].split(",");
            for (int i = 0; i< 5; i++) {
                for (String val : valuesInt) {
                    ints.add(Integer.parseInt(val));
                }
            }

            StringBuilder springs5x = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                springs5x.append(splited[0]).append(i!=4? "?" : "");
            }
            result.add(Pair.of(springs5x.toString(), ints));
        }
        return result;
    }

    public static Triple<List<Integer>, Integer, List<Integer>> calcGroupsSizes(String springs) {
        List<Integer> groups = new ArrayList<>();
        List<Integer> questPos = new ArrayList<>();
        int size = 0;
        int questions = 0;
        for (int i = 0; i < springs.length(); i++) {
            if (springs.charAt(i) == '#') {
                size++;
            }
            else if(springs.charAt(i) == '?') {
                questPos.add(i);
                questions++;
            }
            else {
                if(size != 0) {
                    groups.add(size);
                    size = 0;
                }
            }
        }
        if(size != 0) {
            groups.add(size);
        }
        return Triple.of(groups, questions, questPos);
    }

    public static List<Integer> calcRawGroups(String springs) {

        List<Integer> groups = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < springs.length(); i++) {
            if (springs.charAt(i) == '#') {
                size++;
            } else if (springs.charAt(i) == '?') {
                break;
            }
            else {
                if(size != 0) {
                    groups.add(size);
                    size = 0;
                }
            }
        }

        return groups;
    }

    static Map<Input, Long> memoMap = new HashMap<>();
    public static long dynamicSolution(String springs, List<Integer> groupsToCheck) {
        Input input = new Input(springs, groupsToCheck);

        if(memoMap.containsKey(input)) {
            return memoMap.get(input);
        }

        if(springs.isBlank()) {
            return groupsToCheck.isEmpty() ? 1 : 0;
        }

        char firstChar = springs.charAt(0);
        long count = 0;

        switch (firstChar) {
            case '.' : count = dynamicSolution(springs.substring(1), groupsToCheck);
                break;
            case '?' : count = dynamicSolution('.' + springs.substring(1), groupsToCheck) +
                    dynamicSolution('#' + springs.substring(1), groupsToCheck);
                break;
            default:
            {
                if (groupsToCheck.isEmpty()) {
                    count = 0;
                } else {
                    int currentGroupSize = groupsToCheck.get(0);
                    if(currentGroupSize <= springs.length()
                            && springs.chars().limit(currentGroupSize).allMatch(v -> v == '#' || v == '?')) {
                        List<Integer> subGroup = groupsToCheck.subList(1, groupsToCheck.size());
                        if(currentGroupSize == springs.length()) {
                            count = subGroup.isEmpty() ? 1 : 0;
                        } else if (springs.charAt(currentGroupSize) == '.') {
                            count = dynamicSolution(springs.substring(currentGroupSize + 1), subGroup);
                        } else if (springs.charAt(currentGroupSize) == '?') {
                            count = dynamicSolution("." + springs.substring(currentGroupSize + 1), subGroup);
                        } else {
                            count = 0;
                        }
                    } else {
                        count = 0;
                    }
                }
            }
        }
        memoMap.put(input, count);
        return count;
    }

    // non-dynamic verify Strings
    public static long generateFinalLine(String springs, List<Integer> groupsToCheck, int pos) {
        //System.out.println(springs);
        long countMatches = 0L;

        if (cache.get(springs) != null) {
            return countMatches;
        }

        Triple<List<Integer>, Integer, List<Integer>> listIntegerPair =
                cacheGlobal.getOrDefault(springs, cacheGlobal.put(springs, calcGroupsSizes(springs)));

        List<Integer> groupSizeList = listIntegerPair.getLeft();
        int numQuestions = listIntegerPair.getMiddle();
        List<Integer> questPos = listIntegerPair.getRight();
        cache.put(springs, groupSizeList);

        if (numQuestions == 0) {
            if (!groupSizeList.equals(groupsToCheck)) {
                return countMatches;
            }
            return ++countMatches;
        } else {

            if (groupSizeList.size() == groupsToCheck.size()) {
                for (int i = 0; i < groupSizeList.size(); i++) {
                    if(groupSizeList.get(i) > groupsToCheck.get(i)) {
                        //System.out.println("Here1");
                        return countMatches;
                    }
                }
            } else {
                List<Integer> rawGroups =
                        cacheRaw.getOrDefault(springs, cacheRaw.put(springs, calcRawGroups(springs)));

                if(rawGroups.size() > 0) {
                    if(rawGroups.size() >= groupsToCheck.size()) {
                        return countMatches;
                    }

                    for (int k = 0; k < rawGroups.size(); k++) {
                        if(!rawGroups.get(k).equals(groupsToCheck.get(k))) {
                            return countMatches;
                        }
                    }
                    boolean isFit = isFitInto(springs.length(), rawGroups, pos, groupsToCheck);
                    if(!isFit) {
                        //System.out.println(springs + " -> not fit");
                        return countMatches;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder(springs);
        for(int i = pos; i < questPos.size(); i++) {
            Integer questVal = questPos.get(i);

            ///////////////////////////
            for (int j = 0; j < 2; j++) {
                //nonQuestion = sb.toString().replace("?", "");
                if (j == 0) {
                    sb.replace(questVal, questVal + 1, "#");
                } else {
                    sb.replace(questVal, questVal + 1, ".");
                }

                countMatches += generateFinalLine(sb.toString(), groupsToCheck, pos);
            }
        }

        return countMatches;
    }

    public static boolean isFitInto(int springLength, List<Integer> curRawGroups, int pos, List<Integer> groupsToCheck) {

        int sumOfClosed = 0;
        for(int i = curRawGroups.size()-1; i < groupsToCheck.size(); i++) {
            sumOfClosed += groupsToCheck.get(i);
        }

        return sumOfClosed < (springLength - (pos));
    }

    static void clear() {
        //alreadyVisited.clear();
        inputList.clear();
    }
}

