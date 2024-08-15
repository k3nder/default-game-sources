package net.k3nder.defaults.objects;

import net.k3nder.defaults.DefaultRes;
import net.k3nder.gl.graphic.GraphicalObject;
import net.k3nder.gl.graphic.model.ModelLoader;
import net.k3nder.gl.graphic.model.Polygon;
import net.k3nder.gl.graphic.visual.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;

public class Cube extends GraphicalObject {
    public static Polygon CUBE = DefaultRes.getPolygon("cube");
    public Cube(Vector3f pos, Texture t) {
        super();
        texture = t;
        model.translate(pos);
    }
    @Override
    public void load() {
        polygon = CUBE;
    }
}
