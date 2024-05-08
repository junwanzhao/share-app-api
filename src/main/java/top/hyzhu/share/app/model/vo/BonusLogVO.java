package top.hyzhu.share.app.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 17:01
 **/
@Data
@Schema(name = "BonusLogVO", description = "积分日志返回VO")
public class BonusLogVO {

    @Schema(name = "pkId",description = "主键")
    private Integer pkId;

    @Schema(name = "userId", description = "用户id")
    private Integer userId;

    @Schema(name = "bonus", description = "积分")
    private String bonus;

    @Schema(name = "content", description = "描述")
    private String content;


    @Schema(name = "createTime", description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
