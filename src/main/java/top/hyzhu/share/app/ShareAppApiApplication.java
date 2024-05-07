package top.hyzhu.share.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhy
 * @Date: 2024/4/28
 * @Description: 项目启动主类
 **/
@SpringBootApplication
@MapperScan(basePackages = {"top.hyzhu.share.app.mapper"})
public class ShareAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareAppApiApplication.class, args);
    }
}
