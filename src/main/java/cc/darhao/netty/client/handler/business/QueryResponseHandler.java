package cc.darhao.netty.client.handler.business;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.entity.QueryResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class QueryResponseHandler extends SimpleChannelInboundHandler<QueryResponse> {

   // public static final QueryResponseHandler me = new QueryResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryResponse msg) throws Exception {
        System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 对方回答:" + msg.getResult());
    }
}
