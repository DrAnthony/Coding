package priv.wd.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import priv.wd.Entity.EmailEntity;
import priv.wd.Service.EmailService;

@Controller
public class EmailController {
	@Resource(name="EmailService")
	private EmailService es;
	
	@RequestMapping("sendCode.do")
	public ModelAndView sendCode(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("test");
		mav.addObject("action", "checkCode.do");
		//***
		System.out.println("EmailController");
		//***
		System.out.println(request.getParameter("email_add"));
		es.sendCode(request.getParameter("email_add"));
		return mav;
	}
	
	@RequestMapping("checkCode.do")
	public ModelAndView checkCode(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("result");
		mav.addObject("message", "Ê§°Ü");
		EmailEntity email=new EmailEntity();
		String add=request.getParameter("email_add");
		String code=request.getParameter("email_code");
		email.setAddr(add);email.setCode(code);
		if(es.checkCode(email)) {
			mav.addObject("message", "³É¹¦");
		}
		return mav;
	}
}
