package top.hyzhu.share.app.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @Author: zhy
 * @Description: 声明一些资源查询条件
 * @Date: 2024-05-13 17:23
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ResourceQuery", description = "资源查询")
public class ResourceQuery extends Query {
    @Schema(description = "关键词")
    private String keyword;

    @Schema(description = "⽹盘类型")
    private Integer diskType;

    @Schema(description = "资源类型")
    private Integer resType;

    @Schema(description = "标签")
    private Integer tagId; }
