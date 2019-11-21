package cc.darhao.netty.server.boot;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashSet;
import java.util.Set;

public class ChannelBox {

    private static Set<ChannelHandlerContext> channelSet = new HashSet<>();

    public static Set<ChannelHandlerContext> getChannelSet() {
        return channelSet;
    }

    public synchronized static void add(ChannelHandlerContext ctx) {
        channelSet.add(ctx);
    }

    public synchronized static void remove(ChannelHandlerContext ctx) {
        channelSet.remove(ctx);
    }
}
