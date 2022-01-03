package _05.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.time.LocalDateTime;

public class TextWsFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
    System.out.println("Received: " + msg.text());
    TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame("Server timestamp: " + LocalDateTime.now());
    ctx.channel().writeAndFlush(textWebSocketFrame);
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Handler added: " + ctx.channel().id().asLongText());

  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Handler removed: " + ctx.channel().id().asLongText());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("Exception occurred.");
    ctx.close();
  }
}
