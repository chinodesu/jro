package ro;

import java.util.HashMap;
import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/27/14.
 */
public class Hash<K, V> extends HashMap<K, V> {
    public V set(K k, V v) {
        return put(k, v);
    }

    public AL keys() {
        return toList(keySet());
    }

    public AL vals() {
        return toList(values());
    }

    public static void main(String[] args) {
        Hash h = new Hash();
        h.set("key", "val");
        h.set("key2", "val2");
        System.out.println("h.keys():" + h.keys() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        System.out.println("h.vals():" + h.vals() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }

}
