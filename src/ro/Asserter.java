package ro;

/**
 * Created by roroco on 10/21/14.
 */
public class Asserter {

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
        _assert(one == another, Str.join("assert", one, "eq to", another, "fail").toString());
    }

    public static void main(String[] args) {
        Asserter._assert(true);
    }
}
