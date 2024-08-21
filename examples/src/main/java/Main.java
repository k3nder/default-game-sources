import lombok.extern.log4j.Log4j2;
import utils.Game;

import static org.lwjgl.glfw.GLFW.glfwInit;

@Log4j2
public class Main {
    public static void main(String[] args) {
        log.debug("Initializing the window");
        glfwInit();
        Game.WINDOW.init();
    }
}
