package ro.helper;

import ro.AL;
import ro.Obj;
import ro.Str;

import java.util.regex.Pattern;

/**
 * Created by roroco on 10/28/14.
 *  used by ro.*
 */
public class LightKernel {
    public static void sleep(long... args) {
        long delay = Long.MAX_VALUE;
        if (args.length > 0) {
            delay = args[0] * 1000;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static AL toList(Object... args) {
        AL al = new AL(args);
        return al;
    }

    public static boolean all(Object... _args) {
        AL a = toList(_args);
        if (a.contains(0) || a.contains(false) || a.contains(null)) {
            return false;
        }

        for (Object e : a) {
            if (obj(e).isInsOf(ro.Matcher.class) && e.toString() == null) {
                return false;
            }
        }

        return true;
    }


    public static class Todo {
        int n;

        Todo(int n) {
            this.n = n;
        }

        boolean toStop;

        public void stop() {
            toStop = true;
        }

        public interface _Lamb {
            void call(Todo p) throws Exception;
        }

        public void times(_Lamb l) throws Exception {
            for (int i = 1; i <= n; i++) {
                if (toStop) {
                    break;
                }
                l.call(this);
            }
        }
    }


    public static Todo todo(int n) {
        return new Todo(n);
    }

    public static Obj obj(Object o) {
        return new Obj(o);
    }

    public static Str str(Object...ss) {
        return new Str(ss);
    }

    public static void main(String[] args) throws Exception {
//        todo(1).times((p) -> {
//            System.out.println("smth" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//        });

        Object r = all(Pattern.compile("r").matcher("ruby"));
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
