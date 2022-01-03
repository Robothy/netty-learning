package _04.netty.socket.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    Channel channel = ctx.channel();

    if (evt instanceof IdleStateEvent) {
      IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
      String eventType = switch (idleStateEvent.state()) {
        case READER_IDLE -> "Read Idle";
        case WRITER_IDLE -> "Write Idle";
        case ALL_IDLE -> "Read/Write Idle";
      };

      System.out.println(channel.remoteAddress() + ": " + eventType);
      channel.close();
    }
  }
}
