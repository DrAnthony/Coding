package team.exm.book.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exm.book.entity.User;
import team.exm.book.service.CodeService;
import team.exm.book.service.UserService;
import team.exm.book.web.request.UserVO;
import team.exm.book.web.response.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private Logger log = LoggerFactory.getLogger(RegisterController.class);
    private ResponseEntity re;
    private HttpSession session;
    @Autowired
    UserService us;
    @Autowired
    CodeService cs;

    @PostMapping("/code.do")
    public ResponseEntity sendCode(@RequestBody UserVO user) {
        return cs.addCode(user);
    }

    @PostMapping("/register.do")
    public ResponseEntity register(@RequestBody UserVO user, HttpServletRequest request) {
        log.info("user id registering....");
        log.info("user info:" + user);
        re = us.register(user);
        if (re.getCode() == 1) {
            session = request.getSession();
            session.setAttribute("user", ((User) re.getData()).getId());
            re=us.updateTime(re);
        }
        return re;
    }
}
