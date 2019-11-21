package cc.darhao.duck.counter;

import java.util.Observable;

/**
 * 销售额计数器 - 这是一个可订阅的主题
 * <br>
 * <b>2019年7月31日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class SalesCounter extends Observable {

	/**
	 * 此处直接在定义处赋值相当于在static块进行赋值
	 */
	private static SalesCounter counter = new SalesCounter();
	
	private int sales = 0;

	
	public static SalesCounter getInstance() {
		return counter;
	}
	
	
	public synchronized void addSales(int sales) {
		this.sales += sales;
		setChanged();
		notifyObservers(this.sales);//通知所有订阅者，销售额已更新
	}
}
