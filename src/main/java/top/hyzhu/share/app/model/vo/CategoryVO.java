package top.hyzhu.share.app.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: zhy
 * @Description: 分类视图类
 * @Date: 2024-05-10 16:11
 **/
@Data
@Schema(name = "CategoryVO", description = "分类返回vo")
public class CategoryVO {

    @Schema(name="pkId",description = "主键")
    private final Integer pkId;

    @Schema(name="title",description = "标题")
    private final String title;

    @Schema(name="type",description = "分类类型")
    private final Integer type;

    @Schema(name="description",description = "描述")
    private final String description;

}




