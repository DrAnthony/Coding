package priv.wd.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.wd.DAO.UserDAO;
import priv.wd.Entity.UserEntity;

/**
 * 
 * @author ����
 * @date 2019��4��2��
 * @description
 */
@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="UserDAO")
	UserDAO user;
	
	/**
	 * ������û�
	 */
	@Override
	public void addUser(String name, String pwd, String email, int gender, int age) {
		UserEntity ue=new UserEntity();
		ue.setName(name);
		ue.setPWD(pwd);
		ue.setEMail(email);
		ue.setGender(gender);
		ue.setAge(age);
		user.addUser(ue);
	}

	/**
	 * ͨ��ָ��columnָ����ѯ���ݲ�ѯUser
	 */
	@Override
	public UserEntity getUser(String column,String str) {
		UserEntity ue=user.getUser(column,str);
		if(ue!=null)
		  System.out.println(ue.getID()+" "+ue.getName()+" "+ue.getPWD()+" "+ue.getEMail()+" "+ue.getGender()+" "+ue.getAge());
		return ue;
	}

}
