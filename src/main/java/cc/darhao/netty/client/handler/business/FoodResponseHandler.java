package cc.darhao.netty.client.handler.business;

import java.util.Date;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.entity.FoodResponse;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 订餐响应处理器，单例类可被共享
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
@Sharable
public class FoodResponseHandler extends SimpleChannelInboundHandler<FoodResponse> {

	public static final FoodResponseHandler me = new FoodResponseHandler();
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FoodResponse msg) throws Exception {
		System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 对方回答:" + msg.getResult());
	}
	
}
