package top.hyzhu.share.app.enums;

import lombok.Getter;

/**
 * @Author: zhy
 * @Description: 定义公⽤的状态枚举
 * @Date: 2024-05-09 18:41
 **/
@Getter

public enum CommonStatusEnum {
    NO(0, "否"),
    YES(1, "是"),;

    private final Integer status;
    private final String desc;

    CommonStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
