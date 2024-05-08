package top.hyzhu.share.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: zhy
 * @Description: 封装用户需要修改的信息对应的实体
 * @Date: 2024-05-08 16:23
 **/
@Data
@Schema(description = "用户修改dto")
public class UserEditDTO {
    @Schema(description = "主键")
    private Integer pkId;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "描述")
    private String remark;
}

