package team.web.Service;

import javax.servlet.http.Part;

import team.web.Entity.PicEntity;

/**
 * 
 * @author ����
 * @date 2019��4��1��
 * @description
 * �ļ�����ӿ�
 */
public interface PicService {
	public String uploadPic(Part part)
	               throws Exception;
	public void addPic(PicEntity pic);
	public PicEntity getPic(String pic_name);
}