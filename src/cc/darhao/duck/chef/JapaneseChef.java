package cc.darhao.duck.chef;

import cc.darhao.duck.entity.Duck;

/**
 * 中式厨师
 * <br>
 * <b>2019年7月31日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class JapaneseChef extends Chef{

	@Override
	protected void doWithSecretRecipe(Duck duck) {
		System.out.println("开始烹饪日本料理");
		duck.blanch(10);
		duck.roast(25);
		duck.cut(35);
		duck.pickling(15);
	}

	@Override
	protected int saleBySecretRecipe() {
		return 25;
	}
	
}
