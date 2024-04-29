package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.service.CommonService;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description 实现发送短信接口方法
 */
@Tag(name = "基础服务")
@RestController
@RequestMapping("/common")
@AllArgsConstructor
public class CommonController {
    private final CommonService commonService;

    @PostMapping("/sendSms")
    @Operation(summary = "发送短信")
    public Result<Object> sendSms(@RequestParam("phone") String phone) {
        commonService.sendSms(phone);
        return Result.ok();
    }

    @PostMapping("/upload/img")
    @Operation(summary = "图片上传")
    public Result<Object> uploadImg(@RequestParam("file") MultipartFile file) {
        return Result.ok(commonService.upload(file));
    }
}
