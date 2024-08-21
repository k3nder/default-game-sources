package components;

import lombok.extern.log4j.Log4j2;
import net.k3nder.defaults.objects.ui.Text;
import net.k3nder.gl.graphic.shader.Shader;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
//import ui.Text;
import net.k3nder.defaults.objects.ui.ViewList;

import java.util.ArrayList;

import static utils.Game.THIS;

@Log4j2
public class Chat extends ViewList {

    public Chat(Vector2f position) {
        super(position, new ArrayList<>(), THIS.FONT);
    }

    @Override
    public void render(Shader shader) {
        // Procesa los mensajes en la cola
        var buffer = THIS.CHAT.getMessages();
        // Renderiza el contenido del buffer
        Vector2f pos = new Vector2f(textPosition.x, textPosition.y + (0.05f * (buffer.size() - 1)));
            for (String line : buffer) {
                log.debug("line: {}", line);
                Text t = Text.builder(line)
                        .font(THIS.FONT)
                        .color(new Vector4f(1,0,0,1), 0, line.indexOf(":"))
                        .position(new Vector2f(pos.x, pos.y))
                        .size(new Vector3f(0.05f,0.05f,0))
                        .background(new Vector4f(0,0,0,0.5f))
                        .build();//(line, new Vector3f(pos.x, pos.y, 0), new Vector3f(0.05f, 0.05f, 0), new Vector4f(0.1f, 0.1f, 0.1f, 0.5f));
                t.render(shader);
                pos.sub(new Vector2f(0, 0.05f));
            }

    }
}
