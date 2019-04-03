package team.web.tool;

import team.web.Entity.UserEntity;

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
