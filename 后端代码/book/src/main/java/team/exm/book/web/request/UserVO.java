package team.exm.book.web.request;

import org.springframework.stereotype.Component;
import team.exm.book.entity.User;

@Component
public class UserVO extends User {
    private String code;
    private String newPwd;

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

    @Override
    public String toString() {
        return "UserVO{" +
                "code='" + code + '\'' +
                ", newPwd='" + newPwd + '\'' +
                '}';
    }
}
