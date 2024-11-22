package kg.com;

import java.util.Objects;

public class LongTuple2 {
    Long begins;
    Long ends;

    public LongTuple2(Long begins, Long ends) {
        this.begins = begins;
        this.ends = ends;
    }

    public LongTuple2(){}

    @Override
    public String toString() {
        return "LongTuple2{" +
                "begins=" + begins +
                ", ends=" + ends +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongTuple2 that = (LongTuple2) o;
        return begins.equals(that.begins) && ends.equals(that.ends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begins, ends);
    }
}
