package team.exm.book.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun")
public class AliyunConfig {
    private String regionID;
    private String signName;
    private String templateCode;
    private String accessKeyID;
    private String accessSecret;
    private String domain;

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "AliyunConfig{" +
                "regionID='" + regionID + '\'' +
                ", signName='" + signName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", accessKeyID='" + accessKeyID + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
