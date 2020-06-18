import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int portNumber = Integer.parseInt("4444");

    public Server() throws IOException {
        try (
                //create socket
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();

                InputStream in = clientSocket.getInputStream();
        ) {
            FileOutputStream out = new FileOutputStream("C:\\Users\\rados\\test2.txt");
            int count;
            byte[] buffer = new byte[4096];

            while ((count = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, count);
            }

            out.close();
        }
    }
}
