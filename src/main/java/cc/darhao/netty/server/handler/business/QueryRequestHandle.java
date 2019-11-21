package cc.darhao.netty.server.handler.business;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.constant.Constant;
import cc.darhao.netty.comm.entity.FoodResponse;
import cc.darhao.netty.comm.entity.QueryRequest;
import cc.darhao.netty.comm.entity.QueryResponse;
import cc.darhao.netty.server.cache.OrderManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class QueryRequestHandle  extends SimpleChannelInboundHandler<QueryRequest> {

    //public static final QueryRequestHandle me = new QueryRequestHandle();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryRequest msg) throws Exception {
        String loginName = ctx.channel().attr(Constant.LOGIN_NAME).get();
        if(loginName == null) {
            ctx.writeAndFlush(new FoodResponse("请先登录"));
            return;
        }
        System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - " + loginName + "查询了订单情况" );
        int count = 0;
        if (OrderManager.getOrders().size() != 0){
            for (int i = 0; i < OrderManager.getOrders().size(); i++){
                OrderManager.Order order = OrderManager.getOrders().get(i);
                if (order.customer.equals(loginName)){
                    ctx.writeAndFlush(new QueryResponse("前面未做的菜共有："+count+"个"));
                    System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - " +  "前面未做的菜共有"+count+"个" );
                    break;
                }
                count += order.num;
            }
        }else {
            ctx.writeAndFlush(new QueryResponse("目前暂未有未做的菜"));
        }
    }
}
