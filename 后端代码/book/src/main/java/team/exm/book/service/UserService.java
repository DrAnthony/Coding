package team.exm.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.exm.book.entity.User;
import team.exm.book.mapper.UserMapper;
import team.exm.book.web.request.UserVO;
import team.exm.book.web.response.ResponseEntity;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {
    private Logger log=LoggerFactory.getLogger(UserService.class);
    private ResponseEntity re;
    @Autowired
    UserMapper um;
    @Autowired
    CodeService cs;

    private User clearPassword(User user) {
        if (user.getPwd() != null) {
            user.setPwd(null);
        }
        return user;
    }

    public ResponseEntity updateTime(ResponseEntity re){
        User temp=(User) re.getData();
        Date date=new Date();
        temp.setLastLogin(new Timestamp(date.getTime()));
        updateUser(temp);
        re.setData((User)temp);
        log.info(temp.getLastLogin().toString());
        return re;
    }
    public ResponseEntity selectByPhone(UserVO user) {
        if (user.getPhone() == null || user.getPwd() == null || user.getRole() == null) {
            re = new ResponseEntity(0, "您输入的信息不完整，请重新输入");
        } else {
            User temp = um.selectByPhone(user.getPhone());
            if (temp == null) {
                re = new ResponseEntity(0, "该用户不存在");
            } else if (!temp.getPwd().equals(user.getPwd())) {
                re = new ResponseEntity(0, "密码错误，请核实后重新输入");
            } else {
                if (temp.getRole().equals(user.getRole())) {
                    re = new ResponseEntity(1, "成功", clearPassword(temp));
                } else {
                    re = new ResponseEntity(0, "您选择的身份不匹配");
                }
            }
        }
        return re;
    }

    public ResponseEntity selectById(Integer id) {
        if (id == null) {
            re = new ResponseEntity(0, "您输入的ID为空");
        } else {
            User temp = um.selectByPrimaryKey(id);
            if (temp == null) {
                re = new ResponseEntity(0, "未查询到相关用户信息");
            } else {
                re = new ResponseEntity(1, "查询成功", clearPassword(temp));
            }
        }
        return re;
    }

    public ResponseEntity register(UserVO user) {
        if (user.getId() != null) {
            re = new ResponseEntity(0, "您输入信息不合法");
        } else if ((user.getPhone() == null) ||
                (user.getCode() == null) || (user.getPwd() == null)) {
            re = new ResponseEntity(0, "您输入的信息不完整");
        } else {
            int res = cs.checkCode(user.getPhone(), user.getCode());
            if (res == -1) {
                re = new ResponseEntity(0, "验证码已失效，请重新获取验证码");
            } else if (res == 0) {
                re = new ResponseEntity(0, "验证码错误，请核实后重新输入");
            } else if (res == 1) {
                if(um.selectByPhone(user.getPhone())!=null){
                    re=new ResponseEntity(0,"该手机已经被使用");
                }else {
                    um.insertSelective((User) user);
                    re = new ResponseEntity(1, "注册成功", clearPassword((User)user));
                }
            }
        }
        return re;
    }

    public ResponseEntity delete(UserVO user) {
        if (user.getId() == null) {
            re = new ResponseEntity(0, "您的ID不能为空");
        } else {
            User temp = um.selectByPrimaryKey(user.getId());
            if (temp == null) {
                re = new ResponseEntity(0, "您要注销的账号不存在");
            } else if (!user.getPwd().equals(temp.getPwd())) {
                re = new ResponseEntity(0, "您输入的密码不正确，请核实后继续操作");
            } else if (temp.getRemain() < temp.getTotal()) {
                re = new ResponseEntity(0, "您的账户存在未归还的图书，请将图书归还后再进行此操作");
            } else {
                um.deleteByPrimaryKey(user.getId());
                re = new ResponseEntity(1, "感谢您的使用，期待您的再次光临!");
            }
        }
        return re;
    }

    public ResponseEntity updateUser(User user) {
        if (user.getId() == null) {
            re = new ResponseEntity(0, "您输入的ID为空");
        } else {
            if (um.selectByPrimaryKey(user.getId()) == null) {
                re = new ResponseEntity(0, "用户不存在");
            } else {
                um.updateByPrimaryKeySelective(user);
                re = new ResponseEntity(1, "修改成功");
            }
        }
        return re;
    }

    public ResponseEntity updatePwd(UserVO user) {
        if (user.getId() == null) {
            re = new ResponseEntity(0, "您输入的ID 为空");
        } else {
            User temp = um.selectByPrimaryKey(user.getId());
            if (temp == null) {
                re = new ResponseEntity(0, "用户名不存在");
            } else {
                if (temp.getPwd().equals(user.getPwd())) {
                    temp.setPwd(user.getNewPwd());
                    um.updatePassword(temp);
                    re = new ResponseEntity(1, "修改成功");
                } else {
                    re = new ResponseEntity(0, "密码错误");
                }
            }
        }
        return re;
    }
}
