package cc.darhao.netty.client.handler.assist;

import cc.darhao.netty.comm.entity.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 类型解码器，负责把所有来自服务端的字节集按照协议类型解码成指令对象
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class TypeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		//分割并读取字节集
		String[] cmdAttrs = in.toString(Charset.forName("UTF-8")).split(":");
		//跳过已读部分，否则下次decode进来的buf会追加本次的buf
		in.skipBytes(in.readableBytes());
		if(cmdAttrs.length != 2) {
			ctx.writeAndFlush(new ErrorMessage("指令格式有误"));
			return;
		}
		switch (cmdAttrs[0]) {
		case "Login":
			out.add(new LoginResponse(cmdAttrs[1]));
			break;
		case "Food":
			out.add(new FoodResponse(cmdAttrs[1]));
			break;
		case "Error":
			out.add(new ErrorMessage(cmdAttrs[1]));
			break;
		case "Finish":
			out.add(new FinishMessage(cmdAttrs[1]));
			break;
		case "Query":
			out.add(new QueryResponse(cmdAttrs[1]));
			break;
		default:
			ctx.writeAndFlush(new ErrorMessage("非法指令类型"));
			break;
		}
	}
}
