package ro;

import java.util.HashMap;

/**
 * Created by roroco on 10/21/14.
 */
public class Env {
    public static HashMap<String, String> coll = new HashMap();

    public static String get(String key) {
        return coll.get(key);
    }

    public static void set(String key, String val) {
        coll.put(key, val);
    }
}
