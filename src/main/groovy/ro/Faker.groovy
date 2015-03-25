package ro;

/**
 * Created by roroco on 11/6/14.
 */
 class Faker {
    static AL createdFiles = new AL();

    interface Proc {
         void call(Faker f);
    }

     static void tap(Proc p) {
        p.call(new Faker());
    }

     static String f(String path, String... args) {
        String ctn = "";
        if (args.length > 0)
            ctn = args[0];
        String finPath = File.join(tmpRoot(), path);
        File file = File.ii(finPath);
        File.mkdir(file.parent());
        file.write(ctn);
        String createdFile = file.toString();
        createdFiles.add(createdFile);
        return finPath;
    }


    private static String tmpRoot() {
        return Pj.cur("tmp/test");
    }

     static void reset() {
        File.rm(tmpRoot());
    }
}


