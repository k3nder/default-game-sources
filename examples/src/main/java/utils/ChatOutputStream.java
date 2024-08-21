package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ChatOutputStream extends OutputStream {
    private StringBuffer buffer = new StringBuffer();
    @Override
    public void write(int b) throws IOException {
        // Convierte el byte a un carácter y lo agrega al buffer
        buffer.append((char) b);
    }
    public String getContent() {
        return buffer.toString();
    }
    public void reset() {
        buffer.setLength(0);  // Limpia el buffer
    }
    public List<String> getMessages() {
        return List.of(buffer.toString().split("\n"));
    }
}
