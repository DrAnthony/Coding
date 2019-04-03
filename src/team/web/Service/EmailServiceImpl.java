package team.web.Service;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.web.Entity.EmailEntity;
import team.web.dao.EmailDAO;
import team.web.tool.Global;
import team.web.tool.ToolTimer;

@Service("EmailService")
@Transactional
public class EmailServiceImpl implements EmailService{
	@Resource(name="EmailDAO")
	private EmailDAO ed;
	@Resource(name="mailSender")
	private JavaMailSender sender;
	private ToolTimer timer;
	public void sendCode(String email) {
		//***
		System.out.println("EmailService");
		//***
		System.out.println(email);
		int c=(int)(Math.random()*10000);
      int temp=c;
      for(int i=0;i<4;i++){
          if((temp/=10)==0)
             c*=10;
      }
		String code=String.valueOf(c);
		//***
		System.out.println(code);
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom("w826581298@163.com");
		smm.setTo(email);
		smm.setSubject("验证码");
		smm.setText("验证码有效期2分钟："+code);
		//***
		System.out.println("Over");
		sender.send(smm);
		Global.code=code;
		timer=new ToolTimer();
		timer.start();
	}

	@Override
	public boolean checkCode(EmailEntity email) {
		if(timer.isAlive()) {
			timer.stop=true;
			timer.canDelete=false;
		}
		if(Global.code==null) {
			System.out.println("Email is null!");
		}
		else if(email.getCode().equals(Global.code)) {
			ed.addEmail(email);
			return true;
		}
		Global.code=null;
		return false;
	}

	@Override
	public void deleteEmail(EmailEntity email) {
		ed.deleteEmail(email);
	}

}
