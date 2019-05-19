package the.flash.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import the.flash.protocol.Packet;
import the.flash.protocol.PacketCodeC;


/**
 * 基于 MessageToByteEncoder，我们可以实现自定义编码，
 * 而不用关心 ByteBuf 的创建，不用每次向对端写 Java 对象都进行一次编码。
 * */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
