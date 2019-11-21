package cc.darhao.netty.comm.main;

import cc.darhao.netty.client.boot.CustomerBootStrap;
import cc.darhao.netty.server.boot.RestaurantBootStrap;
import cc.darhao.netty.server.handler.CookFoodThread;

import java.text.ParseException;

/**
 * Netty demo - 订餐系统:<br>
 * 1.客户连接服务器后，先登录，然后才可以提交订餐信息<br>
 * 2.服务器需要保存客户的订餐信息，格式为客户名，食物名，份数<br>
 * 3.客户每次发送指令，都会收到服务器的响应<br>
 * 4.指令格式：<br>
 * 登录请求：【login:客户名】，如【login:bobo】;响应：【login:登录结果】，如【login:成功】<br>
 * 订餐请求：【food:食物名,份数】，如【food:培根意面,5】;响应：【food:订餐结果】，如【food:份数不能为负】<br>
 * 每条指令使用#结束，且最大长度为1024个字节
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ParseException {
		//先开启餐厅启动器
		new RestaurantBootStrap().start();
		//然后开启客户启动器
		new CustomerBootStrap().start();
		//开始烹饪
		new CookFoodThread().start();
	}
	
}
