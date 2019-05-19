package the.flash.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import the.flash.protocol.PacketCodeC;

import java.util.List;

/**
 * 使用 ByteToMessageDecoder，Netty 会自动进行内存的释放，我们不用操心太多的内存管理方面的逻
 *
 * 基于 ByteToMessageDecoder，我们可以实现自定义解码，而不用关心 ByteBuf 的强转和 解码结果的传递。
 *
 * 通过debug发现，接收到请求后在ByteToMessageDecoder类中最终会调用到自定义的PacketDecoder#decode方法，
 * 把bytebuf转换成XXXRequestPacket对象之后放在out容器中，后续会遍历该容器，取出对象，
 * 依次执行SimpleChannelInboundHandler类中的channelRead0(channel, 对象类型)的方法，
 * 该方法是个抽象方法，会自动从子类中寻找对应的子类方法，
 * 就找到了我们的LoginRequestHandler的channelRead0()方法，并把out容器中的该对象的引用传递过来

 * */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        /**
         * 第三个参数是 List类型，我们通过往这个 List 里面添加解码后的结果对象，
         * 就可以自动实现结果往下一个 handler 进行传递，
         * 这样，我们就实现了解码的逻辑 handler。
         * */
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
