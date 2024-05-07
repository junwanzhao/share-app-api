package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserLoginV0;

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
    UserLoginV0 loginByPhone(String phone,String code);

    /*
     * 微信登录
     *
     * @param loginDTo DTO
     * @return {@link UserLoginVO}
     */

}
