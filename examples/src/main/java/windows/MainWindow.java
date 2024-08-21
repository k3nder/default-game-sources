package windows;

import commands.CommandCallback;
import lombok.extern.log4j.Log4j2;
import models.Block;
import net.k3nder.al.ALSound;
import net.k3nder.al.ALSource;
import net.k3nder.defaults.objects.ui.StaticCube;
import net.k3nder.gl.Camera;
import net.k3nder.gl.Window;
import net.k3nder.gravity.AABB;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import utils.ChatScreen;
import utils.Game;
import utils.GlobalTimer;
import utils.Resources;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glClearColor;
@Log4j2
public class MainWindow extends Window {
    private boolean firstMouse;
    private ALSource sound;
    public ChatScreen chatScreen;
    public MainWindow() {
        super("game");

        this.makeContextCurrent();
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL11.GL_BLEND);
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        Game.THIS = new Game();

        log.debug("Loading sound sources");

        ALSound alSound = Resources.sound("example");
        this.sound = ALSource.create(alSound);

        log.debug("Sound loaded");

        Block.POLYGON.load();
        StaticCube.CUBE.load();
        Game.THIS.PLAYER.getCamera().setPos(4,0,0);

        chatScreen = new ChatScreen();
        chatScreen.components();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Game.THIS.WORLD.setBlock(new Block(new Vector3f(i, -1, j)));
            }
        }

        new Thread(new CommandCallback()).start();

    }
    @Override
    public void draw() {
        GlobalTimer.update();

        Game.THIS.PLAYER.getCamera().setWidth(width());
        Game.THIS.PLAYER.getCamera().setHeight(height());

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        Game.THIS.SHADER.use();

        Game.THIS.PLAYER.getCamera().apply(Game.THIS.SHADER);

        Game.THIS.PLAYER.update();

        Game.THIS.WORLD.render();

        if (chatScreen.isOpen()) {
            disableControls();
            disableChar();
            disableKey();
            disableMousePos();
            chatScreen.draw();
            glfwSetCharCallback(id, chatScreen::CharCallback);
            glfwSetKeyCallback(id, chatScreen::KeyCallback);
        } else {
            enableControls();
            enableChar();
            enableKey();
            enableMousePos();
        }
    }
    @Override
    public void ControlsCallback() {
        if (glfwGetKey(id, GLFW_KEY_W) == GLFW_PRESS) {
            Vector3f pos = Game.THIS.PLAYER.getCamera().calcNewPosToMove(GlobalTimer.deltatime+0.01f, Camera.Direction.FRONT);
            if (Game.THIS.DETECTOR.detectCollisions(new AABB(pos, pos))) return;
            Game.THIS.PLAYER.move(Camera.Direction.FRONT);
        }
        if (glfwGetKey(id, GLFW_KEY_A) == GLFW_PRESS) {
            Vector3f pos = Game.THIS.PLAYER.getCamera().calcNewPosToMove(GlobalTimer.deltatime+0.01f, Camera.Direction.LEFT);
            if (Game.THIS.DETECTOR.detectCollisions(new AABB(pos, pos))) return;
            Game.THIS.PLAYER.move(Camera.Direction.LEFT);
        }
        if (glfwGetKey(id, GLFW_KEY_D) == GLFW_PRESS) {
            Vector3f pos = Game.THIS.PLAYER.getCamera().calcNewPosToMove(GlobalTimer.deltatime+0.01f, Camera.Direction.RIGHT);
            if (Game.THIS.DETECTOR.detectCollisions(new AABB(pos, pos))) return;
            Game.THIS.PLAYER.move(Camera.Direction.RIGHT);
        }
        if (glfwGetKey(id, GLFW_KEY_S) == GLFW_PRESS) {
            Vector3f pos = Game.THIS.PLAYER.getCamera().calcNewPosToMove(GlobalTimer.deltatime+0.01f, Camera.Direction.BACK);
            if (Game.THIS.DETECTOR.detectCollisions(new AABB(pos, pos))) return;
            Game.THIS.PLAYER.move(Camera.Direction.BACK);
        }
    }
    @Override
    public void CharCallback(long id, int chars) {
    }

    @Override
    public void KeyCallback(long id, int button, int scancode, int ac, int mods) {
        if (button == GLFW_KEY_C && ac == GLFW_PRESS) {
            //text.setSelected(true);
            disableControls();
            disableChar();
            disableKey();
            disableMousePos();
            disableMouseScroll();
            chatScreen.open();
        } else if (button == GLFW_KEY_V && ac == GLFW_PRESS) {
            sound.play();
        }
    }
    @Override
    public void MousePosCallback(long id, double x, double y) {
        if (firstMouse)
        {
            Game.THIS.PLAYER.getCamera().setLastX((float) x);
            Game.THIS.PLAYER.getCamera().setLastY((float) y);
            firstMouse = false;
        }

        Game.THIS.PLAYER.getCamera().rotate(x, y);
        Game.THIS.PLAYER.getCamera().updateRotation();
    }
    @Override
    public void window() {
        super.window();
        //glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        windowed();
    }
    @Override
    public void CloseCallback(long id) {
        System.exit(0);
    }
}
