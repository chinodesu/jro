package ro;

import ro.obj.HaveVal;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by roroco on 10/22/14.
 * <pre>
 *      import static ro.helper.Kernel.*;
 * </pre>
 */

class Obj {

    Object o;

    Obj(Object o) {
        this.o = o;
    }

    boolean ic(Object _o) {
        if (isIterable()) {
            for (Object e : (Iterable) o) {
                if (e == _o) {
                    return true;
                }
            }
        }

        if (isArr()) {
            for (Object e : (Object[]) o) {
                if (e == _o) {
                    return true;
                }
            }
        }

        return false;
    }

    Object staticField(String fieldName) {
        java.lang.reflect.Field[] declaredFields = ((Class) o).getDeclaredFields();
        AL<java.lang.reflect.Field> staticFields = new AL<java.lang.reflect.Field>();
        for (java.lang.reflect.Field f : declaredFields) {
            if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                staticFields.add(f);
            }
        }
        for (java.lang.reflect.Field f : staticFields) {
            if (f.getName() == fieldName) {
                f.setAccessible(true);
                try {
                    return f.get(o);
                } catch (IllegalAccessException e) {
                    return null;
                }
            }
        }
        return null;
    }

    boolean isHash() {
        return isIns(Map.class);
    }

    Object send(String methName, Object... args) {
        o.getClass().getDeclaredMethod(methName).invoke(o, args);
    }

    /**
     * <pre>
     *     isInstance("a str", String.class); // => true
     * </pre>
     */
    boolean isIns(Class kls) {
        return kls.isInstance(o);
    }

    class Field {
        Object f;

        Field(Object f) {
            this.f = f;
        }

        boolean is(Object o) {
            return f == o;
        }
    }

    Field getField(String fieldName) {
        try {
            java.lang.reflect.Field field = o.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return new Field(field.get(o));
        } catch (IllegalAccessException e) {
            System.out.println(e + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            return null;
        } catch (NoSuchFieldException e) {
            System.out.println(e + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            return null;
        }
    }

    boolean isLamb(Object o) {
        return match(o, "lambda");
    }

    boolean haveVal(Object... args) {
        return new HaveVal(o).haveVal(args);
    }

    boolean hasInsOf(AL a, Class c) {
        for (Object e : a) {
            if (isIns(c)) {
                return true;
            }
        }
        return false;
    }

    String path() {
        Class kls = (Class) o;
        String curDir = System.getProperty("user.dir");
        String pkg = kls.getPackage().getName();
        String klsName = kls.getName();
        return new File(curDir, pkg, klsName).toString();
    }

    boolean isColl() {
        if (o == null) {
            return false;
        }
        return isIterable() || isArr();
    }

    // commonly used in intellij idea watches tool window

    Object grep(String re) {
        if (!isIns(Hash.class) && isColl()) {
            ArrayList a = new ArrayList();

            if (isIterable()) {
                for (Object e : (Iterable) o) {
                    _grep(a, e, re);
                }

            }

            if (isArr()) {
                for (Object e : (Object[]) o) {
                    _grep(a, e, re);
                }
            }
            return a;
        }

        if (isHash()) {
            Hash h = new Hash();
            Iterator i = ((Map) o).entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                Object key = e.getKey();
                Object val = e.getValue();
                if (match(key, re) || match(val, re)) {
                    h.put(key, val);
                }
            }

            return h;
        }

        java.lang.reflect.Field[] fs = o.getClass().getDeclaredFields();
        Hash h = new Hash();

        for (java.lang.reflect.Field f : fs) {
            f.setAccessible(true);
            String name = f.getName();
            Object val = null;
            try {
                val = f.get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (match(name, re) || match(val, re)) {
                h.put(name, val);
            }
        }

        return h;
    }


    void _grep(ArrayList a, Object e, String re) {
        if (match(e, re)) {
            a.add(e);
        }
    }

    private boolean match(Object e, String re) {
        return Pattern.compile(re).matcher(e.toString()).find();
    }

    boolean isArr() {
        return o.getClass().isArray();
    }

    boolean isIterable() {
        return isIns(Iterable.class);
    }
}
