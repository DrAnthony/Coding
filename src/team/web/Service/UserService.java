package team.web.Service;

import org.springframework.transaction.annotation.Transactional;

import team.web.Entity.UserEntity;

@Transactional
public interface UserService {
	public void addUser(String name,String pwd,String email);
	public UserEntity getUser(String column,String str);
}
