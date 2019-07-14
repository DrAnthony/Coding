package team.exm.book.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.exm.book.config.AliyunConfig;

@Service
public class MsgService {
    private static Logger log= LoggerFactory.getLogger(MsgService.class);
    @Autowired
    private AliyunConfig aliConfig;
    @Autowired
    private CodeService cs;

    public boolean sendCode(String phone, String code) {
        return sendMSG(phone, code);
    }

    /*使用阿里云进行验证码短信的发送
     * @Param phone:用于接收验证码的手机号码
     * @Param code:随机生成的六位验证码
     * @return true:发送成功
     * @return false:发送失败
     * */
    private boolean sendMSG(String phone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile(aliConfig.getRegionID(), aliConfig.getAccessKeyID(), aliConfig.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(aliConfig.getDomain());
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", aliConfig.getDomain());
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", aliConfig.getSignName());
        request.putQueryParameter("TemplateCode", aliConfig.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        log.info(aliConfig.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            if (response.getHttpStatus() == HttpStatus.SC_OK) {
                System.out.println("验证码 " + code + " 发送成功");
            } else {
                System.out.println("验证码发送失败");
            }
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
