package top.hyzhu.share.app.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description 读取配置文件中配置的信息
 */
@Data //接口重写
@Configuration  //Sprig提供注解
@ConfigurationProperties(prefix = "share.sms.ccp")  //Spring提供接口，用于读取配置文件中的配置

//创建CloopenConfig 配置类，读取配置文件中刚刚配置的信息
public class CloopenConfig {
    private String serverIp;
    private String port;
    private String accountSId;
    private String accountToken;
    private String appId;
    private String templateId;
}
