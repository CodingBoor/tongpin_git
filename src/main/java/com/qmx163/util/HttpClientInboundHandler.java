package com.qmx163.util;


import org.json.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description:
 * @author LiJing
 * @date 2017年6月30日 下午4:29:30
 */
public class HttpClientInboundHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// messageReceived方法,名称很别扭，像是一个内部方法.
		System.out.println("client接收到服务器返回的消息:" + msg);
		JSONObject json=new JSONObject(String.valueOf(msg));
		String code = json.getString("code");
		if (isBlank(code)) {
			switch (code) {
			case "LOGIN":
				ctx.channel().writeAndFlush("{\"code\":\"LOGIN\", \"message\":\"链接成功\", \"data\":{\"lessonPeriodsId\": \"840334c85b2c11e7905400163e323696\"}}");
				break;
			case "HEARBEAT":
				
				break;

			default:
				break;
			}
		}
	}
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				System.out.println("READER_IDLE");
				// 超时关闭channel
				//ctx.close();
			} else if (event.state().equals(IdleState.WRITER_IDLE)) {
				System.out.println("WRITER_IDLE");
				// 超时关闭channel
				//ctx.close();
			} else if (event.state().equals(IdleState.ALL_IDLE)) {
				System.out.println("ALL_IDLE");
				// 发送心跳
				//ctx.channel().writeAndFlush("{\"code\":\"HEARBEAT\", \"message\":\"链接成功\", \"data\":{\"type\":\"PING\"}}");
			}
		}
		super.userEventTriggered(ctx, evt);
	}

}
