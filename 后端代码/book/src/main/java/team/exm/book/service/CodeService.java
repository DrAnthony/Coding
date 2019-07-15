package team.exm.book.service;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.exm.book.entity.Code;
import team.exm.book.mapper.CodeMapper;
import team.exm.book.mapper.UserMapper;
import team.exm.book.web.request.UserVO;
import team.exm.book.web.response.ResponseEntity;

@Service
public class CodeService {

    private ResponseEntity re;
    @Autowired
    CodeMapper cm;
    @Autowired
    CodeTimerService cts;
    @Autowired
    MsgService msg;
    @Autowired
    UserMapper um;

    //要增加数据校验代码
    public ResponseEntity addCode(UserVO user) {
        String phone = user.getPhone();
        if(user.getOperation()!=0&&um.selectByPhone(user.getPhone())!=null){
            re=new ResponseEntity(0,"该手机已经被使用");
            return re;
        }
        if(cm.selectByPhone(phone)!=null){
            cm.deleteByPrimaryKey(cm.selectByPhone(phone).getId());
        }
        String code = createCode();
        Code c = new Code();
        if (msg.sendCode(phone, code)) {
            c.setPhone(phone);
            c.setCode(code);
            cm.insertSelective(c);
            cts.setId(c.getId());
            cts.start();
            System.out.println("线程创建完成");
            re = new ResponseEntity(1, "验证码发送成功");
        } else {
            re = new ResponseEntity(0, "验证码发送失败");
        }
        return re;
    }

    /*
     * @return 1:验证码输入正确
     * @return 0:验证码输入错误
     * @return -1:验证码已失效
     * */
    public int checkCode(UserVO user) {
        if (cm.queryNum(user.getPhone()) == 0) {
            return -1;
        } else {
            if (user.getCode().equals(cm.selectByPhone(user.getPhone()).getCode())) {
                stopTimer();
                return 1;
            } else
                return 0;
        }
    }

    public void deleteCode(int id) {
        cm.deleteByPrimaryKey(id);
    }

    public void stopTimer() {
        cts.setStop(true);
    }

    /*随机生成六位code*/
    private String createCode() {
        String res = "";
        for (int i = 0; i < 6; i++) {
            int temp = (int) (Math.random() * 10);
            res += String.valueOf(temp);
        }
        System.out.println("*******************CODE:" + res);
        return res;
    }
}
