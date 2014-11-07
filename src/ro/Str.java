package ro;

import com.google.common.base.Joiner;

import java.util.regex.Pattern;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/22/14.
 */
public class Str {
    String s;

    Object[] initArgs;

    public static Str join(Object... args) {
        Opts opts = Opts.get(args);
        String separator = null;
        if (all(opts.get("separator"))) {
            separator = (String) opts.get("separator");
        }

        if (separator == null) {
            separator = "";
        }

        Joiner jr = Joiner.on(separator);
        String finStr = jr.join(toList(opts.restArgs).flatten());
        Str s = new Str(finStr);
        return s;
    }

    public Str(Object... ss) {
        initArgs = ss;
        AL finSs = toList(ss).flatten();
        this.s = Str.join(finSs).toString();
    }

    public Str(String s) {
        this.s = s;
    }

    public String toString() {
        return s.toString();
    }

    public Matcher match(String re) {
        Matcher m = matcher(s, re);
        m.find();
        return m;
    }

    public String toMsg() {
        return join(initArgs, "separator:", " ").toString();
    }

    public boolean exist() {
        return new ro.File(s).exist();
    }

    private Matcher matcher(Object str, String re) {
        Pattern p = Pattern.compile(re);
        return new Matcher(p.matcher(str.toString()));
    }

    public Str gsub(String from, String to) {
        return str(s.replaceAll(from, to));
    }

    public static void main(String[] args) {
        Object r = Str.join("1", "2", "separator:", ",");
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
