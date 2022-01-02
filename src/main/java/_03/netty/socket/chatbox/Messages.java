package _03.netty.socket.chatbox;

public class Messages {

  static String of(Object user, String msg) {
    return String.format("%-15s: %s\n", user, msg);
  }

}
