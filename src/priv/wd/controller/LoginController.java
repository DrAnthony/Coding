package priv.wd.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import priv.wd.Entity.UserEntity;
import priv.wd.Service.UserService;
import priv.wd.tool.Global;

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
	public ModelAndView test(HttpServletRequest request,HttpServletResponse reponse) {
		//System.out.println("Here!");
		ModelAndView mav=new ModelAndView("test");
		mav.addObject("action", "sendCode.do");
		//userService.getUser(null, null);
		return mav;
	}
	
	/**
	 * 
	 * @Desc
	 * ����ǰ��ע����Ϣ
	 */
	@RequestMapping("register.do")
	public ModelAndView register(HttpServletRequest request) {
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
				            request.getParameter("user_email"), 
				            Integer.parseInt(request.getParameter("user_gender")),
				            Integer.parseInt(request.getParameter("user_age")));
		ModelAndView mav=new ModelAndView("result");
		mav.addObject("message", "ע��ɹ�");
		return mav;
	}
	
	/**
	 * 
	 * @Desc
	 * ����ǰ�˵�½��Ϣ
	 */
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("result");
		UserEntity user=userService.getUser("user_name",request.getParameter("user_name"));
		if(user==null) {
			mav.addObject("message", "�û���������");
		}
		else if(!user.getPWD().equals(request.getParameter("user_pwd"))) {
			System.out.println("�������");
			mav.addObject("message", "�������");
		}
		else {
			mav=new ModelAndView("upload");
			Global.setUser(user);
		}
		return mav;
	}
}
