package top.hyzhu.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author Huany
 */
@Getter
@Setter
@ToString
@Data
@TableName("t_user")  //用于设置表名
public class User {
    //@TableId：标注主键，type = IdType.AUTO 表示主键自增
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String phone;
    private String wxOpenId;
    private String avatar;
    private String nickname;
    private Integer gender;
    private String birthday;
    private Integer bonus;
    private String remark;
    /**
     * @see top.hyzhu.share.app.enums.AccountstatusEnum
     */
    private Integer enabled;

    //@TableField：表示该字段自动填充，在 API项目搭建的时候有过配置，fill = FieldFill.INSERT表示填充时机是insert 语句
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    //@TableLogic：表示该字段是逻辑删除字段，在前文也有过配置描述
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
