package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.vo.TagVO;

import top.hyzhu.share.app.service.TagService;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 19:21
 **/
@RestController
@AllArgsConstructor
@Tag(name = "标签接口", description = "标签接⼝")
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;
    //获取分页列表接口
    @GetMapping("/list")
    @Operation(summary = "获取标签列表")
    public Result<List<TagVO>> list() {
        return Result.ok(tagService.getTagList());
    }
}
