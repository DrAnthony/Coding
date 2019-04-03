package team.web.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import team.web.Entity.UserEntity;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addUser(UserEntity user) {
		getCurrentSession().save(user);
		System.out.println(user.getID()+" "+user.getName()+" "+user.getPWD()+" "+user.getEMail()+" "+user.getGender()+" "+user.getAge());
	}

	@Override
	public UserEntity getUser(String column,String str) {
		List<UserEntity> list = getCurrentSession().createQuery("from UserEntity where "+column+" = '"+str+"'").list();
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
}
