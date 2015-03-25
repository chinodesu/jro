package ro;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Created by roroco on 10/22/14.
 */
class Http {
    OkHttpClient c;

    Http() {
        this.c = new OkHttpClient();
    }

    Response get(String url) {
        String finUrl = Path.ii(url).toUrl();
        Request rq = new Request.Builder().url(finUrl).build();
        Response rsp = c.newCall(rq).execute();
        return rsp;
    }

    Response post(String url, String json) {
        String finUrl = new Path(url).toUrl();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(finUrl)
                .post(body)
                .build();
        Response rsp = c.newCall(request).execute();
        return rsp;
    }

    static void main(String[] args) {
    }
}
