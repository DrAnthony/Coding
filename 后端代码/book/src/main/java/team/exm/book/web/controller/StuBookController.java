package team.exm.book.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exm.book.service.BookService;
import team.exm.book.service.StuBookService;
import team.exm.book.web.request.BookVO;
import team.exm.book.web.request.StuBookVO;
import team.exm.book.web.response.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/stubook")
public class StuBookController {
    private HttpSession session;
    @Autowired
    BookService bs;
    @Autowired
    StuBookService sbs;

    @PostMapping("/borrow")
    public ResponseEntity borrow(@RequestBody BookVO book, HttpServletRequest request){
        session=request.getSession();
        book.setUserID((Integer)session.getAttribute("user"));
        return sbs.borrow(book);
    }

    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestBody StuBookVO sbv){
        return sbs.returnBook(sbv);
    }

}
