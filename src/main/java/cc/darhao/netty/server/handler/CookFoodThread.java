package cc.darhao.netty.server.handler;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.comm.constant.Constant;
import cc.darhao.netty.comm.entity.FinishMessage;
import cc.darhao.netty.server.boot.ChannelBox;
import cc.darhao.netty.server.cache.OrderManager;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.Set;

public class CookFoodThread extends Thread{

    @Override
    public void run() {
        while (true) {
            try {
                if (OrderManager.getOrders().size() != 0){
                    for (OrderManager.Order order:OrderManager.getOrders()){
                        //订餐数量
                        int number = order.num;
                        for (int i=1;i<=order.num;i++){
                           Thread.sleep(5000);
                           order.num--;
                        }
                        //订单完成，餐馆通知顾客一声
                        Set<ChannelHandlerContext> channelSet = ChannelBox.getChannelSet();
                        for (ChannelHandlerContext ctx:channelSet){
                            String loginName = ctx.channel().attr(Constant.LOGIN_NAME).get();
                            if (loginName !=null && loginName.equals(order.customer)){
                                ctx.writeAndFlush(new FinishMessage(number+"份"+order.food+"已完成"));
                                System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - " +  order.customer+"的"+number+"份"+order.food+"已完成" );
                                OrderManager.remove(order);
                                break;
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (Exception e){

            }
        }
    }
}
