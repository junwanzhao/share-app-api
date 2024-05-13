package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.query.ResourceQuery;
import top.hyzhu.share.app.model.vo.ResourceItemVO;
import top.hyzhu.share.app.service.ResourceService;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 17:31
 **/
@RestController
@RequestMapping("/resource")
@Tag(name = "资源接⼝")
@AllArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping("page")
    @Operation(summary = "分⻚查询资源")
    public Result<PageResult<ResourceItemVO>> page(@RequestBody ResourceQuery query) {
        return Result.ok(resourceService.getResourcePage(query));
    } }
