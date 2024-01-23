package model;

public class User{

	
	private String name;
	private String password;
	private String email;
	private int phonenum;
	private String accountType;
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(int phonenum) {
		this.phonenum = phonenum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getaccountType() {
		return accountType;
	}
	public void setaccountType(String accountType) {
		this.accountType = accountType;
	}
	
}