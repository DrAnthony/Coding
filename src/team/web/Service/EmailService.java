package team.web.Service;

import team.web.Entity.EmailEntity;

public interface EmailService {
	public void sendCode(String eamil);
	public boolean checkCode(EmailEntity email);
	public void deleteEmail(EmailEntity email);
}
