package ro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by roroco on 10/26/14.
 */
public class Path {
    String path;

    public Path(String path) {
        this.path = path;
    }

    public String toUrl() {
        Matcher m = Pattern.compile("\\:\\/\\/").matcher(path);
        String url = path;
        if (!m.find()) {
            url = "http://" + path;
        }

        return url;
    }

    public static void main(String[] args) {
        Object r = new Path("localhost:23333").toUrl();
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
