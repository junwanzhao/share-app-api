package top.hyzhu.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-09 18:39
 **/
@Data
@TableName("t_notice")
public class Notice {
    @TableId(value = "pk_id", type= IdType.AUTO)
    private Integer pkId;

    private String title;

    private String cover;

    private String content;

    private Integer adminId;
    /**
     * @see top.hyzhu.share.app.enums.CommonStatusEnum
     */
    private Integer isTop;
    /**
     * @see top.hyzhu.share.app.enums.CommonStatusEnum
     */
    private Integer isSwiper;

    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime; }
