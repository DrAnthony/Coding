package team.exm.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import team.exm.book.entity.Book;
import team.exm.book.entity.BookType;
import team.exm.book.entity.StuBook;
import team.exm.book.mapper.*;
import team.exm.book.web.request.BookVO;
import team.exm.book.web.response.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private ResponseEntity re;
    private SimpleDateFormat dFormat;
    @Autowired
    BookMapper bm;
    @Autowired
    UserMapper um;
    @Autowired
    PressMapper pm;
    @Autowired
    BookTypeMapper btm;
    @Autowired
    StuBookMapper sbm;

    private Boolean checkNum(BookVO book) {
        if (book.getTotal() != null) {
            if (book.getTotal() < 0) {
                return false;
            }
        }
        if (book.getRemain() != null) {
            if (book.getRemain() < 0) {
                return false;
            }
        }
        if (book.getTotal() != null && book.getRemain() != null) {
            if (book.getTotal() < book.getRemain()) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkTypeAndPress(BookVO book) {
        if (book.getBookytype() != null) {
            if (btm.selectByPrimaryKey(book.getBookytype()) == null) {
                return false;
            }
        }
        if (book.getBookpress()!= null) {
            if (pm.selectByPrimaryKey(book.getBookpress()) == null) {
                return false;
            }
        }
        return true;
    }

    private BookVO setUserRole(BookVO book) {
        if (book.getUserID() == null) {
            return null;
        } else {
            book.setRole(um.selectByPrimaryKey(book.getUserID()).getRole());
        }
        return book;
    }
    /*
     * @Param mark:1 queryWithKeywords
     *             2 querySelective
     *             3 queryAll
     * */

    private ResponseEntity getList(BookVO book, int mark) {
        if (book.getPage() == null || book.getPage() <= 0 || book.getRows() == null || book.getRows() <= 0) {
            re = new ResponseEntity(0, "您输入的信息有误");
            return re;
        }
        book = setUserRole(book);
        if (book.getRole() == null) {
            re = new ResponseEntity(0, "当前用户无效");
            return re;
        }
        book.setOffset((book.getPage() - 1) * book.getRows());
        List<Book> list;
        int num;
        switch (mark) {
            case 1:
                list = bm.selectByKeywords(book);
                num = bm.selectByKeywordsNum(book);
                break;
            case 2:
                list = bm.selectSeletive(book);
                num = bm.selectByKeywordsNum(book);
                break;
            case 3:
                list = bm.selectAll(book);
                num = bm.selectByKeywordsNum(book);
                break;
            default:
                list = bm.selectVerify(book);
                num = bm.selectVerifyNum(book);
                break;
        }
        if (list.size() == 0) {
            re = new ResponseEntity(0, "未查询到相关书籍");
        } else {
            re = new ResponseEntity(1, String.valueOf(num), list);
        }
        return re;
    }

    public ResponseEntity addBook(BookVO book) {
        book = setUserRole(book);
        if (book == null) {
            re = new ResponseEntity(0, "当前用户无效");
        } else {
            if (book.getRole() == 0) {
                book.setVerified(true);
            }
            if (book.getId() != null) {
                re = new ResponseEntity(0, "您输入的信息不合法");
            } else if (book.getName() == null) {
                re = new ResponseEntity(0, "您输入的书籍不完整");
            } else if (!checkTypeAndPress(book)) {
                re = new ResponseEntity(0, "您输入的出版社信息或图书类型不合法");
            } else if (book.getPrice() != null && (book.getPrice() > 99999 || book.getPrice() < 0)) {
                re = new ResponseEntity(0, "您输入的书籍价格有误");
            } else {
                book.setDate(new Date());
                bm.insertSelective(book);
                re = new ResponseEntity(1, "添加成功");
            }
        }
        return re;
    }

    public ResponseEntity deleteBook(BookVO book) {
        if (book.getId() == null) {
            re = new ResponseEntity(0, "您当前操作的图书无效");
            return re;
        }
        book = setUserRole(book);
        if (book == null) {
            re = new ResponseEntity(0, "当前用户无效");
        } else {
            if (book.getRole() == 1) {
                re = new ResponseEntity(0, "您无权下架该图书");
            } else {
                Book temp = bm.selectByPrimaryKey(book.getId());
                if (temp == null) {
                    re = new ResponseEntity(0, "该图书不存在");
                } else if (temp.getRemain() < temp.getTotal() && !book.isEnsure()) {
                    re = new ResponseEntity(0, "存在违背归还的图书，拒绝下架");
                } else {
                    bm.deleteByPrimaryKey(temp.getId());
                    re = new ResponseEntity(1, "下架成功");
                }
            }
        }
        return re;
    }

    public ResponseEntity queryWithId(BookVO book) {
        if (book.getId() == null) {
            re = new ResponseEntity(0, "您输入的信息无效");
            return re;
        }
        Book temp = bm.selectByPrimaryKey(book.getId());
        if (temp == null) {
            re = new ResponseEntity(0, "未查询到相关书籍");
        } else {
            re = new ResponseEntity(1, "查询成功", temp);
        }
        return re;
    }

    public ResponseEntity queryWithKeyWords(BookVO book) {
        if (book.getKeyword() == "") {
            re = new ResponseEntity(0, "您提供的信息无效");
            return re;
        }
        return getList(book, 1);
    }

    public ResponseEntity querySelective(BookVO book) {
        if ((book.getIsbn() == null || book.getIsbn().equals("")) && (book.getName() == null || book.getName().equals("")) &&
                (book.getAuthor() == null || book.getAuthor().equals("")) && (book.getBookytype() == null)
                && (book.getBookpress() == null)) {
            re = new ResponseEntity(0, "您输入的条件不足");
            return re;
        }
        return getList(book, 2);
    }

    public ResponseEntity queryAll(BookVO book) {
        return getList(book, 3);
    }

    public ResponseEntity updateSelective(BookVO book) {
        if (book.getId() == null) {
            re = new ResponseEntity(0, "您输入的书籍信息有误");
            return re;
        }
        Book temp = bm.selectByPrimaryKey(book.getId());
        if (temp == null) {
            re = new ResponseEntity(0, "您要修改的图书不存在");
        } else {
            if (!checkNum(book)) {
                re = new ResponseEntity(0, "您输入的书籍数量不合法");
            } else if (!checkTypeAndPress(book)) {
                re = new ResponseEntity(0, "书籍出版社或类型不合法");
            } else if (book.getPrice() != null && (book.getPrice() > 99999 || book.getPrice() < 0)) {
                re = new ResponseEntity(0, "您输入的书籍价格有误");
            } else {
                bm.updateByPrimaryKeySelective(book);
                re = new ResponseEntity(1, "修改成功");
            }
        }
        return re;
    }

    public ResponseEntity getVerify(BookVO book) {
        if (book.getUserID() == null | um.selectByPrimaryKey(book.getUserID()) == null) {
            re = new ResponseEntity(0, "当前用户无效");
            return re;
        }
        if (um.selectByPrimaryKey(book.getUserID()).getRole() == 1) {
            re = new ResponseEntity(0, "权限不足");
            return re;
        }
        return getList(book, 4);
    }

    public ResponseEntity verify(Integer id) {
        if (id == null) {
            re = new ResponseEntity(0, "您输入的数据有误");
            return re;
        }
        if (bm.selectByPrimaryKey(id) == null) {
            re = new ResponseEntity(0, "该图书不存在");
            return re;
        }
        if (bm.selectByPrimaryKey(id).getVerified()) {
            re = new ResponseEntity(0, "该书已被审核");
            return re;
        }
        BookVO temp = new BookVO();
        temp.setId(id);
        temp.setDate(new Date());
        temp.setVerified(true);
        bm.updateByPrimaryKeySelective(temp);
        re = new ResponseEntity(1, "审核完成");
        return re;
    }

}
