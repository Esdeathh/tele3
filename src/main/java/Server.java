import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    int portNumber = Integer.parseInt("4444");

    public Server() throws IOException {
        String encoded = null;
        Map<String, Character> map = null;
        try (
                //create socket
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            map = (Map) in.readObject();
            System.out.println("server " + map);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                //create socket
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            encoded = (String) in.readObject();
            System.out.println("server " + encoded);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String decoded = Huffman.decode(encoded, map);
        System.out.println("server decoded " + decoded);
        try (PrintWriter out = new PrintWriter("C:\\Users\\rados\\test2.txt")) {
            out.println(decoded);
        }
    }
}
