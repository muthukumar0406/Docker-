import java.io.*;
import java.net.*;
import com.sun.net.httpserver.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {

        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", exchange -> {

            String logPath = "/logs/app.log";
            BufferedWriter writer = new BufferedWriter(new FileWriter(logPath, true));
            writer.write("Visited at: " + java.time.LocalDateTime.now() + "\n");
            writer.close();

            String response = "<h1>Java Bind Mount</h1><p>Log updated in host!</p>";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        });

        server.start();
        System.out.println("Java server running on port " + port);
    }
}
