package top.hyzhu.share.app.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class OssConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
