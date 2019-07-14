package team.exm.book.web.request;

import team.exm.book.entity.StuBook;

public class StuBookVO extends StuBook {
    private Integer offset;
    private Integer rows;
    private Integer page;

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
}
