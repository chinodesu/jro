package ro;

import org.json.JSONException;

/**
 * Created by roroco on 10/27/14.
 */
 class JSONObject extends org.json.JSONObject {
     JSONObject add(String k, Object v) {
        return (JSONObject) put(k, v);
    }
}
