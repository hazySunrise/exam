package cc.darhao.netty.comm.entity;

public class LoginRequest {

	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LoginRequest(String name) {
		this.name = name;
	}
	
}
