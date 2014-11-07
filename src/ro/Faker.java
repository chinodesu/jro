package ro;

/**
 * Created by roroco on 11/6/14.
 */
public class Faker {
    static AL createdFiles = new AL();

    public static String f(String path, String... args) {
        String ctn = null;
        if (args.length > 0)
            ctn = args[0];
        String finPath = File.join(tmpRoot(), path);
        File file = new File(finPath);
        File.mkdir(file.parent());
        file.write(ctn);
        String createdFile = file.toString();
        createdFiles.add(createdFile);
        return path;
    }

    private static String tmpRoot() {
        return Pj.curRoot("tmp/test");
    }

    public static void reset() {
        File.rm(tmpRoot());
    }
}


