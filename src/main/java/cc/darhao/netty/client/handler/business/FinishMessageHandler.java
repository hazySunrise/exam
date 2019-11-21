package cc.darhao.netty.client.handler.business;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.entity.FinishMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class FinishMessageHandler extends SimpleChannelInboundHandler<FinishMessage> {

    //public static final FinishMessageHandler me = new FinishMessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FinishMessage msg) throws Exception {
        System.out.println("[顾客:" + ctx.channel().id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " 餐馆提醒:" + msg.getMessage());
    }
}
