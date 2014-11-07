package ro;

import java.lang.reflect.Field;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/24/14.
 */
public class Inspect {
    public static String inspect(Object o) {
        String r = o.getClass().getName();

        if (obj(o).isColl()) {
            AL a = toList(o);
            String s;
            s = a.map((e) -> {
                return e.toString();
            }).join(", ");
            r = r + " " + "{" + s + "}";
        }

        if (obj(o).isHash()) {
            Hash h = (Hash) o;
            AL keys = h.keys();
            AL vals = h.vals();
            for (int i = 0; i < keys.length(); i++) {

            }
        }

        Field[] fs = o.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            String val;
            try {
                r = r + ", " + f.getName() + "=" + f.get(o).toString();
            } catch (IllegalAccessException e) {
                System.out.println(e + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                r = r + " " + f.getName() + "=" + "null";
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        Object r = Inspect.inspect(arr);
        System.out.println("r:" + r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
