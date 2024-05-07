package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.vo.UserLoginV0;
import top.hyzhu.share.app.service.AuthService;

/**
 * @Author: zhy
 * @Description: 编写手机号登录接口
 * @Date: 2024-05-07 13:44
 **/

@RestController
@RequestMapping("/auth")
@Tag(name ="认证接口")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "手机号登录")
    public Result<UserLoginV0> loginByPhone(@RequestParam("phone") String phone,@RequestParam("code") String code) {
        return Result.ok(authService.loginByPhone(phone,code));
    }
}
