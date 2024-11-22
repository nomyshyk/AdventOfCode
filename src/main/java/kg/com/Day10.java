package kg.com;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Day10 {

    static List<List<Character>> inputList = new ArrayList<>();
    static Set<Pair<Integer, Integer>> alreadyVisited = new LinkedHashSet<>();

    public static Long executePart1(List<String> strList) {
        clear();
        inputList = parse2DList(strList);
        Pair<Integer, Integer> headPosition = head(inputList);
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.push(headPosition);
        boolean isCycle = false;

        while (!stack.isEmpty()) {
            Pair<Integer, Integer> currentPos = stack.pop();

            if (alreadyVisited.size() > 0 && currentPos.equals(headPosition)) {
                isCycle = true;
                break;
            }

            if (alreadyVisited.contains(currentPos)) {
                continue;
            }

            alreadyVisited.add(currentPos);
            char curSymbol = inputList.get(currentPos.getLeft()).get(currentPos.getRight());

            List<Pair<Integer, Integer>> directionsToGoList
                    = listDirectionsToGo(currentPos, curSymbol);


            for (Pair<Integer, Integer> potentialDirections : directionsToGoList) {
                if (isDestPointOK(potentialDirections)) {
                    if (curSymbol != 'S' || isInitDestPtsOk(currentPos, potentialDirections)
                    ) {
                        stack.push(potentialDirections);
                    }
                }
            }
        }


        System.out.println("isCycle = " + isCycle + ", summa=" + alreadyVisited.size() / 2);
        long summa = isCycle ? (alreadyVisited.size() / 2) : alreadyVisited.size();

        return summa;
    }

    public static Long executePart2(List<String> strList) {
        Long justForFun = executePart1(strList);

        Map<Pair<Integer, Integer>, Integer> pointAndIntersects = new LinkedHashMap<>();
        for(int i = 0; i < inputList.size(); i++) {

            for (int j = 0; j < inputList.get(i).size() ; j++) {
                // intersects
                if(!alreadyVisited.contains(Pair.of(i, j))) {
                    int intersectCount = 0;
                    for (int k = j; k < inputList.get(i).size() ; k++) {
                        if(alreadyVisited.contains(Pair.of(i, k))) {
                            if(inputList.get(i).get(k).equals('|') ||
                                    inputList.get(i).get(k).equals('F') ||
                                    inputList.get(i).get(k).equals('S') ||
                                    inputList.get(i).get(k).equals('7')) {
                                intersectCount++;
                            }
                        }
                    }
                    pointAndIntersects.put(Pair.of(i, j), intersectCount);
                }
            }
        }

        // System.out.println(pointAndIntersects);
        long totalCount = 0L;
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : pointAndIntersects.entrySet()) {
            if(entry.getValue() > 0 && entry.getValue() % 2 == 1) {
                totalCount++;
            }
        }
        System.out.println("points inside = " + totalCount);

        return totalCount;
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

    static Pair<Integer, Integer> head(List<List<Character>> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.get(i).size(); j++) {
                if ('S' == inputList.get(i).get(j)) {
                    return Pair.of(i, j);
                }
            }
        }
        return null;
    }

    static boolean isInitDestPtsOk(Pair<Integer, Integer> curPos, Pair<Integer, Integer> toPoint) {
        if (curPos.getLeft() > toPoint.getLeft()) {
            return switch (inputList.get(toPoint.getLeft()).get(toPoint.getRight())) {
                case 'L', 'J' -> false;
                default -> true;
            };
        }
        if (curPos.getLeft() < toPoint.getLeft()) {
            return switch (inputList.get(toPoint.getLeft()).get(toPoint.getRight())) {
                case '7', 'F' -> false;
                default -> true;
            };
        }
        if (curPos.getRight() > toPoint.getRight()) {
            return switch (inputList.get(toPoint.getLeft()).get(toPoint.getRight())) {
                case '7', 'J' -> false;
                default -> true;
            };
        }
        if (curPos.getRight() < toPoint.getRight()) {
            return switch (inputList.get(toPoint.getLeft()).get(toPoint.getRight())) {
                case 'L', 'F' -> false;
                default -> true;
            };
        }
        return true;
    }

    // if OK returns coords, otherwise null
    static boolean isDestPointOK(Pair<Integer, Integer> toPoint) {

        if ((toPoint.getLeft() < 0) || toPoint.getRight() < 0) {
            return false;
        }
        int xLen = inputList.size();
        int yLen = inputList.get(toPoint.getLeft()).size();

        if (toPoint.getLeft() >= xLen) {
            return false;
        }

        if (toPoint.getLeft() >= yLen) {
            return false;
        }

        if (inputList.get(toPoint.getLeft()).get(toPoint.getRight()) == '.') {
            return false;
        }

        return true;
    }

    static List<Pair<Integer, Integer>> listDirectionsToGo(Pair<Integer, Integer> curPosition, Character symbol) {
        List<Pair<Integer, Integer>> directions = new ArrayList<>();
        if (symbol == 'S') {
            directions.add(moveUp(curPosition));
            directions.add(moveRight(curPosition));
            directions.add(moveDown(curPosition));
            directions.add(moveLeft(curPosition));
        } else if (symbol == '|') {
            directions.add(moveUp(curPosition));
            directions.add(moveDown(curPosition));
        } else if (symbol == '-') {
            directions.add(moveLeft(curPosition));
            directions.add(moveRight(curPosition));
        } else if (symbol == 'L') {
            directions.add(moveRight(curPosition));
            directions.add(moveUp(curPosition));
        } else if (symbol == 'J') {
            directions.add(moveLeft(curPosition));
            directions.add(moveUp(curPosition));
        } else if (symbol == '7') {
            directions.add(moveLeft(curPosition));
            directions.add(moveDown(curPosition));
        } else if (symbol == 'F') {
            directions.add(moveRight(curPosition));
            directions.add(moveDown(curPosition));
        } else {
            return null;
        }
        return directions;
    }

    static Pair<Integer, Integer> moveUp(Pair<Integer, Integer> curPosition) {
        return Pair.of(curPosition.getLeft() - 1, curPosition.getRight());
    }

    static Pair<Integer, Integer> moveDown(Pair<Integer, Integer> curPosition) {
        return Pair.of(curPosition.getLeft() + 1, curPosition.getRight());
    }

    static Pair<Integer, Integer> moveLeft(Pair<Integer, Integer> curPosition) {
        return Pair.of(curPosition.getLeft(), curPosition.getRight() - 1);
    }

    static Pair<Integer, Integer> moveRight(Pair<Integer, Integer> curPosition) {
        return Pair.of(curPosition.getLeft(), curPosition.getRight() + 1);
    }

    static void clear() {
        alreadyVisited.clear();
        inputList.clear();
    }
}

