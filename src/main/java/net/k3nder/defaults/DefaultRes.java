package net.k3nder.defaults;

import net.k3nder.gl.graphic.model.ModelLoader;
import net.k3nder.gl.graphic.model.Polygon;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;

import java.io.IOException;
import java.util.Objects;

public class DefaultRes {
    public static Shader getShader(String name) {
        try {
            return Shader.create(
                    Objects.requireNonNull(DefaultRes.class.getResourceAsStream("/default/shaders/" + name + "/" + name + ".vert")),
                    Objects.requireNonNull(DefaultRes.class.getResourceAsStream("/default/shaders/" + name + "/" + name + ".frag"))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Font getFont(String name, int size) {
        try {
            return new Font(Objects.requireNonNull(DefaultRes.class.getResourceAsStream("/default/fonts/" + name + ".ttf")), size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Polygon getPolygon(String name) {
        try {
            return new ModelLoader().load(Objects.requireNonNull(DefaultRes.class.getResourceAsStream("/default/models/" + name + "/" + name + ".obj")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
