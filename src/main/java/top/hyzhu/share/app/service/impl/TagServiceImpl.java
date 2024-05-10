package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.convert.TagConvert;
import top.hyzhu.share.app.mapper.TagMapper;
import top.hyzhu.share.app.model.entity.Tag;
import top.hyzhu.share.app.model.vo.TagVO;
import top.hyzhu.share.app.service.TagService;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 19:24
 **/
@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    //实现获取标签列表的⽅法
    @Override
    public List<TagVO> getTagList(){
        List<Tag> tagList = baseMapper.selectList(new LambdaQueryWrapper<>());
        return TagConvert.INSTANCE.convert(tagList);
    }
}
