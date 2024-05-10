package top.hyzhu.share.app.convert;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.hyzhu.share.app.model.entity.Tag;
import top.hyzhu.share.app.model.vo.TagVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-10 21:16
 **/
@Mapper
public interface TagConvert {
    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);
    List<TagVO> convert(List<Tag> list);
}

