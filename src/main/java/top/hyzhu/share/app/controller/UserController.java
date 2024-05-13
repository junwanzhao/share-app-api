package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.dto.UserEditDTO;
import top.hyzhu.share.app.model.query.Query;
import top.hyzhu.share.app.model.query.UserActionResourceQuery;
import top.hyzhu.share.app.model.vo.BonusLogVO;
import top.hyzhu.share.app.model.vo.ResourceItemVO;
import top.hyzhu.share.app.model.vo.UserInfoVO;
import top.hyzhu.share.app.service.BonusLogService;
import top.hyzhu.share.app.service.ResourceService;
import top.hyzhu.share.app.service.UserService;
import top.hyzhu.share.app.service.UserActionService;

/**
 * @Author: zhy
 * @Description: 实现获取用户信息接口
 * @Date: 2024-05-08 16:11
 **/
@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "用户接口")
public class UserController {
    private final UserService userService;

    @GetMapping("info")
    @Operation(summary = "查询用户信息")
    public Result<UserInfoVO> userInfo() {
        return Result.ok(userService.userInfo());
    }

    @PostMapping("update")
    @Operation(summary = "修改用户信息")
    public Result<UserInfoVO> update(@RequestBody UserEditDTO userEditDTO) {
        return Result.ok(userService.updateInfo(userEditDTO));
    }

    private final BonusLogService bonusLogService;
    @PostMapping("bonus/page")
    @Operation(summary = "积分明细")
    public Result<PageResult<BonusLogVO>> bonusPage(@RequestBody @Valid Query query) {
        return Result.ok(bonusLogService.page(query));
    }

    @PostMapping("dailyCheck")
    @Operation(summary = "每⽇签到")
    public Result<Object> dailyCheck() {
        bonusLogService.dailyCheck();
        return Result.ok();
    }

    private final UserActionService userActionService;
    @PostMapping("resource/collect")
    @Operation(summary = "收藏资源")
    public Result<Object> collectResource(@RequestParam Integer resourceId) {
        userActionService.collectResource(resourceId);
        return Result.ok();
    }

    @PostMapping("resource/like")
    @Operation(summary = "点赞资源")
    public Result<Object> likeResource(@RequestParam Integer resourceId) {
        userActionService.likeResource(resourceId);
        return Result.ok();
    }

    @PostMapping("resource/exchange") @Operation(summary = "兑换下载资源")
    public Result<Object> exchangeResource(@RequestParam Integer resourceId) {
        userActionService.exchangeResource(resourceId);
        return Result.ok();
    }

    private final ResourceService resourceService;
    @PostMapping("resource")
    @Operation(summary = "资源⾏为列表")
    public Result<PageResult<ResourceItemVO>> resourcePage(@RequestBody UserActionResourceQuery query) {
        return Result.ok(resourceService.getUserActionResourcePage(query)); }
}
