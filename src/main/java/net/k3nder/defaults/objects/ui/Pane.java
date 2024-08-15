package net.k3nder.defaults.objects.ui;

import net.k3nder.gl.graphic.GraphicalObject;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.visual.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Pane extends StaticCube {
    private List<GraphicalObject> objects;
    public Pane(Vector3f pos, Vector3f size, Texture tex) {
        super(pos, tex);
        model.scale(size);
        load();
    }
    @Override
    public void load() {
        super.load();
        objects = new ArrayList<>();
    }
    @Override
    public void render(Shader shader) {
        texture.bind();
        glActiveTexture(GL_TEXTURE0);
        super.render(staticShader);
        staticShader.use();
        objects.forEach(obj -> obj.render(staticShader));
    }
    public void add(GraphicalObject object) {
        objects.add(object);
    }
}
