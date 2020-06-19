import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        String text = "ala ma super kota";
        Map<String, Character> map = Huffman.generateCodes(text);
        String encoded = Huffman.encode(text);
        String decoded = Huffman.decode(encoded, map);
        System.out.println(map);
        System.out.println(encoded);
        System.out.println(decoded);


        TextIO textIO = TextIoFactory.getTextIO();
        String mode = textIO.newStringInputReader()
                .withDefaultValue("s")
                .read("Choose mode s(server)/c(client): ");
        textIO.getTextTerminal().printf("Mode %s.\n",
                mode);


        switch (mode) {
            case "s":
                Server server = new Server();
                break;
            case "c":
                Client client = new Client();
                break;
            default:
                break;
        }
    }
}
