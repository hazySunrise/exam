package cc.darhao.duck.counter;

import java.util.Observable;

/**
 * 库存计数器 - 这是一个可订阅的主题
 * <br>
 * <b>2019年7月31日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class InventoryCounter extends Observable {

	/**
	 * 此处直接在定义处赋值相当于在static块进行赋值
	 */
	private static InventoryCounter counter = new InventoryCounter();
	
	private int inventory = 100;

	
	public static InventoryCounter getInstance() {
		return counter;
	}
	
	
	/**
	 * 需要同步，否则会出现脏读
	 */
	public synchronized int getInventory() {
		return inventory;
	}
	
	
	public synchronized void reduceInventory() {
		inventory --;
		setChanged();
		notifyObservers(inventory);//通知所有订阅者，库存已更新
	}

	public synchronized void addInventory(int number){
		if(number!=0) {
			inventory +=  number;
			setChanged();
			notifyObservers(inventory);//通知所有订阅者，库存已更新
		}
	}
}
