package net.k3nder.defaults.objects.ui;

import net.k3nder.gl.graphic.GraphicalObject;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;


public class ViewList extends GraphicalObject {
    protected List<String> buffer;
    protected Vector2f textPosition;
    protected Font font;
    public ViewList(Vector2f position, List<String> buffer, Font font) {
        super();
        model.setTranslation(new Vector3f(position.x, position.y, 0));
        textPosition = new Vector2f(position.x, position.y+0.05f);
        this.buffer = buffer;
        this.font = font;
    }
    public void render(Shader shader) {
        float i = 0;
        for (String str : buffer) {
            Text t = Text.builder(str)
                    .font(font)
                    .color(new Vector4f(1,1,1,1))
                    .position(new Vector2f(textPosition.x, textPosition.y+i))
                    .size(new Vector3f(0.05f,0.05f,0))
                    .background(new Vector4f(0))
                    .build();
                    //Text(str, new Vector3f(textPosition.x, textPosition.y+i, 0), new Vector3f(0.05f, 0.05f, 0), new Vector4f(0));
            t.render(shader);
            i += 0.05f;
        }
    }
    public void print(String string) {
        buffer.add(string);
    }
    public void load() {}
}
