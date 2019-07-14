package team.exm.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.exm.book.entity.Book;
import team.exm.book.entity.StuBook;
import team.exm.book.entity.User;
import team.exm.book.mapper.BookMapper;
import team.exm.book.mapper.StuBookMapper;
import team.exm.book.mapper.UserMapper;
import team.exm.book.web.request.BookVO;
import team.exm.book.web.request.StuBookVO;
import team.exm.book.web.response.ResponseEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StuBookService {
    private ResponseEntity re;
    private StuBook sb;
    @Autowired
    UserMapper um;
    @Autowired
    BookMapper bm;
    @Autowired
    StuBookMapper sbm;

    private ResponseEntity checkInfo(BookVO book){
        if(book.getId()==null){
            re=new ResponseEntity(0,"您输入的书籍信息有误");
            return re;
        }
        if(book.getUserID()==null){
            re=new ResponseEntity(0,"当前用户无效");
            return re;
        }
        User uTemp=um.selectByPrimaryKey(book.getUserID());
        if(uTemp==null){
            re=new ResponseEntity(0,"当前用户无效");
            return re;
        }
        Book bTemp=bm.selectByPrimaryKey(book.getId());
        if(bTemp==null){
            re=new ResponseEntity(0,"您要修改的图书不存在");
        }else{
            re=new ResponseEntity(1,"正确");
        }
        return re;
    }
    public ResponseEntity getRecords(Integer user,StuBookVO sbv){
        if (sbv.getPage() == null || sbv.getPage() <= 0 || sbv.getRows() == null || sbv.getRows() <= 0) {
            re = new ResponseEntity(0, "您输入的信息有误");
            return re;
        }
        sbv.setOffset((sbv.getPage() - 1) * sbv.getRows());
        if(user==null&&um.selectByPrimaryKey(user)==null){
            re=new ResponseEntity(0,"当前用户无效");
        }else {
            sbv.setsId(new User());
            sbv.getsId().setId(user);
            int num=sbm.selectByUserIdNum(sbv);
            List<StuBook> list=sbm.selectByUserId(sbv);
            if (list.size() == 0) {
                re = new ResponseEntity(0, "未查询到相关记录");
            } else {
                re = new ResponseEntity(1, String.valueOf(num), list);
            }
        }
        return re;
    }
    public ResponseEntity borrow(BookVO book){
        checkInfo(book);
        if(re.getCode()==0){
            return re;
        }else{
            User uTemp=um.selectByPrimaryKey(book.getUserID());
            Book bTemp=bm.selectByPrimaryKey(book.getId());
            if(uTemp.getRemain()<=0){
                re=new ResponseEntity(0,"请您当前借书数量已达到上限，请归还部分书籍后重新尝试！");
                return re;
            }
            uTemp.setRemain(uTemp.getRemain()-1);
            if(bTemp.getRemain()<=0){
                re=new ResponseEntity(0,"该书籍库存不足");
                return re;
            }
            bTemp.setRemain(bTemp.getRemain()-1);
            Date date=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH,2);
            Date deadline=calendar.getTime();
            sb=new StuBook();
            sb.setsId(uTemp);
            sb.setbId(bTemp);
            sb.setbDate(date);
            sb.setrDate(deadline);
            insertStuBook(sb);
            re=new ResponseEntity(1, "借书成功");
            return re;
        }
    }

    public ResponseEntity returnBook(StuBookVO stuBookVO){
        if(stuBookVO.getId()==null){
            re=new ResponseEntity(0,"您提供的信息无效");
        }else{
            sb=sbm.selectByPrimaryKey(stuBookVO.getId());
            if(sb==null){
                re=new ResponseEntity(0,"该记录不存在");
                return re;
            }
            if(sb.getReturned()){
                re=new ResponseEntity(0,"系统故障");
            }
            User uTemp=um.selectByPrimaryKey(sb.getsId().getId());
            Book bTemp=bm.selectByPrimaryKey(sb.getbId().getId());
            if(uTemp.getTotal()<=uTemp.getRemain()||bTemp.getTotal()<=bTemp.getRemain()){
                re=new ResponseEntity(0,"系统故障");
                return re;
            }
            uTemp.setRemain(uTemp.getRemain()+1);
            bTemp.setRemain(bTemp.getRemain()+1);
            sb.setReturned(true);
            sb.setsId(uTemp);
            sb.setbId(bTemp);
            updateStuBook(sb);
            re=new ResponseEntity(1,"还书成功");
        }
        return re;
    }
    private void insertStuBook(StuBook sb){
        um.updateByPrimaryKeySelective(sb.getsId());
        bm.updateByPrimaryKeySelective((BookVO)sb.getbId());
        sbm.insertSelective(sb);
    }
    private void updateStuBook(StuBook sb){
        um.updateByPrimaryKeySelective(sb.getsId());
        bm.updateByPrimaryKeySelective((BookVO)sb.getbId());
        sbm.updateByPrimaryKeySelective(sb);
    }

}
