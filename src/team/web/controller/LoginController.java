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
	 * 处理前端注册信息
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
		//mav.addObject("message", "注册成功");
		response.setStatus(200);
	}
	
	/**
	 * 
	 * @Desc
	 * 处理前端登陆信息
	 */
	@RequestMapping("login.do")
	public void login(HttpServletRequest request,HttpServletResponse response) {
		//ModelAndView mav=new ModelAndView("result");
		UserEntity user=userService.getUser("user_name",request.getParameter("user_name"));
		if(user==null) {
			//mav.addObject("message", "用户名不存在");
		}
		else if(!user.getPWD().equals(request.getParameter("user_pwd"))) {
			System.out.println("密码错误");
			//mav.addObject("message", "密码错误");
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
	 * 跳转到注册界面
	 * @param
	 */
	@RequestMapping("login")
	public ModelAndView toLogin() {
		ModelAndView mav=new ModelAndView("login");
		return mav; 
	}
}
