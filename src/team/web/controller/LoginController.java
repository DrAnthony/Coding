package team.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import team.web.Entity.UserEntity;
import team.web.Service.UserService;
import team.web.tool.Global;

/**
 * @author ����
 * @date 2019��3��30�� ����11:02:08
 * @description
 * �����½�Լ�ע��ҳ������
 */
@Controller
public class LoginController {
	@Resource(name="UserService")
	private UserService userService;
	
	@RequestMapping("test")
	public ResponseEntity<String> test(HttpServletRequest request,HttpServletResponse reponse) {
		//System.out.println("Here!");
		//ModelAndView mav=new ModelAndView("test");
		//mav.addObject("action", "sendCode.do");
		//userService.getUser(null, null);
		System.out.println("I'am here");
		HttpHeaders headers=new HttpHeaders();
		headers.add("text","Hello world!");;
		ResponseEntity<String> re=new ResponseEntity<String>("Hello world!",headers,HttpStatus.OK);
		return re;
	}
	
	/**
	 * 
	 * @Desc
	 * ����ǰ��ע����Ϣ
	 */
	@RequestMapping("register.do")
	public void register(HttpServletRequest request,HttpServletResponse response) {
		/*
		 * UserEntity user=new UserEntity();
		 * user.setName(request.getParameter("user_name"));
		 * user.setPWD(request.getParameter("user_pwd"));
		 * user.setEMail(request.getParameter("user_email"));
		 * user.setGender(Integer.parseInt(request.getParameter("user_gender"));
		 * user.setAge(Integer.parseInt(request.getParameter("user_age")));
		 */
		userService.addUser(request.getParameter("user_name"),
				            request.getParameter("user_pwd"),
				            request.getParameter("user_email")
				            );
		//ModelAndView mav=new ModelAndView("result");
		//mav.addObject("message", "ע��ɹ�");
		response.setStatus(200);
	}
	
	/**
	 * 
	 * @Desc
	 * ����ǰ�˵�½��Ϣ
	 */
	@RequestMapping("login.do")
	public void login(HttpServletRequest request,HttpServletResponse response) {
		//ModelAndView mav=new ModelAndView("result");
		UserEntity user=userService.getUser("user_name",request.getParameter("user_name"));
		if(user==null) {
			//mav.addObject("message", "�û���������");
		}
		else if(!user.getPWD().equals(request.getParameter("user_pwd"))) {
			System.out.println("�������");
			//mav.addObject("message", "�������");
		}
		else {
			//mav=new ModelAndView("upload");
			Global.setUser(user);
		}
		response.setStatus(200);
	}
	
	/**
	 * 
	 * @Desc
	 * ��ת��ע�����
	 * @param
	 */
	@RequestMapping("login")
	public ModelAndView toLogin() {
		ModelAndView mav=new ModelAndView("login");
		return mav; 
	}
}
