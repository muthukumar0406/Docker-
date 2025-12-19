import java.io.*;
import java.net.*;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws Exception {

        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", exchange -> {

            String dataFile = "/data/students.txt";
            File file = new File(dataFile);

            int count = 1;
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while (br.readLine() != null) {
                    count++;
                }
                br.close();
            }

            String studentName = "Student_" + count;

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(studentName + "\n");
            bw.close();

            StringBuilder allStudents = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                allStudents.append(line).append("\n");
            }
            br.close();

            String response = "<h1>Student Record Added</h1>" +
                    "<p>New Student: <b>" + studentName + "</b></p>" +
                    "<h3>All Students (Persistent)</h3>" +
                    "<pre>" + allStudents + "</pre>";

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Student record system running on port " + port);
    }
}
