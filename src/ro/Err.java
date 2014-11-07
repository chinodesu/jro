package ro;

import com.google.common.base.Joiner;

/**
 * Created by roroco on 11/7/14.
 */
public class Err extends RuntimeException {
    String sort;

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Err(String message) {
        super(message);
    }


    public static Err instance(String sort, String... args) {
        String msg = "";

        if (args.length > 0) {
            Joiner jr = Joiner.on(" ");
            msg = jr.join(args);
        }

        Err e = new Err(msg);
        e.setSort(sort);
        return e;
    }

    public boolean is(String sort) {
        return this.sort == sort;
    }

    public void toThrow() {
        try {
            throw this;
        } catch (Err err) {
            err.printStackTrace();
            System.exit(-1);
        }
    }
}
