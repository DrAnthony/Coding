package team.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import team.web.Entity.EmailEntity;
import team.web.Service.EmailService;
import team.web.Service.UserService;
import team.web.tool.Global;

@Controller
public class EmailController {
	@Resource(name="EmailService")
	private EmailService es;
	@Resource(name="UserService")
	private UserService us;
	
	@RequestMapping("sendCode.do")
	public void sendCode(HttpServletRequest request,HttpServletResponse response) {
		//ModelAndView mav=new ModelAndView("test");
		//mav.addObject("action", "checkCode.do");
		//***
		System.out.println("EmailController");
		//***
		System.out.println(request.getParameter("email_add"));
		es.sendCode(request.getParameter("email_add"));
		response.setStatus(200);
	}
	
	@RequestMapping("checkCode.do")
	public ResponseEntity<String> checkCode(HttpServletRequest request) {
		//ModelAndView mav=new ModelAndView("result");
		//mav.addObject("message", "Ê§°Ü");
		ResponseEntity<String> re=null;
		HttpHeaders headers=new HttpHeaders();
		
		EmailEntity email=new EmailEntity();
		String add=request.getParameter("email_add");
		String code=request.getParameter("email_code");
		String user_name=request.getParameter("user_name");
		String user_pwd=request.getParameter("user_pwd");
		email.setAddr(add);email.setCode(code);
		System.out.println(add+" "+code+" "+Global.code);
		headers.add("Access-Control-Expose-Headers","Content-Result");
		if(es.checkCode(email)) {
			headers.add("Content-Result", "true");
			us.addUser(user_name, user_pwd, add);
		}
		else {
			headers.add("Content-Result", "false");
		}
		System.out.println(headers.get("Content-Result"));
		re=new ResponseEntity<String>("result",headers,HttpStatus.OK);
		return re;
	}
}
