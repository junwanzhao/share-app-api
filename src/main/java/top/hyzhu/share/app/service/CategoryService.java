package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.model.entity.Category;
import top.hyzhu.share.app.model.vo.CategoryVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 16:46
 **/
public interface CategoryService extends IService<Category> {
    //实现获取分类列表的⽅法
    List<CategoryVO> getCategoryList();
}
