package team.web.Service;

import java.awt.image.BufferedImage;

import javax.servlet.http.Part;

import team.web.Entity.PicEntity;

/**
 * 
 * @author 王迪
 * @date 2019年4月1日
 * @description
 * 文件服务接口
 */
public interface PicService {
	public String uploadPic(Part part)
	               throws Exception;
	public void addPic(PicEntity pic);
	public PicEntity getPic(String pic_name);
	public byte[] downPic(String filename);
	public BufferedImage showPic(String name);
} 
