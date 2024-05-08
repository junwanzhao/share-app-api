package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.model.dto.UserEditDTO;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserInfoVO;

/**
 * @Author: zhy
 * @Description: 获取用户信息接口
 * @Date: 2024-05-08 15:46
 **/
public interface UserService extends IService<User> {
    /*
    * 用户信息
    *
    * @return {@link UserInfoVO}*/
    UserInfoVO userInfo();

    /*
    * 更新信息
    *
    * @param userEditDTO 用户编辑 DTO
    * @return UserInfoVO*/
    UserInfoVO updateInfo(UserEditDTO userEditDTO);
}
