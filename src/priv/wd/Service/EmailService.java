package priv.wd.Service;

import priv.wd.Entity.EmailEntity;

public interface EmailService {
	public void sendCode(String eamil);
	public boolean checkCode(EmailEntity email);
	public void deleteEmail(EmailEntity email);
}
