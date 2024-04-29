package top.hyzhu.share.app.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description 读取配置文件中配置的信息
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "share.sms.ccp")
public class CloopenConfig {

    private String serverIp;
    private String port;
    private String accountSId;
    private String accountToken;
    private String appId;
    private String templateId;
}
