package kg.com;

import java.math.BigInteger;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day7 {

    // 253638586

    public static Long executePart1() {
        List<String> strList = FileLoader.inputLines("input_day7_2.txt");
        List<CardTuple> cardTuples = strList.stream()
                .map(p -> p.split(" "))
                .map(p -> {
                    return new CardTuple(p[0], Long.valueOf(p[1]));
                })
                .toList();

        Queue<CardTuple> cardTupleQueue = new PriorityQueue<>(cardTuples);

        int idxRank = 0;
        BigInteger result = BigInteger.ZERO;
        while (!cardTupleQueue.isEmpty()) {
            CardTuple priorObj = cardTupleQueue.poll();
            idxRank++;
            result = result.add(BigInteger.valueOf(idxRank * priorObj.val2));
        }

        System.out.println(result);
        return result.longValue();
    }

    public static Long executePart2() {
        List<String> strList = FileLoader.inputLines("input_day7_2.txt");
        List<CardTuple> cardTuples = strList.stream()
                .map(p -> p.split(" "))
                .map(p -> {
                    return new CardTuple(p[0], Long.valueOf(p[1]), true);
                })
                .toList();

        Queue<CardTuple> cardTupleQueue = new PriorityQueue<>(cardTuples);

        int idxRank = 0;
        BigInteger result = BigInteger.ZERO;
        while (!cardTupleQueue.isEmpty()) {
            CardTuple priorObj = cardTupleQueue.poll();
            idxRank++;
            result = result.add(BigInteger.valueOf(idxRank * priorObj.val2));
        }

        System.out.println(result);
        return result.longValue();
    }
}
