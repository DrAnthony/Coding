package team.web.Entity;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author 王迪
 * @date 2019年4月1日
 * @description
 */


public class UserEntity {
	private int user_id;
	private String user_name;
	private String user_pwd;
	private String user_email;
	private int user_gender;
	private int user_age;
	
	public UserEntity() {};
	
	public int getID() {
		return user_id;
	}
	public void setID(int id) {
		user_id=id;
	}
	public String getName() {
		return user_name;
	}
	public void setName(String name) {
		user_name=name;
	}
	public String getPWD() {
		return user_pwd;
	}
	public void setPWD(String pwd) {
		user_pwd=pwd;
	}
	public String getEMail() {
		return user_email;
	}
	public void setEMail(String email) {
		user_email=email;
	}
	public int getGender() {
		return user_gender;
	}
	public void setGender(int gender) {
		user_gender=gender;
	}
	public int getAge() {
		return user_age;
	}
	public void setAge(int age) {
		user_age=age;
	}
}
