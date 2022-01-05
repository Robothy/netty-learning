package _06.netty.protobuf;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufServiceHandler extends SimpleChannelInboundHandler<DataInfo.Member> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Member msg) throws Exception {
    System.out.println("Fuck");
    System.out.println(msg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
