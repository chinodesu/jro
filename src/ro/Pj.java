package ro;


/**
 * Created by roroco on 10/20/14.
 */
public class Pj {

    public static boolean isTest() {
        return roStage() == "test";
    }

    public static boolean isDev() {
        return roStage() == "dev";
    }

    public static String env(String key) {
        return Env.get(key);
    }

    public static void setEnv(String key, String val) {
        Env.set(key, val);
    }

    public static String roStageKey = "RO_STAGE";

    public static String roStage() {
        Object r = env(roStageKey);
        if (r == null) {
            return "dev";

        } else {
            return "test";
        }
    }

    public static String curRoot(String... args) {
        String cr = System.getProperty("user.dir");
        return File.join(cr, args);
    }

    public static void setDev() {
        setEnv(roStageKey, "dev");
    }

    public static void setTest() {
        setEnv(roStageKey, "test");
    }

    public static void main(String[] args) {
        Pj pj = new Pj();
        Asserter.eq(pj.roStage(), "dev");
        pj.setTest();
        Asserter.eq(pj.roStage(), "test");
    }
}
