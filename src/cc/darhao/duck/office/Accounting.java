package cc.darhao.duck.office;

import java.util.Observable;
import java.util.Observer;

import cc.darhao.duck.counter.InventoryCounter;

/**
 * 会计 - 时刻汇报销售额和库存
 * <br>
 * 因为会计这个对象不需要在其他位置被调用，那就不用单例模式
 * <b>2019年8月1日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class Accounting implements Observer {

	public void update(Observable o, Object arg) {
		if(o instanceof InventoryCounter) {
			System.out.println("当前剩余库存为:" + arg);
		}else {
			System.out.println("当前总销售额为:" + arg);
		}
	}
	
}
