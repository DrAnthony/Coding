package team.web.Entity;

import java.util.Date;

/**
 * @author 王迪
 * @date 2019年3月31日 下午1:40:47
 * @description
 */

public class PicEntity {
	private int pic_id;
	private String pic_name;
	private int pic_owner; 
	private double pic_size;
	private String pic_date;
	private int pic_tag;
	private String pic_desc;
	
	public PicEntity() {};
	
	public int getID() {
		return pic_id;
	}
	public void setID(int id) {
		pic_id=id;
	}
	public String getName() {
		return pic_name;
	}
	public void setName(String name) {
		pic_name=name;
	}
	public int getOwner() {
		return pic_owner;
	}
	public void setOwner(int owner) {
		pic_owner=owner;
	}
	public double getSize() {
		return pic_size;
	}
	public void setSize(double size) {
		pic_size=size;
	}
	public String getDate() {
		return pic_date;
	}
	public void setDate(String date) {
		pic_date=date;
	}
	public int getTag() {
		return pic_tag;
	}
	public void setTag(int tag) {
		pic_tag=tag;
	}
	public String getDesc() {
		return pic_desc;
	}
	public void setDesc(String desc) {
		pic_desc=desc;
	}
}
