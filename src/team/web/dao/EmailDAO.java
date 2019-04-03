package team.web.dao;

import org.hibernate.Session;

import team.web.Entity.EmailEntity;

public interface EmailDAO {
	public Session getCurrentSession();
	public EmailEntity addEmail(EmailEntity email);
	public EmailEntity getEamil(String column,String value);
	public void deleteEmail(EmailEntity email);
}
