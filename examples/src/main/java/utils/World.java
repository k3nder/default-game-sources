package utils;

import lombok.Getter;
import lombok.Setter;
import models.Block;
import net.k3nder.gravity.AABB;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class World {
    public List<Block> blocks;
    @Setter
    @Getter
    public float GRAVITY = 0.1f;
    public World() {
     blocks = new ArrayList<>();
    }
    public void render() {
        Vector3f gravityPos = new Vector3f(Game.THIS.PLAYER.getPosition());
        gravityPos.sub(new Vector3f(0, GRAVITY, 0));
        if (!Game.THIS.DETECTOR.detectCollisions(new AABB(gravityPos, gravityPos))) {
            Game.THIS.PLAYER.setPosition(new Vector3f(gravityPos.x, gravityPos.y, gravityPos.z));
        }
        blocks.forEach(block -> block.render(Game.THIS.SHADER));
        blocks.forEach(block -> block.checkCameraPointer(Game.THIS.PLAYER.getCamera()));
    }
    public void setBlock(Block block) {
        blocks.add(block);
    }
    public void rmBlock(Vector3f coords) {}
}
