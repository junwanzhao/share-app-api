package top.hyzhu.share.app.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: zhy
 * @Description: 封装向客户端返回的用户信息
 * @Date: 2024-05-08 15:05
 **/
@Data
@Schema(description = "用户信息")
public class UserInfoVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 991510314045218759L;

    @Schema(description = "主键")
    private Integer pkId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "微信openId")
    private String wxOpenId;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "积分")
    private Integer bonus;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "今日是否签到")
    private Boolean isDailyCheck;

}
