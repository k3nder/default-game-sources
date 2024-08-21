package net.k3nder.defaults.objects.ui;

import net.k3nder.defaults.DefaultRes;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;
import net.k3nder.gl.graphic.text.Glyph;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.lwjgl.util.freetype.FreeType.FT_New_Face;

public class Text extends StaticCube {
    protected String text = "" ;
    protected Font font;
    protected Vector2f position;
    protected Vector3f size;
    protected List<Colored> color;
    protected Background background;


    public static class Colored {
        public final Vector4f color;
        public final int OF;
        public final int TO;
        public Colored(Vector4f color, int of, int to) {
            this.color = color;
            this.OF = of;
            this.TO = to;
        }
    }

    public static Builder builder(String text) {
        return new Builder(text);
    }

    public static class Builder {
        private String text = "";
        private Font font;
        private Vector2f position;
        private Vector3f size;
        private List<Colored> color;
        private Background background;

        public Builder(String text) {
            color = new ArrayList<>();
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Font getFont() {
            return font;
        }

        public Builder font(Font font) {
            this.font = font;
            return this;
        }

        public Vector2f getPosition() {
            return position;
        }

        public Builder position(Vector2f position) {
            this.position = position;
            return this;
        }

        public Vector3f getSize() {
            return size;
        }

        public Builder size(Vector3f size) {
            this.size = size;
            return this;
        }

        public List<Colored> getColor() {
            return color;
        }

        public Builder color(Vector4f color, int of, int to) {
            this.color.add(new Colored(color, of, to));
            return this;
        }

        public Builder color(Vector4f color) {
            this.color.add(new Colored(color, 0, text.length()-1));
            return this;
        }


        public Background getBackground() {
            return background;
        }

        public Builder background(Vector4f color) {
            this.background = new Background(color, new Vector2f(position.x,position.y-0.02f), new Vector2f(10000, size.y));
            return this;
        }

        public Text build() {
            return new Text(this.font, this.text, this.position, this.size, this.background, this.color);
        }
    }

    protected Text(Font loader, String text, Vector2f pos, Vector3f size, Background background, List<Colored> color) {
        super(new Vector3f(pos.x,pos.y,0), null);
        position = new Vector2f(pos.x, pos.y);
        this.size = size;
        this.text = text;
        this.font = loader;
        model.translate(position.x, position.y, 0);
        staticShader = DefaultRes.getShader("static_model_color_mix");

        this.background = background;
        this.color = color;
    }
    public void render(Shader shader) {
        staticShader.use();

        background.render(shader);

        int charIndex = 0;
        float ip = 0;
        for (char c : text.toCharArray()) {
            AtomicReference<Vector4f> colorr = new AtomicReference<>(new Vector4f(1));
            int finalCharIndex = charIndex;
            color.forEach(colored -> {
                if (finalCharIndex >= colored.OF && finalCharIndex <= colored.TO) {
                    colorr.set(colored.color);
                }
            });
            Vector2f pos = new Vector2f(position.x + ip, position.y);
            //.out.println("c: " + c + " pos: " + pos + " img: " + font.getGlyphs().get(Character.toString(c)));
            Glyph glyph = new Glyph(font, c, pos);
            glyph.getModel().scale(new Vector3f(0.05f, 0.05f, 0.0f));
            glyph.setColor(colorr.get());
            glyph.load();
            glyph.render(staticShader);
            ip += size.x;
            charIndex++;
        }
    }
    public void setText(String text) {
        this.text = text;
    }
}
