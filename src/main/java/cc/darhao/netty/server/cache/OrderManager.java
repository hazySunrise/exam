package cc.darhao.netty.server.cache;

import java.util.LinkedList;
import java.util.List;

/**
 * 订单管理器
 * <br>
 * <b>2019年9月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class OrderManager {

	public static class Order {
		public String customer;
		public String food;
		public int num;
		public Order(String customer, String food, int num) {
			this.customer = customer;
			this.food = food;
			this.num = num;
		}

	}
	
	private static List<Order> orders = new LinkedList<>();

	public static synchronized List<Order> getOrders() {
		return orders;
	}

	public static synchronized void add(String customer, String food, int num) {
		orders.add(new Order(customer, food, num));
	}

	public static synchronized void remove(Order order){
		orders.remove(order);
	}
}
