package top.hyzhu.share.app.enums;

import lombok.Getter;

/**
 * @Author: zhy
 * @Description: 分类类型
 * @Date: 2024-05-10 16:04
 **/
@Getter
public enum CategoryTypeEnum {
    Web_Disk_Type(0, "网盘类型"),
    Resource_Type(1, "资源类型"),;

    private final Integer status;
    private final String desc;

    CategoryTypeEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
}
}
