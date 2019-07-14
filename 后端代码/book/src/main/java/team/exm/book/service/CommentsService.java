package team.exm.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.exm.book.entity.Comments;
import team.exm.book.entity.User;
import team.exm.book.mapper.CommentsMapper;
import team.exm.book.mapper.UserMapper;
import team.exm.book.web.response.ResponseEntity;

@Service
public class CommentsService {
    private ResponseEntity re;
    @Autowired
    CommentsMapper cm;
    @Autowired
    UserMapper um;

    public ResponseEntity addComments(Integer user,String comments){
        if(user==null|um.selectByPrimaryKey(user)==null){
            re=new ResponseEntity(0,"当前用户信息无效");
        }else if(comments==null||comments.equals("")){
            re=new ResponseEntity(0,"评论内容无效");
        }else{
            Comments cms=new Comments();
            cms.setsId(new User());
            cms.getsId().setId(user);
            cms.setContent(comments);
            cm.insertSelective(cms);
            re=new ResponseEntity(1,"成功");
        }
        return re;
    }
}
