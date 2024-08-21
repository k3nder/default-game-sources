package utils;


import com.mojang.brigadier.exceptions.CommandSyntaxException;
import components.Chat;
import lombok.extern.log4j.Log4j2;
import net.k3nder.defaults.DefaultRes;
import net.k3nder.defaults.objects.ui.Background;
import net.k3nder.defaults.objects.ui.TextField;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Glyph;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;
import static utils.Game.THIS;
import static utils.Game.WINDOW;

@Log4j2
public class ChatScreen extends Screen {
    private Shader staticShader;
    private TextField textField;
    private Chat chat;
    private boolean firstChar = true;

    public ChatScreen() {
        Glyph.MODEL.load();

        textField = TextField.builder()
                .font(THIS.FONT)
                .position(new Vector2f(-.98f, -.98f))
                        .size(new Vector3f(0.05f,0.06f,0))
                                .color(new Vector4f(1))
                                        .background(new Vector4f(0,0,0,0.7f))
                                                .build();

                //new TextField("command", new Vector3f(-.98f, -.98f, 0), new Vector3f(0.05f, 0.05f, 0));
        textField.getModel().rotate((float) Math.toRadians(90), new Vector3f(0, 0 ,1));

        log.debug("creating chat screen");

        chat = new Chat(new Vector2f(-.98f,-.97f));

        log.debug("ccsf");
    }

    @Override
    public void CharCallback(long window, int character) {
        if (firstChar) {
            textField.setText(String.valueOf((char) character));
            firstChar = false;
            return;
        }
        textField.addChar((char) character);
    }

    @Override
    public void KeyCallback(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ENTER && action == GLFW_PRESS) {
            try {
                THIS.DISPATCHER.execute(textField.getText(), new ChatStream("PLAYER"));
                textField.setText("");
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        } else if (key == GLFW_KEY_BACKSPACE && action == GLFW_PRESS) {
            textField.rmLastChar();
        } else if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
            WINDOW.enableChar();
            WINDOW.enableKey();
            WINDOW.enableControls();
            WINDOW.enableMouseClick();
            WINDOW.enableMousePos();
            WINDOW.enableMouseScroll();
            this.close();
        }
    }

    @Override
    public void draw() {
        //pane.render(staticShader);

        //glClear(GL_COLOR_BUFFER_BIT);
        //glClearColor(0.1f, 0.1f, 0.1f, 0.1f);

        //background.render(null);

        textField.render(staticShader);
        chat.render(staticShader);
    }

    @Override
    public void components() {
        //textField = new TextField(new Vector3f(-0.98f, -0.98f, -3f), new Vector3f(.05f,.05f, .00002f), "hello", THIS.FONT,windowID);
        //textField.load();
        //textField.setSelected(true);
        //pane = new Pane(new Vector3f(0, 0, 1f), new Vector3f(1.999f, 1.999f, 0), Block.TEXTURE);
        //pane.add(new Text(THIS.FONT, "hello", new Vector3f(-0.98f, -0.98f, 0f),  new Vector3f(.05f,.05f, .00002f)));
        staticShader = DefaultRes.getShader("static_model");
    }
    public String getText() {
        return "hh";
    }

}
