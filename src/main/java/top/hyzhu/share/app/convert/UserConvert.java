package top.hyzhu.share.app.convert;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserInfoVO;
/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 15:58
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    UserInfoVO convert(User user);

}
