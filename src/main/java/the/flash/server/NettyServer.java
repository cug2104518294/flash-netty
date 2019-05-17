package the.flash.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import the.flash.codec.PacketCodecHandler;
import the.flash.codec.Spliter;
import the.flash.handler.IMIdleStateHandler;
import the.flash.server.handler.AuthHandler;
import the.flash.server.handler.HeartBeatRequestHandler;
import the.flash.server.handler.IMHandler;
import the.flash.server.handler.LoginRequestHandler;

import java.util.Date;

public class NettyServer {

    private static final int PORT = 8000;

    //线程模型、IO 模型、处理逻辑
    public static void main(String[] args) {
        // bossGroup设置成一个线程最好吧，反正只监听一个端口，不指定一个线程的话，其他的线程也是放着没人用浪费了。李林峰也推荐如果只监听一个端口就设置成一个
        // NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 没有必要，设置一个多此一举，nio线程本身就是懒启动，用多少启动多少，没必要专门这么搞
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //引导类
        final ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                //childHandler()用于指定处理新连接数据的读写处理逻辑，handler()用于指定在服务端启动过程中的一些逻辑
                //其实child基本都是对每一个的新的连接设置的属性  而正常就是对服务端整体channel设置的属性值 例如上面的是option属性
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        //空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
