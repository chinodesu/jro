package ro

import org.apache.commons.lang3.SystemUtils;

/**
 * Created by roroco on 10/20/14.
 */
class Pj {
    static boolean isTest() {
        roStage() == "test"
    }

    static os = os()

    static String os() {
        if (SystemUtils.IS_OS_MAC) {
            return 'osx'
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            return 'windows'
        }

        if (SystemUtils.IS_OS_UNIX) {
            return 'unix'
        }
    }

    static boolean isDev() {
        return roStage() == "dev";
    }

    static String env(String key) {
        return Env.get(key);
    }

    static void setEnv(String key, String val) {
        Env.set(key, val);
    }

    static String roStageKey = "RO_STAGE";

    static String roStage() {
        Object r = env(roStageKey);
        if (r == null) {
            return "dev";

        } else {
            return "test";
        }
    }

    static String cur(String... args) {
        String _cur = System.getProperty("user.dir");
        return File.join(_cur, args);
    }

    static String src(String... args) {
        return File.join(cur("src"), args);
    }

    static String lib(String... args) {
        return File.join(src("main/java"));
    }

    static String test(String... args) {
        String test = src("test/java");
        String adTest = src("test/androidTest");
        if (Dir.exist(test)) {
            return File.join(test, args);
        } else {
            return File.join(adTest, args);
        }
    }

    static void setDev() {
        setEnv(roStageKey, "dev");
    }

    static void setTest() {
        setEnv(roStageKey, "test");
    }

    static void main(String[] args) {
        Pj pj = new Pj();
        Asrr.eq(pj.roStage(), "dev");
        pj.setTest();
        Asrr.eq(pj.roStage(), "test");
    }
}
