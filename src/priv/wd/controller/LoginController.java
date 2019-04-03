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
 * @author 王迪
 * @date 2019年3月30日 下午11:02:08
 * @description
 * 处理登陆以及注册页面请求
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
	 * 处理前端注册信息
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
		mav.addObject("message", "注册成功");
		return mav;
	}
	
	/**
	 * 
	 * @Desc
	 * 处理前端登陆信息
	 */
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("result");
		UserEntity user=userService.getUser("user_name",request.getParameter("user_name"));
		if(user==null) {
			mav.addObject("message", "用户名不存在");
		}
		else if(!user.getPWD().equals(request.getParameter("user_pwd"))) {
			System.out.println("密码错误");
			mav.addObject("message", "密码错误");
		}
		else {
			mav=new ModelAndView("upload");
			Global.setUser(user);
		}
		return mav;
	}
}
