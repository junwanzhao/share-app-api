package top.hyzhu.share.app.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: zhy
 * @Description: 标签视图类
 * @Date: 2024-05-10 19:15
 **/

@Data
@Schema(name = "TagVO", description = "标签返回vo")
public class TagVO {

    @Schema(name="pkId",description = "主键")
    private final Integer pkId;

    @Schema(name="title",description = "标题")
    private final String title;

    @Schema(name="isHot",description = "是否热门")
    private final Integer isHot;
}
