package ro;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;

import ro.httpSv.*;

/**
 * Created by roroco on 10/26/14.
 */
 class HttpSv {

     static HttpSv ii() throws IOException {
        return new HttpSv();
    }

    HttpServer sv;

     HttpSv() throws IOException {
        this.sv = HttpServer.create(new InetSocketAddress(23333), 0);
    }

     void setPort(int port) throws IOException {
        sv = HttpServer.create(new InetSocketAddress(port), 0);
    }

     void get(String url, Handler h) {
        sv.createContext(url, new HttpHandler() {
             void handle(HttpExchange t) {

            }
        });
    }

     void start() {
        sv.start();
    }

     static void main(String[] args) throws IOException {
        final HttpSv sv = HttpSv.ii();

        sv.get("/", new Handler() {
            @Override
             String handle(Rq rq) {
                System.out.println(rq.getBody() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
                return "";
            }
        });

        Thr.start(new Thr.Runner() {
            @Override
             void run() {
                sv.start();
            }
        });

        Http h = new Http();
        while (true) {
            try {
                h.post("localhost:23333", Json.build(new Json.Proc() {
                    @Override
                     void call(JSONObject json) {
                        json.add("key", "val");
                    }
                }));
                break;
            } catch (ConnectException e) {
                System.out.println(e + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            }
        }

    }
}
