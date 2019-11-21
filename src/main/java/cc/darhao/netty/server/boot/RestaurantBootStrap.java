package cc.darhao.netty.server.boot;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.client.handler.business.ErrorMessageHandler;
import cc.darhao.netty.comm.constant.Constant;
import cc.darhao.netty.server.handler.assist.TypeDecoder;
import cc.darhao.netty.server.handler.assist.TypeEncoder;
import cc.darhao.netty.server.handler.business.ConnectionHandler;
import cc.darhao.netty.server.handler.business.FoodRequestHandler;
import cc.darhao.netty.server.handler.business.LoginRequestHandler;
import cc.darhao.netty.server.handler.business.QueryRequestHandle;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.text.ParseException;
import java.util.Date;

/**
 * 服务端启动器，负责配置服务器参数、指令处理器链，并启动服务器
 * <br>
 * <b>2019年9月27日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class RestaurantBootStrap extends ChannelInitializer<SocketChannel>{

	public void start() throws InterruptedException, ParseException {
		//创建服务端启动器
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		//创建并设定接收连接、指令处理线程组
		serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
		//设定IO模型为非阻塞
		serverBootstrap.channel(NioServerSocketChannel.class);
		//创建并设定服务端指令处理器链
		serverBootstrap.childHandler(this);
		//开启端口监听，阻塞直到开启后返回
		serverBootstrap.bind(Constant.LISTEN_PORT).sync();
		System.out.println("[餐厅的服务器] - " + DateUtil.HHmmssSSS(new Date()) + " - 餐厅开始营业！");
	}

	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//首先需要一个指令字节集整合器，解决粘包拆包问题
		ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Constant.CMD_MAX_BYTES_LENGTH, Unpooled.copiedBuffer(Constant.CMD_END_FLAG.getBytes())));
		//然后需要一个类型解码器，识别指令字节集类型并转换成指令对象
		ch.pipeline().addLast(new TypeDecoder());
		//然后需要一个连接监听器
		ch.pipeline().addLast(ConnectionHandler.me);
		//然后需要一系列指令处理器，这些处理器充当了业务层（包括处理请求与响应），此处处理器的添加顺序可以随意，业务层的处理器是可以单例共享的
		ch.pipeline().addLast(LoginRequestHandler.me);
		ch.pipeline().addLast(FoodRequestHandler.me);
		ch.pipeline().addLast(ErrorMessageHandler.me);
		ch.pipeline().addLast(new QueryRequestHandle());
		//最后需要一个类型编码器，根据不同的对象类型编码成不同的字节集
		//千万注意：Netty的编码器必须位于链首，否则输出时必须用这个方法ctx.channel().write()才会成功，为避免遗漏，请放在链首
		//此处调用addFirst，或者在initChannel最上方调addLast
		ch.pipeline().addFirst(new TypeEncoder());
	}
}
