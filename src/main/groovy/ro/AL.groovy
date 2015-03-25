package ro;

import com.google.common.base.Joiner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import static ro.helper.LightKernel.obj;

/**
 * Created by roroco on 10/24/14.
 */
class AL<T> extends ArrayList<T> {

    static AL ii(Object[] args) {
        return new AL(args);
    }

    AL() {
        super();
    }

    AL(T[] args) {
        if (args == null) {
            add(null);
            return;
        }

        ArrayList finArgs = null;
        for (T arg : args) {
            if (arg == null) {
                continue;
            }
            if (obj(arg).isColl()) {
                finArgs = Flatten.flatten(arg);

                for (Object e : finArgs) {
                    add((T) e);
                }

            } else {
                add(arg);
            }
        }
    }

    AL(Iterable<T> it) {
        for (T i : it) {
            add(i);
        }
    }

    T first() {
        if (length() > 0) {
            return (T) get(0);
        }
        return null;
    }

    T last() {
        if (length() > 0) {
            return get(length() - 1);
        }

        return null;
    }

    String join(String separator) {
        Joiner jr = Joiner.on(separator);
        return jr.join(this);
    }

    String join() {
        return join("");
    }

    AL hits() {
        return flatten();
    }

    AL<T> grep(... os) {
        AL<T> finA = AL.ii();
        String re = AL.ii(os).join();
        for (Object o : this) {
            if (o.toString().matches(re)) {
                finA.add((T) o);
            }
        }
        return finA;
    }


    int length() {
        return size();
    }

    AL flatten() {
        ArrayList<T> a = Flatten.flatten(this);
        clear();
        for (T e : a) {
            add(e);
        }
        return this;
    }

    interface Proc {
        Object call(Object o);
    }

    String toMsg() {
        return join(" ");
    }

    String toCmd() {
        return toMsg();
    }

    AL map(Proc l) {
        AL thisClone = clone();
        for (int i = 0; i < size(); i++) {
            Object e = thisClone.get(i);
            T finE = (T) l.call(e);
            set(i, finE);
        }

        return this;
    }

    AL clone() {
        AL a = AL.ii();
        for (Object e : this) {
            a.add(e);
        }
        return a;
    }

    interface Proc2 {
        Boolean call(o);
    }

    AL<T> select(Proc2 p) {
        AL<T> finA = new AL<T>();
        for (T e : this) {
            if (p.call(e)) {
                finA.add(e);
            }
        }
        return finA;
    }


    AL range(int from, int to) {
        AL<T> a = new AL<T>();
        int i = 0;
        for (T e : a) {
            if (i >= from && i <= to) {
                a.add(e);
            }
        }
        return a;
    }

    AL range(int from) {
        AL<T> a = new AL<T>();
        int i = 0;
        for (T e : a) {
            if (i >= from && i <= a.length()) {
                a.add(e);
            }
        }
        return a;
    }

    static void main(String[] args) {
        AL<String> items = new AL<String>(["arg", "arg2"]);
        for (Object e : items) {
            System.out.println("smth" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        }
    }
}
