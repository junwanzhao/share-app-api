package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.model.entity.Tag;
import top.hyzhu.share.app.model.vo.CategoryVO;
import top.hyzhu.share.app.model.vo.TagVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 19:23
 **/
public interface TagService extends IService<Tag> {
    //实现获取标签列表的⽅法
    List<TagVO> getTagList();
    List<String> queryTagNamesByIds(List<Integer> pkIdList);
}
