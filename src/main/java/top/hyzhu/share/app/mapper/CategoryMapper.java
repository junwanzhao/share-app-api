package top.hyzhu.share.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hyzhu.share.app.model.entity.Category;
import top.hyzhu.share.app.model.vo.CategoryVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 16:43
 **/
public interface CategoryMapper extends BaseMapper<Category> {
    //获取分类列表⽅法
    List<CategoryVO> getCategoryList();
}
