package ro.helper;

import ro.AL;
import ro.Obj;
import ro.Range;
import ro.Str;

import java.util.regex.Pattern;

/**
 * Created by roroco on 10/28/14.
 * used by ro.*
 */
 class LightKernel {
     static void sleep(long... args) {
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

     static AL toLs(Object... args) {
        AL al = AL.ii(args);
        return al;
    }

     static boolean all(Object... _args) {
        AL a = toLs(_args);
        if (obj(a).ic(0) || obj(a).ic(false) || obj(a).ic(null)) {
            return false;
        }

        for (Object e : a) {
            if (obj(e).isIns(ro.Matcher.class) && e.toString() == null) {
                return false;
            }
        }

        return true;
    }


     boolean isJunit() {
        String s = System.getProperty("java.class.path");
        return Pattern.compile("junit").matcher(s).find();
    }


     static class Todo {
        int n;

        Todo(int n) {
            this.n = n;
        }

        boolean toStop;

         void stop() {
            toStop = true;
        }

        interface Proc {
            void call(Todo p) ;
        }

         void times(Proc l)  {
            for (int i = 1; i <= n; i++) {
                if (toStop) {
                    break;
                }
                l.call(this);
            }
        }
    }

     static Todo todo(int n) {
        return new Todo(n);
    }

     static Obj obj(Object o) {
        return new Obj(o);
    }

//     ContiguousSet<Integer> range(Integer from, Integer to) {
//        return ContiguousSet.create(Range.closed(from, to), DiscreteDomain.integers());
//    }

     static Range range(int from, int to) {
        return new Range(from, to);
    }

     static Str str(Object... ss) {
        return new Str(ss);
    }

     static void main(String[] args)  {
//        todo(1).times((p) -> {
//            System.out.println("smth" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//        });

        Object r = all(Pattern.compile("r").matcher("ruby"));
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
