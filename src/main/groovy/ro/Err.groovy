package ro;

import com.google.common.base.Joiner;

/**
 * Created by roroco on 11/7/14.
 */
class Err extends RuntimeException {

    String type;

    Err(String msg) {
        super(msg);
    }

    static def ii(name) {
        def br = new KlsBuilder(name, RuntimeException, new Err().class.classLoader)
        br.build()
    }

    void setType(String type) {
        this.type = type;
    }


    static Err ii(String Type, Object... args) {
        String msg = "";

        if (args.length > 0) {
            Joiner jr = Joiner.on(" ");
            msg = jr.join(args);
        }

        Err e = new Err(msg);
        e.setType(Type);
        return e;
    }

    String toString() {
        return type + " " + getMessage();
    }

    boolean is(String sort) {
        return this.type == sort;
    }

    void toThrow() {
        throw this;
    }
}
