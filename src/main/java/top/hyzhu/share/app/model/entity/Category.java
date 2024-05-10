package top.hyzhu.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: zhy
 * @Description: 分类
 * @Date: 2024-05-10 15:45
 **/
@Data
@TableName("t_category")
public class Category {
    @TableId(value = "pk_id", type= IdType.AUTO)
    private Integer pkId;

    private String title;
    /**
     * @see top.hyzhu.share.app.enums.CategoryTypeEnum
     */
    private Integer type;

    private String description;
    /**
     * @see top.hyzhu.share.app.enums.CommonStatusEnum
     */
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
