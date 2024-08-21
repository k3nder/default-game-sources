package net.k3nder.defaults.objects.ui;

import net.k3nder.defaults.DefaultRes;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;
import net.k3nder.gl.graphic.text.Glyph;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class TextField extends Text {
    protected TextField(Font loader, String text, Vector2f pos, Vector3f size, Background background, List<Colored> color) {
        super(loader, text, pos, size, background, color);
    }
    public void addChar(char c) {
        text += c;
    }
    public void rmChar(int index) {
        this.text = new StringBuilder(this.text).deleteCharAt(index).toString();
    }
    public void rmLastChar() {
        if (this.text.isEmpty()) return;
        rmChar(this.text.length() - 1);
    }
    public String getText() {
        return text;
    }

    public static class Builder {
        private String text = "";
        private Font font;
        private Vector2f position;
        private Vector3f size;
        private List<Colored> color;
        private Background background;

        public Builder() {
            color = new ArrayList<>();
            this.text = "";
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

        public TextField build() {
            return new TextField(this.font, this.text, this.position, this.size, this.background, this.color);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
}
