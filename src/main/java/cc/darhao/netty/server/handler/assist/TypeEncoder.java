package cc.darhao.netty.server.handler.assist;

import cc.darhao.netty.comm.constant.Constant;
import cc.darhao.netty.comm.entity.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 类型编码器，负责把指令对象按照协议类型编码成指令字节集
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class TypeEncoder extends MessageToByteEncoder {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		if(msg instanceof ErrorMessage) {
			out.writeBytes(("Error:"+((ErrorMessage) msg).getMessage()).getBytes());
		}else if(msg instanceof FoodResponse) {
			out.writeBytes(("Food:"+((FoodResponse) msg).getResult()).getBytes());
		}else if(msg instanceof LoginResponse) {
			out.writeBytes(("Login:"+((LoginResponse) msg).getResult()).getBytes());
		}else if (msg instanceof QueryResponse){
			out.writeBytes(("Query:"+((QueryResponse) msg).getResult()).getBytes());
		}else if (msg instanceof FinishMessage){
			out.writeBytes(("Finish:"+((FinishMessage) msg).getMessage()).getBytes());
		}
		//别忘了编码的时候也按照协议来加上结束符
		out.writeBytes(Constant.CMD_END_FLAG.getBytes());
	}

}
