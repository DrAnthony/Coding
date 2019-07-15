package team.exm.book.web.request;

import org.springframework.stereotype.Component;
import team.exm.book.entity.User;

@Component
public class UserVO extends User {
    private String code;
    private String newPwd;
    private Integer operation;//0 发送短信，1 核实验证码，3 重置密码;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "code='" + code + '\'' +
                ", newPwd='" + newPwd + '\'' +
                '}';
    }
}
