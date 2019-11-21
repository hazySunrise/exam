package cc.darhao.duck.entity;

public class Duck {

	public void kill() {
		System.out.println("厨师：送鸭子上路");
	}
	
	
	public void wash() {
		System.out.println("厨师：清洗鸭子");
	}
	
	
	public void blanch(int time) {
		System.out.println("厨师：把鸭子焯水" + time + "分钟");
	}
	
	
	public void cut(int piece) {
		System.out.println("厨师：把鸭子切成" + piece + "块");
	}
	
	
	public void pickling(int time) {
		System.out.println("厨师：把鸭子腌制" + time + "分钟");
	}
	
	
	public void roast(int time) {
		System.out.println("厨师：把鸭子放进烤箱" + time + "分钟");
	}
	
	
	public void dishOut() {
		System.out.println("厨师：把鸭子装盘");
	}
	
}
