package utils;

import lombok.extern.log4j.Log4j2;
import net.k3nder.al.ALSound;
import net.k3nder.gl.graphic.model.ModelLoader;
import net.k3nder.gl.graphic.model.Polygon;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.visual.Texture;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
@Log4j2
public class Resources {
    public static Polygon model(String s) {
        return new ModelLoader().load(Resources.class.getResourceAsStream("/assets/models/" + s + "/" + s + ".obj"));
    }
    public static Texture texture(String str) {
        InputStream s = Resources.class.getResourceAsStream("/assets/textures/" + str + ".png");
        if (s == null) {
            log.warn("Could not find texture: {}", str);
        }
        try {
            Path tmpfile = Files.createTempFile("texture-", "-loader");
            FileUtils.copyInputStreamToFile(s, tmpfile.toFile());
            tmpfile.toFile().deleteOnExit();
            return Texture.loadTexture(tmpfile.toString());
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }
    public static Shader shader(String str) {
        InputStream vertexShader = Resources.class.getResourceAsStream("/assets/shaders/" + str + "/" + str + ".vert");
        InputStream fragmentShader = Resources.class.getResourceAsStream("/assets/shaders/" + str + "/" + str +  ".frag");
        if ((vertexShader == null) || (fragmentShader == null)) {
            log.warn("Could not find vertex shader: {}", str);
        }
        try {
            return Shader.create(vertexShader, fragmentShader);
        } catch (IOException e) {
            log.error("Error loading or compilating shader: {}", e, str);
        }
        return null;
    }

    public static ALSound sound(String sound) {
        try {
            return ALSound.create(Resources.class.getResourceAsStream("/assets/sounds/" + sound + ".ogg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
