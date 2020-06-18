import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.*;
import java.net.Socket;

public class Client {
    private final String hostName = "127.0.0.1";
    private final int portNumber = Integer.parseInt("4444");


    public Client() throws IOException {
        TextIO textIO = TextIoFactory.getTextIO();
        try (
                Socket socket = new Socket(hostName, portNumber);
        ) {
            File file = new File("C:\\Users\\rados\\test.txt");
            byte[] buffer = new byte[4096];
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();

            int count;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }

        }
    }
}
