package priv.wd.DAO;

import org.hibernate.Session;

import priv.wd.Entity.EmailEntity;

public interface EmailDAO {
	public Session getCurrentSession();
	public EmailEntity addEmail(EmailEntity email);
	public EmailEntity getEamil(String column,String value);
	public void deleteEmail(EmailEntity email);
}
