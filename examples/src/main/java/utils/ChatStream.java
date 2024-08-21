package utils;

import static utils.Game.THIS;

public class ChatStream {
    private String user;
    public ChatStream(String user) {
        this.user = user;
    }
    public void send(String message) {
        THIS.CHAT.out.println(user + ": " + message);
    }
}
