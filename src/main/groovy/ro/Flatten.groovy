package ro;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import static ro.helper.LightKernel.obj;

 class Flatten {
     static ArrayList flatten(Object a) {
        if (obj(a).isColl()) {
            if (obj(a).isArr()) {
                return new Flatten()._flatten((Object[]) a);
            }

            if (obj(a).isIterable()) {
                return new Flatten()._flatten((Iterable) a);
            }
        }
        return null;
    }

    ArrayList finA = new ArrayList();

     ArrayList _flatten(Object[] a) {
        for (Object e : a) {
            if (obj(e).isIterable()) {
                _flatten((Iterable) e);
            } else if (obj(e).isArr()) {
                _flatten((Object[]) e);
            } else {
                finA.add(e);
            }
        }
        return finA;
    }


     ArrayList _flatten(Iterable a) {
        for (Object e : a) {
            if (obj(e).isIterable()) {
                _flatten((Iterable) e);
            } else if (obj(e).isArr()) {
                _flatten((Object[]) e);
            } else {
                finA.add(e);
            }
        }
        return finA;
    }
}
