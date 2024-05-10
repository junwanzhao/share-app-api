package top.hyzhu.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: zhy
 * @Description: 标签
 * @Date: 2024-05-10 19:02
 **/

@Data
@TableName("t_tag")
public class Tag {
    @TableId(value = "pk_id", type= IdType.AUTO)
    private Integer pkId;

    private String title;
    /**
     * @see top.hyzhu.share.app.enums.CommonStatusEnum
     */
    private Integer isHot;
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
