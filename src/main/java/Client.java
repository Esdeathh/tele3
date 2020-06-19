import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Client {
    private final String hostName = "127.0.0.1";
    private final int portNumber = Integer.parseInt("4444");


    public Client() throws IOException {
        String text = Files.readString(Paths.get("C:\\Users\\rados\\test.txt"), StandardCharsets.US_ASCII);
        Map<String, Character> map = Huffman.generateCodes(text);
        String encoded = Huffman.encode(text);
        System.out.println("przed kodowaniem: " + text);
        System.out.println("kody: " + map);
        System.out.println("po kodowaniu: " + encoded);

        try (
                Socket socket = new Socket(hostName, portNumber);
        ) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(map);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (
                Socket socket = new Socket(hostName, portNumber);
        ) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(encoded);
        }
    }
}
