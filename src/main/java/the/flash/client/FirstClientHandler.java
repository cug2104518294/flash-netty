package the.flash.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 继承自 ChannelInboundHandlerAdapter，然后覆盖了 channelActive()方法，这个方法会在客户端连接建立成功之后被调用
    * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");
        // 1.获取数据
        ByteBuf buffer = getByteBuf(ctx);
        // 2.写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，闪电侠!".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }

    //channelRead() 方法读取数据后，需要调用ByteBuf的release()方法吗
    //要调用的，这小节不涉及内存相关的知识，没放到这里讲
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
