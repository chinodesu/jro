package ro;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by roroco on 10/22/14.
 * <pre>
 *      import static ro.helper.Kernel.*;
 * </pre>
 */

public class Obj {

    Object o;

    public Obj(Object o) {
        this.o = o;
    }

    public Object staticField(String fieldName) {
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

    public boolean isHash() {
        return isInsOf(Map.class);
    }

    /**
     * <pre>
     *     isInstance("a str", String.class); // => true
     * </pre>
     */
    public <T> boolean isInsOf(Class kls) {
        return kls.isInstance(o);
    }

    public class Field {
        Object f;

        public Field(Object f) {
            this.f = f;
        }

        public boolean is(Object o) {
            return f == o;
        }
    }

    public Field getField(String fieldName) {
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

    public boolean isLamb(Object o) {
        return Pattern.compile("lambda").matcher(o.toString()).find();
    }

    public boolean hasInsOf(AL a, Class c) {
        for (Object e : a) {
            if (isInsOf(c)) {
                return true;
            }
        }
        return false;
    }

    public String path() {
        Class kls = (Class) o;
        String curDir = System.getProperty("user.dir");
        String pkg = kls.getPackage().getName();
        String klsName = kls.getName();
        return new File(curDir, pkg, klsName).toString();
    }

    public boolean isColl() {
        if (o == null) {
            return false;
        }
        return isIterable() || isArr();
    }

    public boolean isArr() {
        return o.getClass().isArray();
    }

    public boolean isIterable() {
        return isInsOf(Iterable.class);
    }
}
