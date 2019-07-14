package com.example.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.nio.cs.US_ASCII;
import team.exm.book.BookApplication;
import team.exm.book.service.UserService;
import team.exm.book.web.request.UserVO;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookApplication.class)
public class BookApplicationTests {
    @Autowired
    UserService us;

    @Test
    public void contextLoads() {
        Date date=new Date();
        System.out.println(new Timestamp(date.getTime()));
    }

}
