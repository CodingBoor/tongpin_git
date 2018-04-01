package com.qmx163.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author LiJing
 * @date 2017年6月30日 下午4:28:14
 */
public class NettyClient {

	public static String HOST = "47.94.208.70";
	public static int PORT = 42081;
	public static EventLoopGroup group = null;
	public static Bootstrap bootstrap = getBootstrap();
	public static Channel channel = getChannel(HOST, PORT);

	/**
	 * 初始化Bootstrap
	 * 
	 * @return
	 */
	public static final Bootstrap getBootstrap() {
		group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				//pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				//pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
				pipeline.addLast("idleStateHandler", new IdleStateHandler(20, 20, 10)); // 心跳监测 读超时为10s，写超时为10s 全部空闲时间100s
				pipeline.addLast("handler", new HttpClientInboundHandler());
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public static final Channel getChannel(String host, int port) {
		Channel channel = null;
		try {
			channel = bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
			System.out.println(String.format("连接Server(IP[%s],PORT[%s])失败", host, port));
			e.printStackTrace();
			return null;
		}
		return channel;
	}

	public static void sendMsg(String msg) throws Exception {
		if (channel == null && !channel.isActive()) {
			System.out.println("重新连接....");
			channel = getChannel(HOST, PORT);
		}
		channel.writeAndFlush(msg).sync();
	}
	
	public static void closeClient() {
		if (channel != null && channel.isActive()) {
			channel.close();
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			String readline = sin.readLine(); // 从系统标准输入读入一字符串
			while (!readline.equals("bye")) {
				// 刷新输出流，使Server马上收到该字符串
				System.out.println("Client:" + readline);
				//NettyClient.sendMsg("{\"code\":\""+readline+"\", \"message\":\"退出链接\", \"data\":null}");
				NettyClient.sendMsg(readline);
				if (readline.equals("close")) {
					NettyClient.closeClient();
				}
				// 从Server读入一字符串，并打印到标准输出上
				readline = sin.readLine(); // 从系统标准输入读入一字符串
			}
			System.out.println("---------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
