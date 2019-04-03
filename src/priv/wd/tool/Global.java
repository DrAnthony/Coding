package priv.wd.tool;

import priv.wd.Entity.UserEntity;

public class Global {
	private static UserEntity user;
	public static String code=null;
	
	public static void setUser(UserEntity u) {
		user=u;
	}
	public static UserEntity getUser() {
		return user;
	}
}
