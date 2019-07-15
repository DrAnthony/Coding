package team.exm.book.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.exm.book.service.CommentsService;
import team.exm.book.service.StuBookService;
import team.exm.book.service.UserService;
import team.exm.book.web.request.StuBookVO;
import team.exm.book.web.request.UserVO;
import team.exm.book.web.response.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    private HttpSession session;
    @Autowired
    UserService us;
    @Autowired
    CommentsService cs;
    @Autowired
    StuBookService sbs;
    @GetMapping("/info")
    public ResponseEntity getInfo(HttpServletRequest request) {
        session = request.getSession();
        return us.selectById((Integer) session.getAttribute("user"));
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody UserVO user,HttpServletRequest request) {
        session=request.getSession();
        user.setId((Integer)session.getAttribute("user"));
        return us.delete(user);
    }

    @PostMapping("/updateInfo")
    public ResponseEntity updateInfo(@RequestBody UserVO user, HttpServletRequest request) {
        session=request.getSession();
        user.setId((Integer)session.getAttribute("user"));
        return us.updateUser(user);
    }

    @PostMapping("/updatePwd")
    public ResponseEntity updatePwd(@RequestBody UserVO user, HttpServletRequest request) {
        session=request.getSession();
        user.setId((Integer)session.getAttribute("user"));
        ResponseEntity re = us.updatePwd(user);
        if (re.getCode() == 1) {
            session = request.getSession();
            session.removeAttribute("user");
        }
        return re;
    }

    @PostMapping("/comments")
    public ResponseEntity comments(@RequestParam("comments")String comments,HttpServletRequest request){
        session=request.getSession();
        return cs.addComments((Integer)session.getAttribute("user"),comments);
    }

    @PostMapping("/records")
    public ResponseEntity records(@RequestBody StuBookVO sbv, HttpServletRequest request){
        session=request.getSession();
        return sbs.getRecords((Integer)session.getAttribute("user"),sbv);
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile file,HttpServletRequest request){
        session=request.getSession();
        return us.upload((Integer)session.getAttribute("user"),file);
    }
}
