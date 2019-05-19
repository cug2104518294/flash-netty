package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 *
 * 180个指令，180个hanler？那么假设有个请求是第180个handler才能处理的，前面要判断179次。。。
 * 你好，你说的非常对。
 * 指令不是很多的情况下，可以通过netty自带的handler这么处理，
 * 如果指令成百上千，用户自定义映射逻辑比较合适，但是大多数情况下，指令不会太多，可以粗暴一些
 *
 * channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) 的msg是由父类SimpleChannelInboundHandler的channelRead() 方法判断是需要类型后, 强转类型后传递进来的
 I imsg = (I) msg;
 channelRead0(ctx, imsg);
 * */

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
    }
}
