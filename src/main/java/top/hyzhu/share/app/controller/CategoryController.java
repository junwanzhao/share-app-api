package top.hyzhu.share.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hyzhu.share.app.common.result.Result;
import top.hyzhu.share.app.model.vo.CategoryVO;
import top.hyzhu.share.app.service.CategoryService;
import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 17:05
 **/
@RestController
@AllArgsConstructor
@Tag(name = "分类接口", description = "分类接⼝")
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    //获取分页列表接口
    @GetMapping("/list")
    @Operation(summary = "获取分类列表")
    public Result<List<CategoryVO>> list() {
        return Result.ok(categoryService.getCategoryList());
    }
}
