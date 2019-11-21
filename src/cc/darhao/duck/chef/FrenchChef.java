package cc.darhao.duck.chef;

import cc.darhao.duck.entity.Duck;

/**
 * 中式厨师
 * <br>
 * <b>2019年7月31日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class FrenchChef extends Chef{

	@Override
	protected void doWithSecretRecipe(Duck duck) {
		System.out.println("开始烹饪法国料理");
		duck.blanch(5);
		duck.cut(40);
		duck.pickling(15);
		duck.roast(10);
		
	}

	@Override
	protected int saleBySecretRecipe() {
		return 20;
	}
	
}
