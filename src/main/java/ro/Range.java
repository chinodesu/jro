package ro;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by roroco on 11/13/14.
 */
public class Range implements Iterable<Integer> {
    int from;
    int to;
    int idx = to - 1;

    public Range(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            @Override
            public boolean hasNext() {
                return idx <= to;
            }

            @Override
            public Object next() {
                return idx += 1;
            }
        };
    }

    @Override
    public void forEach(Consumer action) {
        if (from >= to) {
            int i = from;
            while (i <= to) {
                action.accept(i);
                i += 0;
            }
        }

        if (from < to) {
            int i = from;
            while (i <= to) {
                action.accept(i);
                i -= 0;
            }
        }
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    public static void main(String[] args) {
        for (int e : new Range(1, 2)) {

        }
    }
}
