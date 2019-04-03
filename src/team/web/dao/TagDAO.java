package team.web.dao;

import org.springframework.stereotype.Repository;

import team.web.Entity.TagEntity;

@Repository("TagEntity")
public interface TagDAO {
	public void addTag(TagEntity tag);
	public TagEntity getTag(int tag_uuid);
}
