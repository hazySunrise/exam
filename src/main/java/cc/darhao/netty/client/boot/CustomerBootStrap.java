package cc.darhao.netty.client.boot;

import cc.darhao.dautils.api.DateUtil;
import cc.darhao.netty.client.handler.assist.TypeDecoder;
import cc.darhao.netty.client.handler.assist.TypeEncoder;
import cc.darhao.netty.client.handler.business.*;
import cc.darhao.netty.comm.constant.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * 客户端启动器，负责配置客户端参数、指令处理器链，并连接服务器
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class CustomerBootStrap extends ChannelInitializer<SocketChannel>{

	public void start() throws InterruptedException {
		//创建客户端启动器
		Bootstrap bootstrap = new Bootstrap();
		//创建并设定指令处理线程组
		bootstrap.group(new NioEventLoopGroup());
		//设定IO模型为非阻塞
		bootstrap.channel(NioSocketChannel.class);
		//创建并设定客户端指令处理器链
		bootstrap.handler(this);
		//连接服务器，阻塞直到连接后返回，再获取一个Channel对象
		Channel channel = bootstrap.connect(Constant.SERVER_IP, Constant.SERVER_PORT).sync().channel();
		//创建指令发送线程
		initSender(channel);
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
		ch.pipeline().addLast(LoginResponseHandler.me);
		ch.pipeline().addLast(FoodResponseHandler.me);
		ch.pipeline().addLast(ErrorMessageHandler.me);
		ch.pipeline().addLast(new FinishMessageHandler());
		ch.pipeline().addLast(new QueryResponseHandler());
		//最后需要一个类型编码器，根据不同的对象类型编码成不同的字节集
		//千万注意：Netty的编码器必须位于链首，否则输出时必须用这个方法ctx.channel().write()才会成功，为避免遗漏，请放在链首
		//此处调用addFirst，或者在initChannel最上方调addLast
		ch.pipeline().addFirst(new TypeEncoder());
	}


	@SuppressWarnings("resource")
	private void initSender(Channel channel) {
		new Thread(()->{
			Scanner scanner = new Scanner(System.in);
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignore) {
				}
				//由于配置了编码器， 控制台输入的字符串，会自动经过编码器转成ByteBuf再输出到服务器
				System.out.println("[顾客:" + channel.id() + "] - 请输入指令，无需带#，按回车发送：");
				String cmd = scanner.nextLine();
				channel.writeAndFlush(cmd);
				try {
					System.out.println("[顾客:" + channel.id() + "] - " + DateUtil.HHmmssSSS(new Date()) + " - 发送指令:" + cmd);
				} catch (ParseException ignore) {
				}
			}
		}).start();
	}
	
}
