package team.web.dao;

import team.web.Entity.UserEntity;

public interface UserDAO {
	public void addUser(UserEntity user);
	public UserEntity getUser(String column,String str);
}
