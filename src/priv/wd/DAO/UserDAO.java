package priv.wd.DAO;

import priv.wd.Entity.UserEntity;

public interface UserDAO {
	public void addUser(UserEntity user);
	public UserEntity getUser(String column,String str);
}
