import java.io.*;
import java.net.*;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws Exception {

        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", exchange -> {
            String filePath = "/data/count.txt";
            File file = new File(filePath);

            int count = 0;

            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                count = Integer.parseInt(br.readLine());
                br.close();
            }

            count++;

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(count));
            bw.close();

            String response = "<h1>Java Using Existing Named Volume</h1>" +
                    "<p>Visitor Count: " + count + "</p>" +
                    "<p>Volume Used: named_vol_one</p>";

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Java server running on port " + port);
    }
}
