package top.hyzhu.share.app.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: zhy
 * @Description: 用于返回封装给客户端的参数
 * @Date: 2024-05-07 10:19
 **/
@Data
@Schema(description="用户登录vo")
public class UserLoginVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8212240698099812005L;

    @Schema(description = "用户ID")
    private Integer pkId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "微信0penId")
    private String wxOpenId;

    @Schema(description = "令牌")
    private String accessToken;
}