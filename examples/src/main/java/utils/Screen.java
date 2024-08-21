package utils;

public abstract class Screen {
    protected boolean open;
    public void KeyCallback(long window, int button, int scancode, int action, int mods) {
    }
    public void ControlsCallback(long window) {
    }
    public void CharCallback(long window, int character) {
    }
    public void MouseButtonCallback(long window, int button, int action, int mods) {
    }
    public void MousePosCallback(long id, double x, double y) {
    }
    public void MouseClickCallback(long id, int button, int action, int mods) {
    }
    public void MouseScrollCallback(long id, double x, double y) {
    }
    public abstract void draw();
    public abstract void components();
    public final void open() {
        open = true;
    }
    public final void close() {
        open = false;
    }
    public final boolean isOpen() {
        return open;
    }
}
