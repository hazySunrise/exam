package cc.darhao.duck.chef;

import cc.darhao.duck.counter.InventoryCounter;
import cc.darhao.duck.counter.SalesCounter;
import cc.darhao.duck.entity.Duck;

/**
 * 厨师
 * <br>
 * <b>2019年7月31日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public abstract class Chef {

	public boolean cook() {
		if(InventoryCounter.getInstance().getInventory() == 0) {
			return false;
		}
		InventoryCounter.getInstance().reduceInventory();
		Duck duck = new Duck();
		duck.kill();
		duck.wash();
		doWithSecretRecipe(duck);
		duck.dishOut();
		SalesCounter.getInstance().addSales(saleBySecretRecipe());
		return true;
	}
	
	
	/**
	 * 使用秘方步骤加工烤鸭
	 */
	protected abstract void doWithSecretRecipe(Duck duck);
	
	
	/**
	 * 根据不同秘方售出不同的价格
	 */
	protected abstract int saleBySecretRecipe();
	
}
