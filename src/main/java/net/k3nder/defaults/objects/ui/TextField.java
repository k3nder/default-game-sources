package net.k3nder.defaults.objects.ui;

import net.k3nder.gl.DefaultRes;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;
import net.k3nder.gl.graphic.text.Glyph;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class TextField extends Text {
    private boolean selected = false;
    private Long window;
    private int cursorPos = 0;
    private Shader selectedShader;
    public TextField(Vector3f pos, Vector3f size, String placeText, Font font, long winID) {
        super(font, placeText, pos, size);
        window = winID;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
    @Override
    public void load() {
        super.load();
        selectedShader = DefaultRes.getShader("static_selected");
    }
    double lastPressTime = 0;
    double delayTime = 0.12;
    @Override
    public void render(Shader shader) {

        if (selected && (lastPressTime < (glfwGetTime() - delayTime))) {
            if (glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS) {
                cursorPos++;
                lastPressTime = glfwGetTime();
            } else if (glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
                cursorPos--;
                lastPressTime = glfwGetTime();
            }

        }

        staticShader.use();
        staticShader.setMatrix(model, "model");

        float ip = 0;
        int charIndex = 0;
        for (char c : text.toCharArray()) {
            if (cursorPos == charIndex) {
                 selectedShader.use();
                selectedShader.setMatrix(model, "model");
                System.out.println("sss: " + c);
            }
            Vector2f pos = new Vector2f(position.x + ip, position.y);
            Glyph glyph = new Glyph(font, c, pos);
            glyph.setModel(new Matrix4f().translate(pos.x, pos.y, 0).scale(new Vector3f(size.x, size.y + (charIndex == cursorPos ? 1 : 0), 0.0f)));
            glyph.load();
            glyph.render((charIndex == cursorPos ? selectedShader : staticShader));
            if (cursorPos == charIndex) {
                staticShader.use();
                staticShader.setMatrix(model, "model");
            }
            ip += size.x + size.z;
            charIndex++;
        }
    }
    public void addChar(char c) {
        text += c;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
