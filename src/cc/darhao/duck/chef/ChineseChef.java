package cc.darhao.duck.chef;

import cc.darhao.duck.entity.Duck;

/**
 * 中式厨师
 * <br>
 * <b>2019年7月31日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class ChineseChef extends Chef{

	@Override
	protected void doWithSecretRecipe(Duck duck) {
		System.out.println("开始烹饪中华料理");
		duck.blanch(15);
		duck.pickling(50);
		duck.roast(20);
		duck.cut(20);
	}

	@Override
	protected int saleBySecretRecipe() {
		return 30;
	}
	
}
