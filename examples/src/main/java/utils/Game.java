package utils;

import com.mojang.brigadier.CommandDispatcher;
import entity.Player;
import net.k3nder.al.ALContext;
import net.k3nder.al.ALDevice;
import net.k3nder.defaults.DefaultRes;
import net.k3nder.gl.Camera;
import net.k3nder.gl.graphic.shader.Shader;
import net.k3nder.gl.graphic.text.Font;
import net.k3nder.gravity.CollisionDetector;
import net.k3nder.gravity.SpatialGrid;
import org.joml.Vector3f;
import org.lwjgl.openal.ALCapabilities;
import windows.MainWindow;

public class Game {
    public static final MainWindow WINDOW = new MainWindow();
    public final Player PLAYER;
    public final Shader SHADER;
    public final Integer GRID_SIZE = 10;
    public final Integer WORLD_SIZE = 100;
    public final SpatialGrid GRID = new SpatialGrid(GRID_SIZE, WORLD_SIZE);
    public final CollisionDetector DETECTOR = new CollisionDetector(GRID);
    public final CommandDispatcher<ChatStream> DISPATCHER = new CommandDispatcher<>();
    public final World WORLD;
    public final Font FONT;
    public final ChatSocket CHAT;
    public static Game THIS;
    public final ALDevice AL_DEVICE;
    public final ALContext AL_CONTEXT;
    public final ALCapabilities AL_CAPABILITIES;
    public Game() {

        this.PLAYER = new Player(new Vector3f(0, 4, 0) ,Camera.create(900, 800));
        this.SHADER = Resources.shader("game");
        this.WORLD = new World();
        this.FONT = DefaultRes.getFont("Roboto/Roboto-Regular", 48);
        this.CHAT = new ChatSocket();

        // Open AL
        this.AL_DEVICE = new ALDevice();
        this.AL_CONTEXT = this.AL_DEVICE.createContext();
        this.AL_CONTEXT.makeContextCurrent();
        this.AL_CAPABILITIES = this.AL_DEVICE.getCapabilities();




    }
}
