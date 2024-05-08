package top.hyzhu.share.app.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.hyzhu.share.app.model.entity.BonusLog;
import top.hyzhu.share.app.model.vo.BonusLogVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 17:23
 **/
@Mapper
public interface BonusLogConvert {
    BonusLogConvert INSTANCE = Mappers.getMapper(BonusLogConvert.class);
    List<BonusLogVO> convert(List<BonusLog> list);
}
