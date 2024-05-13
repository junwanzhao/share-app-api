package top.hyzhu.share.app.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hyzhu.share.app.model.entity.User;

/**
 * @Author: zhy
 * @Description: 使用已经封装好的SQL操作
 * @Date: 2024-05-07 10:03
 **/
public interface UserMapper extends BaseMapper<User> {
//    根据手机号查询用户
    default User getByPhone(String phone){
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }
//根据微信 openId 查询用户
    default User getByWxOpenId(String openId){
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getWxOpenId, openId));
    }
}
