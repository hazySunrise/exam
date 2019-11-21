package cc.darhao.netty.comm.entity;

public class FoodRequest {

	private String name;
	
	private int num;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public FoodRequest(String name, int num) {
		this.name = name;
		this.num = num;
	}
	
}
