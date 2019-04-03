package priv.wd.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import priv.wd.DAO.PicDAO;
import priv.wd.Entity.PicEntity;
import priv.wd.tool.Global;

/**
 * 
 * @author 王迪
 * @date 2019年4月1日
 * @description
 * 文件上传服务实现类
 */
@Service("PicService")
public class PicServiceImpl implements PicService{
	@Resource(name="PicDAO")
	PicDAO pd;

	/**
	 * 将新图片信息保存到数据库
	 */
	@Override
	public void addPic(PicEntity pic) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pic.setDate(sdf.format(date));
		pic.setOwner(Global.getUser().getID());
		pd.addPic(pic);
	}
	
	/**
	 * 通过照片名称获取picEntity
	 */
	@Override
	public PicEntity getPic(String pic_name) {
		pd.getPicInfo("pic_name",pic_name);
		return null;
	}
	
	/**
	 * 将照片保存到本地
	 * D:/image
	 */
	@Override
	public String uploadPic(Part part) throws Exception{
		String str=part.getHeader("content-disposition");
		String name=getFilename(str);
		System.out.println(name+" "+part.getSize()+" "+part.getName());
		File file=new File("D:\\image\\"+name);
		file.createNewFile();
		part.write(file.getPath());
		return name;
	}
	
	/**
	 * 
	 * @Desc
	 * 提取传入字符串中的纯文件名信息
	 * @param name part中的filename信息
	 */
	private String getFilename(String name) {
		String str=null;
		name=name.substring(name.indexOf("filename=\"")+10, name.lastIndexOf('\"'));
		if(name.contains("\\")) {
			str=name.substring(name.lastIndexOf('\\')+1, name.length());
		}
		else
			str=name;
		return str;
	}
	
}
