package top.hyzhu.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 13:32
 **/
@Data
@TableName("t_action")
public class UserAction {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

    private Integer userId;

    private Integer resourceId;
    /**
     * @see top.hyzhu.share.app.enums.UserActionEnum
     */
    private Integer type;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)

    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime; }