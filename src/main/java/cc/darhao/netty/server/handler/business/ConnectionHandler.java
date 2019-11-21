package cc.darhao.netty.server.handler.business;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.server.boot.ChannelBox;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

@Sharable
public class ConnectionHandler extends ChannelInboundHandlerAdapter {

	public static final ConnectionHandler me = new ConnectionHandler();

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ChannelBox.add(ctx);
		System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - 监听到一个连接");
	}
	
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ChannelBox.remove(ctx);
		System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - 一个连接已断开");
	}
	
}
