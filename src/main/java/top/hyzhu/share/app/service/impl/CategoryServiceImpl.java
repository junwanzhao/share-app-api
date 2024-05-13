package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.mapper.CategoryMapper;
import top.hyzhu.share.app.model.entity.Category;
import top.hyzhu.share.app.model.vo.CategoryVO;
import top.hyzhu.share.app.service.CategoryService;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 16:48
 **/
@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

//实现获取分类列表的⽅法
@Override
public List<CategoryVO> getCategoryList() {

    return baseMapper.getCategoryList();
}

//    private final CategoryMapper categoryMapper;
//    //实现获取分类列表的⽅法
//    @Override
//    public List<CategoryVO> getCategoryList() {
//        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<>());
//        return CategoryConvert.INSTANCE.convert(categoryList);
//    }
@Override
public List<String> queryCategoryNameList(List<Integer> pkIdList) {
    return baseMapper.selectBatchIds(pkIdList)
            .stream()
            .map(Category::getTitle)
            .toList(); }
}
