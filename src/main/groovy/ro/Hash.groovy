package ro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/27/14.
 */


class Hash<K, V> extends HashMap<K, V> implements Iterable<Map.Entry<K, V>> {
    class Node {

        Map.Entry n;

        Node(Map.Entry n) {
            this.n = n;
        }

        Object key() {
            return n.getKey();
        }

        Object val() {
            return n.getValue();
        }
    }

    static Hash ii() {
        return new Hash();
    }

    void set(K k, V v) {
        put(k, v);
    }

    Node get(int _i) {
        Iterator it = entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            if (i == _i) {
                return Node.ii(e);
            }
        }

        return null;
    }

    Node first() {
        return get(0);
    }

    AL keys() {
        return toLs(keySet());
    }

    AL vals() {
        return toLs(values());
    }

    static void main(String[] args) {
        Hash h = Hash.ii();
        h.set("key", "val");
        h.set("key2", "val2");
        System.out.println("h.keys():" + h.keys() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        System.out.println("h.vals():" + h.vals() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }


    Iterator<Map.Entry<K, V>> iterator() {
        return entrySet().iterator();
    }
}
