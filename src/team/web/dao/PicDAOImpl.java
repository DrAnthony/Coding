package team.web.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import team.web.Entity.PicEntity;

@Repository("PicDAO")
public class PicDAOImpl implements PicDAO{
	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public void addPic(PicEntity pic) {
		getCurrentSession().save(pic);
	}

	@Override
	public PicEntity getPicInfo(String column,String value) {
		Query<PicEntity> query=getCurrentSession().createQuery("from PicEntity where "+column+" = '"+value+"'");
		List<PicEntity> list=query.list();
		if(list.isEmpty())
			return null;
		return list.get(0);
	}
}
