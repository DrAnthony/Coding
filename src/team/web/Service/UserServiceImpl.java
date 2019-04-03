package team.web.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import team.web.Entity.UserEntity;
import team.web.dao.UserDAO;

/**
 * 
 * @author 王迪
 * @date 2019年4月2日
 * @description
 */
@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="UserDAO")
	UserDAO user;
	
	/**
	 * 添加新用户
	 */
	@Override
	public void addUser(String name, String pwd, String email) {
		UserEntity ue=new UserEntity();
		ue.setName(name);
		ue.setPWD(pwd);
		ue.setEMail(email);
		user.addUser(ue);
	}

	/**
	 * 通过指定column指定查询内容查询User
	 */
	@Override
	public UserEntity getUser(String column,String str) {
		UserEntity ue=user.getUser(column,str);
		if(ue!=null)
		  System.out.println(ue.getID()+" "+ue.getName()+" "+ue.getPWD()+" "+ue.getEMail());
		return ue;
	}

}
