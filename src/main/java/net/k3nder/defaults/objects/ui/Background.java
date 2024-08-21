package net.k3nder.defaults.objects.ui;


import net.k3nder.defaults.DefaultRes;
import net.k3nder.gl.graphic.GraphicalObject;
import net.k3nder.gl.graphic.shader.Shader;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Background extends GraphicalObject {
    private Shader shader;
    private Vector4f color;
    public Background(Vector4f color) {
        super();
        model.translate(new Vector3f(0, 0, 0.000001f));
        model.scale(new Vector3f(1, 1, 0));
        this.shader = DefaultRes.getShader("static_model_color");
        this.color = color;
    }
    public Background(Vector4f color, Vector2f pos) {
        super();
        model.translate(new Vector3f(pos.x, pos.y, 0.000001f));
        model.scale(new Vector3f(1, 1, 0));
        this.shader = DefaultRes.getShader("static_model_color");
        this.color = color;
    }
    public Background(Vector4f color, Vector2f pos, Vector2f size) {
        super();
        model.translate(new Vector3f(pos.x, pos.y, 0.000001f));
        model.scale(new Vector3f(size.x, size.y, 0));
        this.shader = DefaultRes.getShader("static_model_color");
        this.color = color;
    }
    public void render(Shader sh) {
        shader.use();
        shader.setV4f("color", color.x,color.y,color.z,color.w);
        shader.setMatrix(this.model, "model");
        StaticCube.CUBE.render(4);
    }
}
