package team.exm.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.exm.book.entity.User;
import team.exm.book.mapper.UserMapper;
import team.exm.book.web.request.UserVO;
import team.exm.book.web.response.ResponseEntity;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {
    private Logger log=LoggerFactory.getLogger(UserService.class);
    private ResponseEntity re;

    @Value("${upload-path}")
    private String path;

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
            int res = cs.checkCode(user);
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

    public ResponseEntity forget(UserVO user){
        Integer ope=user.getOperation();
        if(ope==null){
            re=new ResponseEntity(0,"操作码不能为空");
            return re;
        }
        String phone=user.getPhone();
        if(phone==null){
            re=new ResponseEntity(0,"您输入的手机号有误");
            return re;
        }
        if(um.selectByPhone(phone)==null){
            re=new ResponseEntity(0,"该手机号未注册");
            return re;
        }
        if(ope==0){
            cs.addCode(user);
            re=new ResponseEntity(1,"验证码发送成功");
        }else if(ope==1){
            int res=cs.checkCode(user);
            if (res == -1) {
                re = new ResponseEntity(0, "验证码已失效，请重新获取验证码");
            } else if (res == 0) {
                re = new ResponseEntity(0, "验证码错误，请核实后重新输入");
            } else if (res == 1) {
                re=new ResponseEntity(1,"验证码正确");
            }
        } else if(ope==2){
            if(user.getNewPwd()==null){
                re=new ResponseEntity(0,"您输入的新密码为空");
            }else{
                User temp=um.selectByPhone(phone);
                temp.setPwd(user.getNewPwd());
                um.updatePassword(temp);
                re=new ResponseEntity(1,"密码重置成功");
            }
        }else {
            re=new ResponseEntity(0,"操作码错误");
        }
        return re;
    }

    public ResponseEntity upload(Integer id, MultipartFile multipartFile){
        if(id==null){
            re=new ResponseEntity(0,"当前用户未登录");
            return re;
        }
        if(um.selectByPrimaryKey(id)==null){
            re=new ResponseEntity(0,"当前用户无效");
            return re;
        }

        if(multipartFile.isEmpty()){
            re=new ResponseEntity(0,"文件为空");
            return re;
        }
        String originalname=multipartFile.getOriginalFilename();
        String type=originalname.substring(originalname.lastIndexOf('.'));
        String fileName=String.valueOf(System.currentTimeMillis())+type;
        File des=new File(path+fileName);
        log.info(des.getPath());
        try{
            multipartFile.transferTo(des);
            User temp=new User();
            temp.setId(id);
            temp.setPhoto(fileName);
            um.updateByPrimaryKeySelective(temp);
            re=new ResponseEntity(1,"上传成功");
        }catch (Exception e){
            e.printStackTrace();
            re=new ResponseEntity(0,"系统错误，文件上传失败");
        }
        return re;
    }
}
