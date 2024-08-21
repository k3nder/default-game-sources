package models;

import net.k3nder.gl.graphic.GraphicalObject;
import net.k3nder.gl.graphic.model.Polygon;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.visual.Texture;
import net.k3nder.gravity.AABB;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL13;
import utils.Resources;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static utils.Game.THIS;

public class Block extends GraphicalObject {
    public static final Polygon POLYGON = Resources.model("block");
    public static final Texture TEXTURE = Resources.texture("block");
    private float color;
    private boolean decrement = false;
    public AABB aabb;

    public Block(Vector3f pos) {
        super();
        model.translate(pos);
        model.scale(0.5f);
        color = 1.0f;
        selected = false;
        aabb = new AABB(getMin(), getMax());
        THIS.DETECTOR.add(aabb);
    }
    @Override
    public void render(Shader shader) {
        shader.use();
        shader.setV4f("color", color,color,color, 1);
        GL13.glActiveTexture(33984);
        TEXTURE.bind();
        shader.setI("tex", 0);
        shader.setMatrix(this.model, "model");
        POLYGON.render(4);
        if (selected) {
            if (color >= 2 && !decrement) {
                decrement = true;
            } else if (color <= 1 && decrement) {
                decrement = false;
            }
            if (decrement) {
                color -= 0.01f;
            } else {
                color += 0.01f;
            }
        } else color = 1f;
        shader.setV4f("color", 1,1,1,1);
    }
    @Override
    public Vector3f[] getTransformedVertices() {

        FloatBuffer vertices_total = Block.POLYGON.getVertices();

        List<Vector3f> vertices = new ArrayList<>(vertices_total.capacity());

        for (int i = 0; i < vertices_total.limit(); i += 8) {
            vertices.add(new Vector3f(vertices_total.get(i), vertices_total.get(i+1), vertices_total.get(i+2)));
        }

        Vector3f[] transformedVertices = new Vector3f[8];

        for(int i = 0; i < 8; ++i) {
            transformedVertices[i] = vertices.get(i).mulPosition(this.model, new Vector3f());
        }

        return transformedVertices;
    }
}
