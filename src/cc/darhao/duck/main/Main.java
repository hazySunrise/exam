package cc.darhao.duck.main;

import cc.darhao.duck.chef.*;
import cc.darhao.duck.counter.InventoryCounter;
import cc.darhao.duck.counter.SalesCounter;
import cc.darhao.duck.office.Accounting;
import cc.darhao.duck.office.Boss;
import cc.darhao.duck.office.Buyer;
import cc.darhao.duck.office.Hr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试类
 * <br>
 * <b>2019年8月1日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
public class Main {
   private static List<Chef> chefTeam = new ArrayList<>();

	public static void main(String[] args) {
		createOfficeTeam();
		List<Chef> chefTeam = createChefTeam();
		work(chefTeam);
	}


	private static void work(List<Chef> chefTeam) {
		Random random = new Random();
		System.out.println("系统：烤鸭店开始营业！");
		while(true) {
			if(!chefTeam.get(random.nextInt(chefTeam.size())).cook()) {
				break;
			}
		}
		System.out.println("系统：库存不足，打烊！");
	}

	
	private static void createOfficeTeam() {
		Boss boss = new Boss();
		Accounting accounting = new Accounting();
		Buyer buyer = new Buyer();
        Hr hr = new Hr();
		InventoryCounter.getInstance().addObserver(buyer);
		InventoryCounter.getInstance().addObserver(accounting);
		SalesCounter.getInstance().addObserver(boss);
		SalesCounter.getInstance().addObserver(accounting);
        SalesCounter.getInstance().addObserver(hr);
	}

	
	private static List<Chef> createChefTeam() {
		Chef chineseChef = new ChineseChef();
		Chef japaneseChef = new JapaneseChef();
		Chef frenchChef = new FrenchChef();
		Chef frenchChef2 = new FrenchChef();
		Chef indiaChef = new IndiaChef();
        chefTeam.add(chineseChef);
		chefTeam.add(japaneseChef);
		chefTeam.add(frenchChef);
		chefTeam.add(frenchChef2);
		chefTeam.add(indiaChef);
		return chefTeam;
	}

    public static List<Chef> getChefTeam() {
        return chefTeam;
    }

}
