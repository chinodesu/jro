package ro;

import java.io.*;

import static ro.helper.LightKernel.str;

/**
 * Created by roroco on 10/31/14.
 */
public class File {
    public File(String... fs) {
        String finPath = new Str(fs, "separator:", "/").toString();
        this.f = new java.io.File(finPath);
    }

    java.io.File f;

    public File(String f) {
        this.f = new java.io.File(f);
    }

    public static void rm(String path) {
        System.out.println(str("rm", path) + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        new java.io.File(path).delete();
    }

    public static boolean isDir(String path) {
        return new java.io.File(path).isDirectory();
    }

    public String parent() {
        return f.getParent();
    }

    public static String mkdir(String path) {
        System.out.println(str("mkdir", path).toMsg() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        java.io.File dir = new java.io.File(path);
        dir.mkdir();
        if (dir.isDirectory()) {
            return dir.getAbsolutePath();
        }
        throw Err.instance("NotExist", "mkdir", path, "fail");
    }

    public String toString() {
        return f.getAbsolutePath();
    }

    public static String join(Object... args) {
        return Str.join(args, "separator:", "/").toString();
    }

    public String read() {
        try {
            System.out.println(str("read", f).toMsg() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            BufferedReader reader = new BufferedReader(new FileReader(f));
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public void write(String ctn) {
        try {
            System.out.println(str("write", f.toString()).toMsg() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(ctn);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return;
    }


    public boolean exist() {
        return f.exists();
    }
}
