package top.hyzhu.share.app.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: zhy
 * @Description: 资源列表项视图类
 * @Date: 2024-05-13 17:08
 **/
@Data
@Schema(name = "ResourceItemVO", description = "资源列表项vo")
public class ResourceItemVO {
    @Schema(description = "主键")
    private Integer pkId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "作者头像")
    private String authorAvatar;

    @Schema(description = "详情")
    private String detail;

    @Schema(description = "⽹盘类型")
    private String diskType;

    @Schema(description = "资源类型")
    private List<String> resType;

    @Schema(description = "标签")
    private List<String> tags;

    @Schema(description = "价格")
    private Integer price;

    @Schema(description = "点赞数")
    private Integer likeNum;

    @Schema(description = "下载数")
    private Integer downloadNum;

    @Schema(description = "收藏数")
    private Integer collectNum;

    @Schema(description = "是否置顶")
    private Integer isTop;

    @Schema(description = "审核状态")
    private Integer status;

    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
