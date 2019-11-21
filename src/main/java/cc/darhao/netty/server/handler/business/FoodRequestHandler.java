package cc.darhao.netty.server.handler.business;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.constant.Constant;
import cc.darhao.netty.comm.entity.FoodRequest;
import cc.darhao.netty.comm.entity.FoodResponse;
import cc.darhao.netty.server.cache.OrderManager;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 订餐请求处理器，单例类可被共享
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
@Sharable
public class FoodRequestHandler extends SimpleChannelInboundHandler<FoodRequest> {

	public static final FoodRequestHandler me = new FoodRequestHandler();
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FoodRequest msg) throws Exception {
		String loginName = ctx.channel().attr(Constant.LOGIN_NAME).get();
		if(loginName == null) {
			ctx.writeAndFlush(new FoodResponse("请先登录"));
			return;
		}
		if(msg.getNum() <= 0) {
			ctx.writeAndFlush(new FoodResponse("份数必须大于0"));
			return;
		}
		OrderManager.add(loginName, msg.getName(), msg.getNum());
		ctx.writeAndFlush(new FoodResponse("订餐成功"));
		System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - " + loginName + "订了" + msg.getNum() + "份" + msg.getName());
	}
	
}
