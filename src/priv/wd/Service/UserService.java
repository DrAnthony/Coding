package priv.wd.Service;

import org.springframework.transaction.annotation.Transactional;

import priv.wd.Entity.UserEntity;

@Transactional
public interface UserService {
	public void addUser(String name,String pwd,String email,int gender,int age);
	public UserEntity getUser(String column,String str);
}
