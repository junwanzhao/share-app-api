package top.hyzhu.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 16:50
 **/
@Data
@TableName("t_bonus_log")
public class BonusLog {
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    private Integer userId;
   /*
   * @see top.hyzhu.share.app.enums.BonusActionEnum
   * */
    private String content;
    private Integer bonus;

    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;



}
