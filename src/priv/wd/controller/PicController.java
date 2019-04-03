package priv.wd.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import priv.wd.Entity.PicEntity;
import priv.wd.Service.PicService;

/**
 * 
 * @author 王迪
 * @date 2019年4月1日
 * @description
 * 文件上传控制器
 */
@Controller
@RequestMapping("pic")
public class PicController {
	@Resource(name="PicService")
	PicService ps;
	
	public PicEntity getPic(int id) {
		return null;
	}
	
	/**
	 * 
	 * @Desc
	 * 将上传的pic信息存储到数据库
	 * 以及存储到本地磁盘
	 * D:/image
	 */
	@RequestMapping("upload")
	public ModelAndView addPic(HttpServletRequest request) throws Exception{
		ModelAndView mav=new ModelAndView("result");
		System.out.println("Start!");
		Part part=request.getPart("file");
		if(part==null)
			System.out.println("part is null!");
		String name=null;
		if(part.getSize()>0) {
			name=ps.uploadPic(part);
		}
		if(name!=null) {
			mav.addObject("message", "上传成功");
			PicEntity pic=new PicEntity();
			pic.setName(name);
			pic.setSize((double)part.getSize());
			pic.setTag(Integer.parseInt(request.getParameter("pic_tag")));
			pic.setDesc(request.getParameter("pic_desc"));
			ps.addPic(pic);
		}
		else {
			mav.addObject("message","上传失败");
		}
		return mav;
	}
}
