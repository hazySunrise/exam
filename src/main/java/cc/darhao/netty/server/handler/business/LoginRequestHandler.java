package cc.darhao.netty.server.handler.business;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.constant.Constant;
import cc.darhao.netty.comm.entity.LoginRequest;
import cc.darhao.netty.comm.entity.LoginResponse;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 登录请求处理器，单例类可被共享
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
@Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

	public static final LoginRequestHandler me = new LoginRequestHandler();
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
		String loginName = ctx.channel().attr(Constant.LOGIN_NAME).get();
		if(loginName != null) {
			ctx.writeAndFlush(new LoginResponse("请不要重复登录"));
			return;
		}
		ctx.channel().attr(Constant.LOGIN_NAME).set(msg.getName());

		System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - 欢迎，" + msg.getName());
	}
	
}
