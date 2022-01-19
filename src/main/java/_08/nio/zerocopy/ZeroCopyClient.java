package _08.nio.zerocopy;

import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ZeroCopyClient {

  public static void main(String[] args) throws Exception {
    String filename = "build.gradle";

    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.connect(new InetSocketAddress("localhost", 8899));

    FileChannel fileChannel = FileChannel.open(Path.of(filename), StandardOpenOption.READ);
    long start = System.currentTimeMillis();
    fileChannel.transferTo(0, fileChannel.size(), socketChannel);
    long end = System.currentTimeMillis();
    System.out.println(String.format("Transfered %d bytes, cost %d ms.", fileChannel.size(), end - start));
    fileChannel.close();
    socketChannel.close();
  }

}
