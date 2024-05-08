package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.common.cache.RequestContext;
import top.hyzhu.share.app.common.exception.ErrorCode;
import top.hyzhu.share.app.common.exception.ServerException;
import top.hyzhu.share.app.convert.UserConvert;
import top.hyzhu.share.app.mapper.UserMapper;
import top.hyzhu.share.app.model.dto.UserEditDTO;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserInfoVO;
import top.hyzhu.share.app.service.UserService;

/**
 * @Author: zhy
 * @Description: 用户信息接口实现类
 * @Date: 2024-05-08 15:50
 **/
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public UserInfoVO userInfo() {
        Integer userId = RequestContext.getUserId();
        //查询数据库
        User user = baseMapper.selectById(userId);
        if(user == null) {
            log.error("用户不存在，userId:{}", userId);
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
            UserInfoVO userInfoVO = UserConvert.INSTANCE.convert(user);
            // TODO 用户是否签到
            return userInfoVO;
    }

    @Override
    public UserInfoVO updateInfo(UserEditDTO userEditDTO) {
        Integer userId = RequestContext.getUserId();
        userEditDTO.setPkId(userId);
        User user = UserConvert.INSTANCE.convert(userEditDTO);
        if (user.getPkId() == null) {
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        try {
            if (baseMapper.updateById(user) < 1) {
                throw new ServerException("修改失败");
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return this.userInfo();
    }
}
