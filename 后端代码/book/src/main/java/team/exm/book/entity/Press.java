package team.exm.book.entity;

public class Press {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Press{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}