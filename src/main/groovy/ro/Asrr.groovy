package ro;

/**
 * Created by roroco on 10/21/14.
 */
 class Asrr {

    static void _assert(Object result, String... args) {
        String failed_msg = "";

        if (args.length > 1) {
            failed_msg = args[0];
        }

        if (!(Boolean) result) {
            throw new AssertionError(failed_msg);
        }
    }

    static void eq(Object one, Object another) {
        _assert(one == another, Str._join("assert", one, "eq to", another, "fail").toString());
    }

     static void main(String[] args) {
        Asrr._assert(true);
    }
}
