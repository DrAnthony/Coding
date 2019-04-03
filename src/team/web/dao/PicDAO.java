package team.web.dao;

import org.springframework.stereotype.Repository;

import team.web.Entity.PicEntity;

@Repository("PicDAO")
public interface PicDAO {
	public void addPic(PicEntity pic);
	public PicEntity getPicInfo(String column,String value);
}
