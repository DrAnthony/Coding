package priv.wd.DAO;

import org.springframework.stereotype.Repository;

import priv.wd.Entity.PicEntity;

@Repository("PicDAO")
public interface PicDAO {
	public void addPic(PicEntity pic);
	public PicEntity getPicInfo(String column,String value);
}
