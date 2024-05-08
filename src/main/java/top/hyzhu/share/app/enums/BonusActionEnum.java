package top.hyzhu.share.app.enums;

import lombok.Getter;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 16:57
 **/
@Getter
public enum BonusActionEnum {
    RESOURCE_AUDIT_PASS(10,"资源审核通过"),

    RESOURCE_EXCHANGE(0,"资源兑换"),

    DAILY_SIGN(5,"每日签到"),

    RESOURCE_BE_EXCHANGED(5,"资源被兑换");

    private final Integer bonus;
    private final String desc;

    BonusActionEnum(Integer bonus, String desc) {
        this.bonus = bonus;
        this.desc = desc;
    }
}
