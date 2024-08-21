package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {
    private float time;
    private float lastShot;
    public void shot() {
        lastShot = (float) glfwGetTime();
    }
    public void update() {
        time = (float) (lastShot - glfwGetTime());
    }
    public boolean isTime(float time) {
        return time >= this.time;
    }
}
