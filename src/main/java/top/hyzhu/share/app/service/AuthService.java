package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.model.dto.WxLoginDTO;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserLoginVO;

/**
 * @Author: zhy
 * @Description: User相关业务
 * @Date: 2024-05-07 10:10
 **/

//继承 IService 泛型类，虽然是 Auth 前缀，但是本质上还是User 相关业务，所以泛型还是User，这也是Mybatis Plus 提供给我们的工具
public interface AuthService extends IService<User> {
    /*
    * 登录
    *
    * @param phone 电话
    * @phone code 验证码
    * @return {@link UserLoginVO}*/
    UserLoginVO loginByPhone(String phone, String code);

    /*
     * 微信登录
     *
     * @param loginDTo DTO
     * @return {@link UserLoginVO}
     */
    UserLoginVO weChatLogin(WxLoginDTO loginDTO);
    /*
    * 检查用户是否启用
    *
    * @param userId 用户 ID
    * @return boolean*/
    boolean checkUserEnabled(Integer userId);

    /*
    * 登出*/
    void logout();

    /*
    * 绑定手机号
    *
    * @param phone 电话
    * @param code   验证码
    * @param accessToken 访问令牌*/

    void bindPhone(String phone, String code, String accessToken);
}
