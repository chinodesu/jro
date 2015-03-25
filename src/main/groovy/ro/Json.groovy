package ro;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by roroco on 10/22/14.
 */
 class Json {

    interface Proc {
        void call(JSONObject json);
    }

     static String build(Proc l) {
        JSONObject json = new JSONObject();
        l.call(json);
        return json.toString();
    }

     static String dump(Object o) {
        return new Gson().toJson(o);
    }

     static Object load(String json, Type type) {
        return new Gson().fromJson(json, type);
    }

     static void main(String[] args) {
        Object r = Json.build(new Proc() {

             void call(JSONObject json) {
                json.add("key", "val");
            }
        });
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
