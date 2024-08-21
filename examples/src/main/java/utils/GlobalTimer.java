package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class GlobalTimer {
    public static float deltatime = 0.0f;
    public static float lastframe = 0.0f;

    public static void update() {
        float currentframe = (float) glfwGetTime();
        deltatime = currentframe - lastframe;
        lastframe = currentframe;
    }
}
