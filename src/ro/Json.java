package ro;

import com.google.gson.Gson;
import ro.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by roroco on 10/22/14.
 */
public class Json {

    public interface _Lamb {
        void call(JSONObject json);
    }

    public static String build(_Lamb l) {
        JSONObject json = new JSONObject();
        l.call(json);
        return json.toString();
    }

    public static String dump(Object o) {
        return new Gson().toJson(o);
    }

    public static Object load(String json, Type type) {
        return new Gson().fromJson(json, type);
    }

    public static void main(String[] args) {
        Object r = Json.build((j) -> {
            j.add("key", "val");
        });
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
