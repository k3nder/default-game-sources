package net.k3nder.defaults.objects.ui;

import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;
import net.k3nder.gl.graphic.visual.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Button extends StaticCube {
    private Text text;
    public Button(Vector3f pos, Vector2f size, Texture tex, String text, Font font) {
        super(pos, tex);
        model.scale(new Vector3f(size.x, size.y, 0f));
        this.text = new Text(font, text, pos, new Vector3f(.045f, .045f, 0.0002f));
        load();
    }
    @Override
    public void render(Shader shader) {
        super.render(staticShader);
        text.render(staticShader);
    }
    @Override
    public void load() {
        super.load();
        this.text.load();
    }
    public boolean mouseOver(double x, double y) {
        Vector3f minn = getMin();
        Vector3f maxx = getMax();

        Vector2f min = new Vector2f(minn.x, minn.y);
        Vector2f max = new Vector2f(maxx.x, maxx.y);

        return ( min.x <= x && x <= max.x && max.y <= y && y <= max.y );
    }
}
