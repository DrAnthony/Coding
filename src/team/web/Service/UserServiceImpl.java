package team.web.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import team.web.Entity.UserEntity;
import team.web.dao.UserDAO;

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
	public void addUser(String name, String pwd, String email) {
		UserEntity ue=new UserEntity();
		ue.setName(name);
		ue.setPWD(pwd);
		ue.setEMail(email);
		user.addUser(ue);
	}

	/**
	 * ͨ��ָ��columnָ����ѯ���ݲ�ѯUser
	 */
	@Override
	public UserEntity getUser(String column,String str) {
		UserEntity ue=user.getUser(column,str);
		if(ue!=null)
		  System.out.println(ue.getID()+" "+ue.getName()+" "+ue.getPWD()+" "+ue.getEMail());
		return ue;
	}

}
