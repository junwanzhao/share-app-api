package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.dto.WxLoginDTO;
import top.hyzhu.share.app.model.vo.UserLoginVO;
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
    public Result<UserLoginVO> loginByPhone(@RequestParam("phone") String phone,@RequestParam("code") String code) {
        return Result.ok(authService.loginByPhone(phone,code));
    }
    //@RequestBody：Sp  ring 提供注解，意为使用 JSON 格式接收请求参数
    public Result<UserLoginVO> weChatLogin(@RequestBody WxLoginDTO dto){
        return Result.ok(authService.weChatLogin(dto));
    }
}
