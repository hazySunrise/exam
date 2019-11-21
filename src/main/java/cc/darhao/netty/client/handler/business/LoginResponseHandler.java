package cc.darhao.netty.client.handler.business;

import java.util.Date;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.entity.LoginResponse;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录响应处理器，单例类可被共享
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
@Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

	public static final LoginResponseHandler me = new LoginResponseHandler();
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
		System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 对方回答:" + msg.getResult());
	}
	
}
