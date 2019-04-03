package team.web.Entity;

public class EmailEntity {
	private int email_id;
	private String email_add;
	private String email_code;
	
	public EmailEntity() {};
	
	public int getID() {
		return email_id;
	}
	public void setID(int id) {
		email_id=id;
	}
	
	public String getAddr() {
		return email_add;
	}
	public void setAddr(String add) {
		email_add=add;
	}
	
	public String getCode() {
		return email_code;
	}
	public void setCode(String code) {
		email_code=code;
	}
}
