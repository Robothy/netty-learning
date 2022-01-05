package _06.netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ProtobufClient {
  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

    try{
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(eventExecutors).channel(NioSocketChannel.class)
          .handler(new ProtobufClientInitializer())
          .connect("localhost", 8899)
          .sync()
          .channel()
          .closeFuture()
          .sync();
    } finally {
      eventExecutors.shutdownGracefully();
    }

  }
}
