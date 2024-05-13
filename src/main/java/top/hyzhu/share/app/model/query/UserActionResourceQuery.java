package top.hyzhu.share.app.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 17:03
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "UserActionResourceQuery", description = "⽤户⾏为资源查询")
public class UserActionResourceQuery extends Query {
    /**
     * @see top.hyzhu.share.app.enums.UserActionEnum
     */
    @Schema(description = "⽤户⾏为")
    private Integer type; }
