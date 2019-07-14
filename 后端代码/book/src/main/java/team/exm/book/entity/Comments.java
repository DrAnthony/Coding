package team.exm.book.entity;

public class Comments {
    private Integer id;

    private User sId;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", sId=" + sId +
                ", content='" + content + '\'' +
                '}';
    }
}