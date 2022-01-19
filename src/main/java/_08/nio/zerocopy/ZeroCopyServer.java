package _08.nio.zerocopy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ZeroCopyServer {

  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().setReuseAddress(true);
    serverSocketChannel.bind(new InetSocketAddress(8899));

    while (true) {
      SocketChannel socketChannel = serverSocketChannel.accept();
      ByteBuffer buf = ByteBuffer.allocateDirect(4096);
      try {
        while (socketChannel.read(buf) >= 0){
          buf.clear();
        }
      } catch (Exception e) {
        socketChannel.shutdownInput();
        e.printStackTrace();
      }
    }
  }

}
