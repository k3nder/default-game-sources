package utils;

import java.io.PrintStream;
import java.util.List;

public class ChatSocket {
    public final PrintStream out;
    public final ChatOutputStream couts;
    public ChatSocket() {
        couts = new ChatOutputStream();
        out = new PrintStream(couts);
    }
    public List<String> getMessages() {
        return couts.getMessages();
    }
}
