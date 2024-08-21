package commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import models.Block;
import org.joml.Vector3f;
import utils.ChatStream;

import java.util.Scanner;

import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static utils.Game.THIS;
import static utils.Game.WINDOW;

public class CommandCallback implements Runnable {
    public void run() {
        setupCommands();
        while (true) {
            System.out.print("game -> ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            int code = 0;
            try {
                code = THIS.DISPATCHER.execute(command, new ChatStream("SYSTEM"));
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
            System.out.println(code);
        }
    }
    public static void setupCommands() {
        LiteralArgumentBuilder<ChatStream> set = LiteralArgumentBuilder.<ChatStream>literal("set")
                .then(RequiredArgumentBuilder.<ChatStream, Integer>argument("x", IntegerArgumentType.integer())
                        .then(RequiredArgumentBuilder.<ChatStream, Integer>argument("y", IntegerArgumentType.integer())
                                .then(RequiredArgumentBuilder.<ChatStream, Integer>argument("z", IntegerArgumentType.integer())
                                        .executes(commandContext -> {
                                            ChatStream chatStream = commandContext.getSource();
                                            // Obtener los argumentos
                                            float x = IntegerArgumentType.getInteger(commandContext, "x");
                                            float y = IntegerArgumentType.getInteger(commandContext, "y");
                                            float z = IntegerArgumentType.getInteger(commandContext, "z");

                                            // Crear la posición y añadir el bloque
                                            THIS.WORLD.setBlock(new Block(new Vector3f(x, y, z)));
                                            //THIS.WORLD.addBlock(pos);
                                            chatStream.send("");

                                            return 1; // Retorna 1 para indicar que el comando se ejecutó con éxito
                                        }))));
        THIS.DISPATCHER.register(set);


        LiteralArgumentBuilder<ChatStream> openChat = LiteralArgumentBuilder.<ChatStream>literal("open")
                .then(RequiredArgumentBuilder.<ChatStream, String>argument("screen", string())
                        .executes(commandContext -> {
                            String screen = StringArgumentType.getString(commandContext, "screen");
                            if ("chat".equals(screen)) {
                                THIS.WINDOW.chatScreen.open();
                            }

                            return 1;
                        }));
        THIS.DISPATCHER.register(openChat);

        LiteralArgumentBuilder<ChatStream> setChat = LiteralArgumentBuilder.<ChatStream>literal("close")
                .executes(commandContext -> {
                    WINDOW.close();
                    System.exit(0);
                    return 1;
                })
                .then(RequiredArgumentBuilder.<ChatStream, String>argument("screen", string())
                        .executes(commandContext -> {
                            String screen = StringArgumentType.getString(commandContext, "screen");
                            if ("chat".equals(screen)) {
                                WINDOW.chatScreen.close();
                            }
                            return 1;
                        }));

        THIS.DISPATCHER.register(setChat);

        LiteralArgumentBuilder<ChatStream> chat = LiteralArgumentBuilder.<ChatStream>literal("chat")
                .then(RequiredArgumentBuilder.<ChatStream, String>argument("msg", string())
                .executes(commandContext -> {
                   String msg = StringArgumentType.getString(commandContext, "msg");
                   commandContext.getSource().send(msg);
                   return 1;
                }));

        THIS.DISPATCHER.register(chat);
    }
    public static int setBlock(CommandContext<Object> context) {
        float x = FloatArgumentType.getFloat(context, "x");
        float y = FloatArgumentType.getFloat(context, "y");
        float z = FloatArgumentType.getFloat(context, "z");
        THIS.WORLD.setBlock(new Block(new Vector3f(x,y,z)));
        return 1;
    }
}
