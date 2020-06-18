import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
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
