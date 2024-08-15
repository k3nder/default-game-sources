package net.k3nder.defaults.objects.ui;

import net.k3nder.gl.DefaultRes;
import net.k3nder.defaults.objects.Cube;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.visual.Texture;
import org.joml.Vector3f;

public class StaticCube extends Cube {
    protected Shader staticShader;
    public StaticCube(Vector3f pos, Texture tex) {
        super(pos, tex);
        model.translate(pos);
    }
    @Override
    public void render(Shader shader) {
        staticShader.use();
        super.render(staticShader);
    }
    @Override
    public void load() {
        super.load();
        staticShader = DefaultRes.getShader("static_model");
    }
}
