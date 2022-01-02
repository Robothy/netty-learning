package _03.netty.socket.chatbox;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyChatClient {

  public static void main(String[] args) throws InterruptedException, IOException {
    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      ChannelFuture channelFuture = bootstrap.group(eventExecutors)
          .channel(NioSocketChannel.class)
          .handler(new MyChatClientInitializer())
          .connect("localhost", 8899)
          .sync();

      Channel channel = channelFuture.channel();

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      for (;;) {
        channel.writeAndFlush(reader.readLine() + "\r\n");
      }
    } finally {
      eventExecutors.shutdownGracefully();
    }
  }

}
