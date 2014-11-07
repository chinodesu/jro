package ro.httpSv;

import com.squareup.okhttp.Response;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;
import ro.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/26/14.
 */
public class Rq {
    Hash<String, String> headers = new Hash<>();
    Hash<String, String> params = new Hash<>();
    String body;

    public Rq(Headers hs, URI url, InputStream body) throws IOException {
        setHeaders(hs);
        setParams(url);
        setBody(body);
    }


    public String header(String key) {
        return headers.get(key);
    }

    public void setHeaders(Headers hs) {
        AL<String> keys = toList(hs.keySet());
        AL<String> vals = toList(hs.values());
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String val = vals.get(i);
            headers.set(key, val);
        }
    }

    public void setParams(URI url) {
        String q = url.getQuery();
        if (all(q)) {
            AL<String> a = new AL<String>(q.split("&"));
            Hash<String, String> ps = new Hash();
            for (String p : a) {
                String name = p.split("=")[0];
                String value = p.split("=")[1];
                ps.set(name, value);
            }
            this.params = ps;
        }
    }

    public String param(String key) {
        return params.get(key);
    }


    public void setBody(InputStream body) throws IOException {
        this.body = IOUtils.toString(body, "UTF-8");
    }


    public HashMap getParams() {
        return params;
    }

    public Headers getHeaders() {
        return new Headers();
    }

    public String getBody() {
        return body;
    }

    public static void main(String[] args) throws Exception {
        HttpServer sv = HttpServer.create(new InetSocketAddress("localhost", 23333), 0);
        sv.createContext("/", (t) -> {
            try {
                Object r = new Rq(t.getRequestHeaders(), t.getRequestURI(), t.getRequestBody());
                System.out.println(Inspect.inspect(r) + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                OutputStream os = t.getResponseBody();
                String rsp = "smth";
                t.sendResponseHeaders(200, rsp.length());
                os.write(rsp.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });

        Thr.start(() -> {
            sv.start();
        });

        Http h = new Http();
        todo(3).times((p) -> {
            try {
                Response r = h.get("localhost:23333");
                System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                System.out.println("cnn succeed" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                sv.stop(0);
                p.stop();
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                e.printStackTrace();
            } finally {
                sv.stop(1);
            }
            sleep(1);
        });

    }
}
