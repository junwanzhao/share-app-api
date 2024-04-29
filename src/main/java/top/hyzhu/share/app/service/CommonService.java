package top.hyzhu.share.app.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description 创建短信发送方法
 */

public interface CommonService {
    /*
    * 发送短信
    *
    * @param phone 手机号*/
    void sendSms(String phone);

    /*
    * 文件上传
    *
    * @param file 文件
    * @return 上传后的url
    * */
    String upload(MultipartFile file);
}
