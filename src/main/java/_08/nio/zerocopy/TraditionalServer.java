package _08.nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TraditionalServer {

  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(8899);
    while (true) {
      Socket socket = serverSocket.accept();
      DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
      byte[] buf = new byte[4096];
      while (-1 != dataInputStream.read(buf));
    }
  }
}
