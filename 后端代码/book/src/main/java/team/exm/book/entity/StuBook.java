package team.exm.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class StuBook {
    private Integer id;

    private User sId;

    private Book bId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rDate;

    private Integer overtime;

    private Boolean isReturned;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getsId() {
        return sId;
    }

    public void setsId(User sId) {
        this.sId = sId;
    }

    public Book getbId() {
        return bId;
    }

    public void setbId(Book bId) {
        this.bId = bId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "StuBook{" +
                "id=" + id +
                ", sId=" + sId +
                ", bId=" + bId +
                ", bDate=" + bDate +
                ", rDate=" + rDate +
                ", overtime=" + overtime +
                ", isReturned=" + isReturned +
                '}';
    }
}