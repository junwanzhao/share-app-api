package top.hyzhu.share.app.common.cache;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description 统一维护 key
 */

public class RedisKeys {
    /**
    * 验证码Key
    */
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }

    /*
    * accessToken Key
    * */

    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:token:" + accessToken;
    }

    /*
    * 获取用户 ID 密钥
    *
    * @param id id
    * @return {@link String}*/
    public static String getUserIdKey(Integer id) {
        return "sys:userId:" + id;
    }

}
