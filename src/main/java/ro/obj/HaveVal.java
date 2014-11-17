package ro.obj;

import java.util.HashMap;

/**
 * Created by roroco on 11/13/14.
 */
public class HaveVal {
    Object o;

    public HaveVal(Object o) {
        this.o = o;
    }

    public boolean haveVal(Object... args) {
        if (args.length == 0) {
            try {
                if ((Boolean) o) {
                    return false;
                }
            } catch (ClassCastException exc) {
            }

            if (o instanceof String &&  o.toString().length() == 0 ) {
                return false;
            }

            if (o == null) {
                return false;
            }

            if (o instanceof Iterable) {
                int i = 0;
                for (Object e : (Iterable) o) {
                    i += 1;
                }
                if (i == 0) {
                    return false;
                }
            }

            if (o.getClass().isArray()) {
                if (((Object[]) o).length == 0) {
                    return false;
                }
            }

            if (o instanceof HashMap) {
                if (((HashMap) o).size() == 0) {
                    return false;
                }
            }

        } else {
            boolean r = true;
            for (Object o : args) {
                r = r && new HaveVal(o).haveVal();
            }
            return r;
        }

        return true;
    }
}
