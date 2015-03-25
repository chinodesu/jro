package ro.httpSv;

import com.squareup.okhttp.Response;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;
import static ro.helper.Kernel.*;
import ro.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;

/**
 * Created by roroco on 10/26/14.
 */
class Rq {
    Hash<String, String> headers = new Hash<>();
    Hash<String, String> params = new Hash<>();
    String body;

    Rq(Headers hs, URI url, InputStream body) {
        setHeaders(hs);
        setParams(url);
        setBody(body);
    }


    String header(String key) {
        return headers.get(key);
    }

    void setHeaders(Headers hs) {
        AL<String> keys = toLs(hs.keySet());
        AL<String> vals = toLs(hs.values());
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String val = vals.get(i);
            headers.set(key, val);
        }
    }

    void setParams(URI url) {
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

    String param(String key) {
        return params.get(key);
    }


    void setBody(InputStream body) {
        this.body = IOUtils.toString(body, "UTF-8");
    }


    HashMap getParams() {
        return params;
    }

    Headers getHeaders() {
        return new Headers();
    }

    String getBody() {
        return body;
    }

    static void main(String[] args) {
        final HttpServer sv = HttpServer.create(new InetSocketAddress("localhost", 23333), 0);

        sv.createContext("/", new com.sun.net.httpserver.HttpHandler() {
            void handle(HttpExchange httpExchange) {
                HttpExchange t = httpExchange;
                Object r = new Rq(t.getRequestHeaders(), t.getRequestURI(), t.getRequestBody());
                OutputStream os = t.getResponseBody();
                String rsp = "smth";
                t.sendResponseHeaders(200, rsp.length());
                os.write(rsp.getBytes());
            }
        });

        Thr.start(new Thr.Runner() {

            void run() {
                sv.start();
            }
        });

        Http h = new Http();
        for (int n : range(1, 3)) {
            try {
                Response r = h.get("localhost:23333");
                System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                System.out.println("cnn succeed" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                sv.stop(0);
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                e.printStackTrace();
            } finally {
                sv.stop(1);
            }
            sleep(1);
        }
    }
}
