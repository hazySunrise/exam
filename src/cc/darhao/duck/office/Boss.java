package cc.darhao.duck.office;

import java.util.Observable;
import java.util.Observer;

/**
 * 老板 - 销售额达到一个新的层次要鼓舞员工，对销售额感兴趣
 * <br>
 * 因为老板这个对象不需要在其他位置被调用，那就不用单例模式
 * <b>2019年8月1日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class Boss implements Observer {

	private int newLevel = 100;
	
	public void update(Observable o, Object sales) {
		if((Integer) sales > newLevel) {
			System.out.println("老板：销售额已经突破" + newLevel + "，大家继续加油！");
			newLevel += 100;
		}
	}
	
}
