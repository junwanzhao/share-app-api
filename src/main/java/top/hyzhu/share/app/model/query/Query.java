package top.hyzhu.share.app.model.query;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 17:33
 **/
@Data
public class Query {
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为1")
    Integer page;

    @NotNull(message = "每页条数不能为空")
    @Range(min = 1,max = 100, message = "每页条数,取值范围1-100")

    Integer limit;

    String order;

    boolean asc;


}

