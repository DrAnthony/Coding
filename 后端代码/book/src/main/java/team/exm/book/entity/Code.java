package team.exm.book.entity;

public class Code {
    private Integer id;

    private String phone;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}