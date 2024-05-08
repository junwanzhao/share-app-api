package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.dto.UserEditDTO;
import top.hyzhu.share.app.model.vo.UserInfoVO;
import top.hyzhu.share.app.service.UserService;

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
}
