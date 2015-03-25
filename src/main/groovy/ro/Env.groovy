package ro;

import java.util.HashMap;

/**
 * Created by roroco on 10/21/14.
 */
 class Env {
     static HashMap<String, String> coll = new HashMap();

     static String get(String key) {
        return coll.get(key);
    }

     static void set(String key, String val) {
        coll.put(key, val);
    }
}
