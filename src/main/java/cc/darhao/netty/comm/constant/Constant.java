package cc.darhao.netty.comm.constant;

import io.netty.util.AttributeKey;

public class Constant {
	public final static AttributeKey<String> LOGIN_NAME = AttributeKey.newInstance("LOGIN_NAME");
	
	public final static String SERVER_IP = "127.0.0.1";
	
	public final static int SERVER_PORT = 666;
	
	public final static int LISTEN_PORT = 666;
	
	public final static int CMD_MAX_BYTES_LENGTH = 1024;
	
	public final static String CMD_END_FLAG = "#";
}
