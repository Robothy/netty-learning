package _03.netty.socket.chatbox;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

  private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.writeAndFlush(Messages.of(channel.remoteAddress(), msg), ch -> ch != channel);
    channel.writeAndFlush(Messages.of("You", msg));
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.writeAndFlush(Messages.of("Server", channel.remoteAddress() + " Connected."));
    channelGroup.add(channel);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    Channel ch = ctx.channel();
    ch.writeAndFlush(Messages.of("Server", ch.remoteAddress() + " Disconnected."));
    // Netty automatically remove the disconnected client. Below code is not required.
    // channelGroup.remove(ch);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Channel ch = ctx.channel();
    System.out.println(Messages.of(ch.remoteAddress(), "Online."));
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    Channel ch = ctx.channel();
    System.out.println(Messages.of(ch.remoteAddress(), "Offline."));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    //cause.printStackTrace();
    ctx.close();
  }
}
