package team.exm.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.PriorityQueue;

public class Book {
    private Integer id;

    private String isbn;

    private BookType type;

    private String name;

    private String author;

    private Press press;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer total;

    private Integer remain;

    private Double price;

    private Boolean verified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Press getPress() {
        return press;
    }

    public void setPress(Press press) {
        this.press = press;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press=" + press +
                ", date=" + date +
                ", total=" + total +
                ", remain=" + remain +
                ", price=" + price +
                ", verified=" + verified +
                '}';
    }
}