package priv.wd.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.wd.Entity.EmailEntity;

@Repository("EmailDAO")
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class EmailDAOImpl implements EmailDAO{
	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public EmailEntity addEmail(EmailEntity email) {
		getCurrentSession().save(email);
		return email;
	}

	@Override
	public EmailEntity getEamil(String column,String value) {
		List<EmailEntity> list=getCurrentSession().createQuery("from EmailEntity where "+column+" = '"+value+"'").list();
		if(list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public void deleteEmail(EmailEntity email) {
		//Transaction tx=getCurrentSession().getTransaction();
		getCurrentSession().delete(email);
		//tx.commit();
	}
	
}
