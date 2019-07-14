package team.exm.book.web.controller;

import com.aliyuncs.RpcAcsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import team.exm.book.entity.StuBook;
import team.exm.book.mapper.BookMapper;
import team.exm.book.service.BookService;
import team.exm.book.service.StuBookService;
import team.exm.book.web.request.BookVO;
import team.exm.book.web.request.StuBookVO;
import team.exm.book.web.response.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/book")
public class BookController {
    private HttpSession session;
    @Autowired
    BookService bs;
    @Autowired
    StuBookService sbs;

    @PostMapping("/add")
    public ResponseEntity addNewBook(@RequestBody BookVO book, HttpServletRequest request) {
        session = request.getSession();
        book.setUserID((Integer) session.getAttribute("user"));
        return bs.addBook(book);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteBook(@RequestBody BookVO book, HttpServletRequest request) {
        session = request.getSession();
        book.setUserID((Integer) session.getAttribute("user"));
        return bs.deleteBook(book);
    }

    @PostMapping("/keyword")
    public ResponseEntity searchByKeywords(@RequestBody BookVO book,HttpServletRequest request){
        session=request.getSession();
        book.setUserID((Integer)session.getAttribute("user"));
        return bs.queryWithKeyWords(book);
    }

    @PostMapping("/selective")
    public ResponseEntity searchSelective(@RequestBody BookVO book,HttpServletRequest request){
        session=request.getSession();
        book.setUserID((Integer)session.getAttribute("user"));
        return bs.querySelective(book);
    }

    @PostMapping("/all")
    public ResponseEntity searchAllBooks(@RequestBody BookVO book,HttpServletRequest request){
        session=request.getSession();
        book.setUserID((Integer)session.getAttribute("user"));
        return bs.queryAll(book);
    }

    @PostMapping("/update")
    public ResponseEntity updateBook(@RequestBody BookVO book){
        return bs.updateSelective(book);
    }

    @PostMapping("/getVerify")
    public ResponseEntity getVerifiedList(@RequestBody BookVO book,HttpServletRequest request){
        session=request.getSession();
        book.setUserID((Integer)session.getAttribute("user"));
        return bs.getVerify(book);
    }

    @PostMapping("/verify")
    public ResponseEntity verify(@RequestParam("id") Integer id){
        return bs.verify(id);
    }
}
