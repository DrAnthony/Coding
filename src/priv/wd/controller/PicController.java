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
 * @author ����
 * @date 2019��4��1��
 * @description
 * �ļ��ϴ�������
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
	 * ���ϴ���pic��Ϣ�洢�����ݿ�
	 * �Լ��洢�����ش���
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
			mav.addObject("message", "�ϴ��ɹ�");
			PicEntity pic=new PicEntity();
			pic.setName(name);
			pic.setSize((double)part.getSize());
			pic.setTag(Integer.parseInt(request.getParameter("pic_tag")));
			pic.setDesc(request.getParameter("pic_desc"));
			ps.addPic(pic);
		}
		else {
			mav.addObject("message","�ϴ�ʧ��");
		}
		return mav;
	}
}
