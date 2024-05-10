package top.hyzhu.share.app.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: zhy
 * @Description: 公告分⻚查询
 * @Date: 2024-05-09 19:01
 **/
@Data
@EqualsAndHashCode(callSuper = true) @Schema(name = "NoticeQuery", description = "公告查询")
public class NoticeQuery extends Query {
    @Schema(description = "标题")
    private String title;
}
