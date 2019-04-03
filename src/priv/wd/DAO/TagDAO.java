package priv.wd.DAO;

import org.springframework.stereotype.Repository;

import priv.wd.Entity.TagEntity;

@Repository("TagEntity")
public interface TagDAO {
	public void addTag(TagEntity tag);
	public TagEntity getTag(int tag_uuid);
}
