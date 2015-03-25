package ro;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

import static ro.helper.LightKernel.obj;

/**
 * Created by roroco on 11/3/14.
 */

 class Trace {

    class Frame {
        StackTraceElement frame;
        AL<StackTraceElement> frames;
        AL<StackTraceElement> restFrames;

        String kls;
        String meth;
        int lineno;
        String filename;

         void setup() {
            kls = frame.getClassName();
            meth = frame.getMethodName();
            lineno = frame.getLineNumber();
            filename = frame.getFileName();
            if (restFrames == null) {
                restFrames = frames;
            }
        }

         Frame(StackTraceElement frame) {
            this.frame = frame;
            setup();
        }

         Frame(StackTraceElement _frame, StackTraceElement[] _frames) {
            frame = _frame;
            frames = AL.ii(_frames);
            setup();
        }

         Frame(StackTraceElement frame, AL frames, AL restFrames) {
            this.frame = frame;
            this.frames = frames;
            this.restFrames = restFrames;
            setup();
        }

         String kls() {
            return kls;
        }

         String meth() {
            return meth;
        }

         int lineno() {
            return lineno;
        }

         String filename() {
            return filename;
        }

         Frame not(Object... args) {
            Opts<String, Object> opts = Opts.get(args);
            if (anyMatch(frame, opts)) {
                int i = -1;
                for (StackTraceElement f : frames) {
                    i++;
                    if (anyMatch(f, opts)) {
                        continue;
                    } else {
                        return new Frame(f, frames, frames.range(i + 1));
                    }
                }
            } else {
                return this;
            }
            return null;
        }

        boolean anyMatch(StackTraceElement _f, opts) {
            Frame f = new Frame(_f);
            for (Map.Entry<String, Object> e : opts) {
                String k = e.getKey();
                Object v = e.getValue();
                if (v instanceof Pattern && ((Pattern) v).matcher((String) obj(f).send(k)).find()) {
                    return true;
                }

                if (v instanceof String && v ==  obj(f).send(k).toString()) {
                    return true;
                }
            }
            return false;
        }
    }


    Frame last;
    Frame cur;

     Trace() {
        StackTraceElement[] frames = new Exception().getStackTrace();
        cur = new Frame(frames[1], frames);
        last = new Frame(frames[2], frames);
    }

     Frame cur() {
        return cur.not("kls:", this.getClass().getName()).not("kls:", "Kernel");
    }

     Frame last() {
        return last.not("kls:", this.getClass().getName()).not("kls:", "Kernel");
    }

     String curMeth() {
        return cur().meth();
    }

     String lastMeth() {
        return new Exception().getStackTrace()[2].getMethodName();
    }

     String lastLoc() {
        return new Exception().getStackTrace()[2].getMethodName();
    }
}
