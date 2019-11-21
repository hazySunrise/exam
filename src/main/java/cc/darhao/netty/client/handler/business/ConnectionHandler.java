package cc.darhao.netty.client.handler.business;

import java.util.Date;

import cc.darhao.dautils.api.DateUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class ConnectionHandler extends ChannelInboundHandlerAdapter {

	public static final ConnectionHandler me = new ConnectionHandler();
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 连接服务器成功");
	}
	
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 与服务器断开连接");
	}
	
}
