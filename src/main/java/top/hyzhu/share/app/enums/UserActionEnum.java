package top.hyzhu.share.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 13:33
 **/
@Getter
@AllArgsConstructor
public enum UserActionEnum {
    /**
     * 收藏
     */
    COLLECT(0, "收藏"),
    /**
     * 发布
     */
    PUBLISH(1, "发布"),
    /**
     * 兑换（下载）
     */
    EXCHANGE(2, "兑换"),
    /**
     * 点赞
     */
    LIKE(3, "点赞"),;
    private final Integer code;
    private final String desc;
    public static UserActionEnum getByCode(Integer code) {
        for (UserActionEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    } }
