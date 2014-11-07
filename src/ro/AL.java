package ro;

import com.google.common.base.Joiner;

import java.util.ArrayList;

import static ro.helper.LightKernel.obj;

/**
 * Created by roroco on 10/24/14.
 */
public class AL<T> extends ArrayList<T> {
    public AL(T... args) {
        if (args == null) {
            add(null);
            return;
        }
        ArrayList finArgs;
        for (T arg : args) {
            if (arg == null) {
                continue;
            }
            try {
                if (obj(arg).isIterable()) {
                    finArgs = new Flatten().flatten((Iterable) arg);
                    for (Object e : finArgs) {
                        add((T) e);
                    }
                } else if (obj(arg).isArr()) {
                    finArgs = new Flatten().flatten((Object[]) arg);
                    for (Object e : finArgs) {
                        add((T) e);
                    }
                } else {
                    add(arg);
                }
            } catch (Exception exc) {
                throw exc;
            }
        }
    }

    public String join(String separator) {
        Joiner jr = Joiner.on(separator);
        return jr.join(this);
    }

    public AL hits() {
        return flatten();
    }

    class Flatten {
        ArrayList finA = new ArrayList();

        public ArrayList flatten(Object a) {

            try {
                if (obj(a).isIterable()) {
                    for (Object e : (Iterable) a) {
                        if (obj(e).isColl()) {
                            flatten((Iterable) e);
                        } else {
                            finA.add(e);
                        }
                    }
                }

                if (obj(a).isArr()) {
                    for (Object e : (Object[]) a) {
                        if (obj(e).isColl()) {
                            flatten(e);
                        } else {
                            finA.add(e);
                        }
                    }
                }
            } catch (Exception exc) {
                throw exc;
            }
            return finA;
        }
    }

    public int length() {
        return size();
    }

    public AL flatten() {
        ArrayList<T> a = new Flatten().flatten(this);
        clear();
        for (T e : a) {
            add(e);
        }
        return this;
    }

    public interface _Lamb {
        Object call(Object o);
    }

    public AL map(_Lamb l) {
        AL thisClone = clone();
        for (int i = 0; i < size(); i++) {
            Object e = thisClone.get(i);
            T finE = (T) l.call(e);
            set(i, finE);
        }

        return this;
    }

    public AL clone() {
        AL a = new AL();
        for (Object e : this) {
            a.add(e);
        }
        return a;
    }

    public static void main(String[] args) {
        AL<String> items = new AL<String>("arg", "arg2");
        for (Object e : items) {
            System.out.println("smth" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        }
    }
}
