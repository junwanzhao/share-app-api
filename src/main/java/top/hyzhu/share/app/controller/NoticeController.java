package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.query.NoticeQuery;
import top.hyzhu.share.app.model.vo.NoticeVO;
import top.hyzhu.share.app.service.NoticeService;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-09 18:53
 **/
@RestController
@AllArgsConstructor
@Tag(name = "公告接⼝", description = "公告接⼝") @RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/index")
    @Operation(summary = "⾸⻚置顶公告")
    public Result<List<NoticeVO>> index() {
        return Result.ok(noticeService.indexPageNotice());
    }

    @PostMapping("/page")
    @Operation(summary = "分⻚查询公告")
    public Result<PageResult<NoticeVO>> page(@RequestBody @Valid NoticeQuery query) {
        return Result.ok(noticeService.getNoticeList(query));
    }

    @GetMapping("/detail/{id}") @Operation(summary = "公告详情")
    public Result<NoticeVO> detail(@PathVariable Integer id) {
        return Result.ok(noticeService.detail(id));
    }
}
