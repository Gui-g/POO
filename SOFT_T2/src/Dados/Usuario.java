package Dados;

public class Usuario {
	private String user_name;
	private String password;
	private String name;
	private double credit;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public void increaseCredit(double credit) {
		this.credit = this.credit + credit;
	}
	
	public void payBus(double credit) {
		this.credit = this.credit - credit;
	}
}
