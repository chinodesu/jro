package ro;

import com.google.common.base.Joiner;

import java.util.regex.Pattern;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/22/14.
 */
class Str {
    String s;

    Object[] initArgs;

    static Str _join(String[] ss) {
        return _join(ss);
    }

    static Str _join(... args) {
        def opts = null

        (args, opts) = Opts.get(args);

        def separator = ''

        if (opts) {
            if (opts['separator']) {
                separator = opts['separator'];
            }
        }

        Joiner jr = Joiner.on(separator);
        String finStr = jr.join(toLs(args).flatten());
        Str s = new Str(finStr);
        return s;
    }

    Str join(String... args) {
        return Str._join(s, args);
    }

//     Str(String[] ss) {
//        initArgs = ss;
//        this.sec = Str._join(ss).toString();
//    }

    Str(Object... os) {
        initArgs = os;
        AL finSs = toLs(os).flatten();
        this.s = Str._join(finSs).toString();
    }

    Str(Object s) {
        this.s = s.toString();
    }

    Str(String[] ss) {
        new Str(ss);
    }

    String toString() {
        return s.toString();
    }

    boolean match(String re_str) {
        return all(matcher(re_str));
    }

    String camelize() {
        String[] ss = toString().split("");
        ss[0] = ss[0].toUpperCase();
        return new Str(ss).join("").toString();
    }

    String toCmd() {
        return toMsg();
    }

    String toMsg() {
        return _join(initArgs, "separator:", " ").toString();
    }

    boolean exist() {
        return new ro.File(s).exist();
    }

    Matcher matcher(Object str, String re) {
        Pattern p = Pattern.compile(re);
        return new Matcher(p.matcher(str.toString()));
    }

    Str gsub(String from, String to) {
        return str(s.replaceAll(from, to));
    }


    static void main(String[] args) {
        Object r = Str._join("1", "2", "separator:", ",");
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
