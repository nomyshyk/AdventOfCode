package kg.com;

import java.util.*;

public class CardTuple implements Comparable{

    String cards;
    Long val2;
    boolean isJoker;
    Double summa;
    Double razr;
    String oldCard;
    // idx, score
    Map<Integer, Integer> indexForce = new TreeMap<>();

    static Map<Character, Integer> pointsMap = new LinkedHashMap<>();

    static {
        pointsMap.put('2', 2);
        pointsMap.put('3', 3);
        pointsMap.put('4', 4);
        pointsMap.put('5', 5);
        pointsMap.put('6', 6);
        pointsMap.put('7', 7);
        pointsMap.put('8', 8);
        pointsMap.put('9', 9);
        pointsMap.put('T', 10);
        pointsMap.put('J', 11);
        pointsMap.put('Q', 12);
        pointsMap.put('K', 13);
        pointsMap.put('A', 14);
    }

    static Map<Character, Integer> jokerMap = new LinkedHashMap<>();

    static {
        jokerMap.put('2', 2);
        jokerMap.put('3', 3);
        jokerMap.put('4', 4);
        jokerMap.put('5', 5);
        jokerMap.put('6', 6);
        jokerMap.put('7', 7);
        jokerMap.put('8', 8);
        jokerMap.put('9', 9);
        jokerMap.put('T', 10);
        jokerMap.put('J', 1);
        jokerMap.put('Q', 12);
        jokerMap.put('K', 13);
        jokerMap.put('A', 14);
    }

    public CardTuple(String cards, Long val2) {
        this.cards = cards;
        this.val2 = val2;
    }

    public CardTuple(String cards, Long val2, boolean isJoker) {
        this.cards = cards;
        this.val2 = val2;
        this.cards = enrichJokerString();
        enrichObject();
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if(!(o instanceof CardTuple)) {
            return 0;
        }

        CardTuple anotherCard = (CardTuple) o;

        List<Double> thisDoubles = decideBasedOnFlag(this.cards, this.isJoker, this.oldCard);
        List<Double> anotherDoubles = decideBasedOnFlag(anotherCard.cards, anotherCard.isJoker, anotherCard.oldCard);

        Double thisRank = thisDoubles.get(1);
        Double anotherRank = anotherDoubles.get(1);

        Double thisWeight = thisDoubles.get(0);
        Double anotherWeight = anotherDoubles.get(0);

        if (thisRank/10 > anotherRank/10) {
            return 1;
        } else if (thisRank/10 < anotherRank/10) {
            return -1;
        } else {
            if (thisWeight > anotherWeight) {
                return 1;
            } else if (thisWeight < anotherWeight) {
                return -1;
            }
        }

        return 0;
    }

    public Map<Character, Long> enrichObject () {
        Map<Character, Long> map = new HashMap<>();
        Double razr = 0.0;
        for (int i = 0; i < this.cards.length(); i++) {
            map.put(this.cards.charAt(i), map.getOrDefault(this.cards.charAt(i), 0L) + 1);
            razr += Math.pow(14, this.cards.length()-i-1) *
                (this.isJoker ? jokerMap.get(this.cards.charAt(i)) : pointsMap.get(this.cards.charAt(i)));
        }
        this.razr = razr;

        Double wordWeight = 0.0;
        for (Map.Entry<Character, Long> entry: map.entrySet()) {

            wordWeight += (Math.pow(10, entry.getValue()-1));
        }
        this.summa = wordWeight;

        return map;
    }

    public String enrichJokerString () {
        Map<Character, Long> map = new HashMap<>();
        int countJokers = 0;
        for (int i = 0; i < this.cards.length(); i++) {
            if(this.cards.charAt(i) == 'J') {
                countJokers++;
            }
            map.put(this.cards.charAt(i), map.getOrDefault(this.cards.charAt(i), 0L) + 1);
        }

        if(countJokers == 0) {
            return this.cards;
        }
        if(countJokers == 5) {
            this.oldCard = new String(this.cards);
            this.cards = "AAAAA";
            this.isJoker = true;
            return this.cards;
        }

        char strongestNonJoker = 0;
        Long summa = 0L;
        for (Map.Entry<Character, Long> entry: map.entrySet()) {
            if (entry.getKey().equals('J')) {
                continue;
            }

            if((entry.getValue() * 100) + (entry.getKey()) > summa ) {
                summa = (entry.getValue() * 100) + (entry.getKey());
                strongestNonJoker = entry.getKey();
            }
        }
        this.isJoker = true;
        this.oldCard = new String(this.cards);
        return this.cards.replace('J', strongestNonJoker);
    }

    public List<Double> decideBasedOnFlag(String myCard, boolean jokerbi, String oldCard) {
        Map<Character, Long> map = new HashMap<>();
        List<Double> result = new ArrayList<>();

        Double razr = 0.0;
        for (int i = 0; i < myCard.length(); i++) {
            map.put(myCard.charAt(i), map.getOrDefault(myCard.charAt(i), 0L) + 1);
            razr += Math.pow(14, myCard.length()-i-1) *
                    (jokerbi ? jokerMap.get(oldCard.charAt(i)) : pointsMap.get(myCard.charAt(i)));
        }
        result.add(razr);

        Double wordWeight = 0.0;
        for (Map.Entry<Character, Long> entry: map.entrySet()) {
            wordWeight += (Math.pow(10, entry.getValue()-1));
        }
        result.add(wordWeight);

        return result;
    }

    @Override
    public String toString() {
        return "CardTuple{" +
                "cards='" + cards + '\'' +
                ", val2=" + val2 +
                ", isJoker=" + isJoker +
                ", summa=" + summa +
                ", razr=" + razr +
                ", oldCard='" + oldCard + '\'' +
                ", indexForce=" + indexForce +
                '}';
    }
}
