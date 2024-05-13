package top.hyzhu.share.app.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.hyzhu.share.app.model.dto.ResourcePublishDTO;
import top.hyzhu.share.app.model.entity.Resource;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 18:54
 **/
@Mapper
public interface ResourceConvert {
    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);
    Resource convert(ResourcePublishDTO dto);
}