package top.hyzhu.share.app.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import top.hyzhu.share.app.common.constant.Constant;
import java.util.HashMap;
import java.util.Map;
/**
 * @author zhy
 * @date 2024/4/28
 * @description JwtUtil
 **/
@Slf4j
public class JwtUtil {
    public static String createToken(Integer pkId) {
        return "";
    }
    /**
     * 盐值很重要，不能泄漏，且每个项⽬都应该不⼀样，可以放到配置⽂件中
     */
//    private static final String KEY = "spring-boot-learning-ytq";
//    public static String createToken(Integer userId, String nickname) {
//        DateTime now = DateTime.now();
//        // 超时时间
//        DateTime expTime = now.offsetNew(DateField.HOUR, 48);
//        Map<String, Object> payload = new HashMap<>();
//        // 签发时间
//        payload.put(RegisteredPayload.ISSUED_AT, now);
//        // 过期时间
//        payload.put(RegisteredPayload.EXPIRES_AT, expTime);
//        // ⽣效时间
//        payload.put(RegisteredPayload.NOT_BEFORE, now);
//        // ⾃定义载荷
//        payload.put(Constant.USER_ID, userId);
//        payload.put(Constant.NICKNAME, nickname);
//        String token = JWTUtil.createToken(payload, KEY.getBytes());
//        //log.info("⽣成 JWT token：{}", token);
//        return token;
//    }
//    public static boolean validate(String token) {
//        try {
//            JWT jwt = JWTUtil.parseToken(token).setKey(KEY.getBytes());
//            // validate包含了verify
//            boolean validate = jwt.validate(0);
//            //log.info("JWT token 校验结果：{}", validate);
//            return validate;
//        } catch (Exception e) {
//            log.error("JWT token 校验异常：{}", e.getMessage());
//            return false;
//        }
//    }
//    public static JSONObject getPayload(String token) {
//        JWT jwt = JWTUtil.parseToken(token).setKey(KEY.getBytes());
//        JSONObject payloads = jwt.getPayloads();
//        payloads.remove(RegisteredPayload.ISSUED_AT);
//        payloads.remove(RegisteredPayload.EXPIRES_AT);
//        payloads.remove(RegisteredPayload.NOT_BEFORE);
//        //log.info("根据 token 获取原始内容：{}", payloads);
//        return payloads;
//    }
//    public static String getAccessToken(HttpServletRequest request) {
//        String accessToken = request.getHeader("Authorization");
//        if (StringUtils.isBlank(accessToken)) {
//            accessToken = request.getParameter("accessToken");
//        }
//        return accessToken;
//    }
//    public static void main(String[] args) {
//        String token = createToken(10001, "ytq");
//        System.out.println(token);
//        validate(token);
//        getPayload(token);
//    }
}