package team.exm.book.web.request;

import team.exm.book.entity.Book;

public class BookVO extends Book {
    private String keyword;
    private Integer offset;
    private Integer rows;
    private Integer page;
    private Integer userID;
    private Byte role;
    private boolean ensure;

    private Integer bookytype;
    private Integer bookpress;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public boolean isEnsure() {
        return ensure;
    }

    public void setEnsure(boolean ensure) {
        this.ensure = ensure;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getBookytype() {
        return bookytype;
    }

    public void setBookytype(Integer bookytype) {
        this.bookytype = bookytype;
    }

    public Integer getBookpress() {
        return bookpress;
    }

    public void setBookpress(Integer bookpress) {
        this.bookpress = bookpress;
    }


}
