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
public class HttpSv {

    HttpServer sv;

    public HttpSv() throws IOException {
        this.sv = HttpServer.create(new InetSocketAddress(23333), 0);
    }

    public void setPort(int port) throws IOException {
        sv = HttpServer.create(new InetSocketAddress(port), 0);
    }

    public void get(String url, Handler h) {
        sv.createContext(url, new HttpHandler() {
            public void handle(HttpExchange t) {

            }
        });
    }

    public void start() {
        sv.start();
    }

    public static void main(String[] args) throws IOException {
        HttpSv sv = new HttpSv();
        sv.get("/", (rq) -> {
            System.out.println(rq.getBody() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            return "";
        });

        Thr.start(() -> {
            sv.start();
        });

        Http h = new Http();
        while (true) {
            try {
                h.post("localhost:23333", Json.build((j) -> {
                    j.add("key", "val");
                }));
                break;
            } catch (ConnectException e) {
            System.out.println(e + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            }
        }

    }
}
