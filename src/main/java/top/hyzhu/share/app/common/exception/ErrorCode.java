package top.hyzhu.share.app.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zhy
 * @Description: 错误异常枚举
 * @Date: 2024-04-28 20:24
 **/
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "登录失效，请重新登录"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试");

    private final int code;
    private final String msg;
}
