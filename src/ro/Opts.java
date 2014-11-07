package ro;

import java.util.regex.Pattern;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/22/14.
 */
public class Opts extends Hash {
    public static Opts get(Object... args) {
        Opts opts = new Opts();
        AL finArgs = toList(args);
        AL a = new AL();

        for (int i = 0; i < finArgs.size(); i++) {
            try {
                Object arg = finArgs.get(i);
                Object isKey = Pattern.compile("\\:$").matcher((String) arg).find();
                if (all(obj(arg).isInsOf(String.class), isKey)) {
                    a.add(arg.toString());
                    String k = arg.toString().replace(":", "");
                    Object v = finArgs.get(i + 1);
                    a.add(v);
                    i++;
                    opts.set(k, v);
                }
            } catch (Exception exc) {
                throw exc;
            }
        }

        finArgs.removeAll(a);
        opts.setRestArgs(finArgs);
        return opts;
    }

    AL restArgs;

    public AL getRestArgs() {
        return restArgs;
    }

    public void set(String key, Object val) {
        put(key, val);
    }

    public void setRestArgs(AL restArgs) {
        this.restArgs = restArgs;
    }

    public Object m(Object[] arg) {
        Object[] a = {1, 2};
        arg = a;
        return arg;
    }

    public static void main(String[] args) {
        Object r = Opts.get("arg", "arg2", "key:", "val", "key2:", "val2");
        System.out.println(Inspect.inspect(r) + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
