package priv.wd.Entity;

/**
 * @author ����
 * @date 2019��3��31�� ����1:40:47
 * @description
 */

public class TagEntity {
	private int tag_uuid;
	private int tag_id;
	private String tag_name;
	
	public TagEntity() {};
	
	public int getUUDI() {
		return tag_uuid;
	}
	public void setUUID(int uuid) {
		tag_uuid=uuid;
	}
	public int getID() {
		return tag_id;
	}
	public void set_ID(int id) {
		tag_id=id;
	}
	public String getName() {
		return tag_name;
	}
	public void setName(String name) {
		tag_name=name;
	}
}
