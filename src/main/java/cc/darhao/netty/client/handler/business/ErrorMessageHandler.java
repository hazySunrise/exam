package cc.darhao.netty.client.handler.business;

import java.util.Date;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.entity.ErrorMessage;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 异常信息处理器，单例类可被共享
 * <br>
 * <b>2019年9月27日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
@Sharable
public class ErrorMessageHandler extends SimpleChannelInboundHandler<ErrorMessage> {

	public static final ErrorMessageHandler me = new ErrorMessageHandler();
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ErrorMessage msg) throws Exception {
		System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 对方回答:" + msg.getMessage());
	}

}
