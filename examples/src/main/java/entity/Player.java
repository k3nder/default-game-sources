package entity;

import lombok.Getter;
import lombok.Setter;
import net.k3nder.gl.Camera;
import net.k3nder.gravity.AABB;
import org.joml.Vector3f;
import utils.Game;
import utils.GlobalTimer;
@Setter
@Getter
public class Player {
    private Vector3f position;
    private Camera camera;
    public Player(Vector3f position, Camera camera) {
        this.position = position;
        this.camera = camera;
    }

    public Camera camera() {
        return camera;
    }

    public void update() {
        camera.setPos(position.x, position.y+2, position.z);
    }
    public void move(Camera.Direction direction) {
        position = calcNewPosition(direction);
    }
    public Vector3f calcNewPosition(Camera.Direction direction) {
        Vector3f position = camera.calcNewPosToMove(GlobalTimer.deltatime, direction);
        if (!Game.THIS.DETECTOR.detectCollisions(new AABB(position, position))) {
            position.y = this.position.y;
            return position;
        }
        return this.position;
    }
}
