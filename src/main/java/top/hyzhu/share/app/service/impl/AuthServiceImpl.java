package top.hyzhu.share.app.service.impl;
import org.apache.commons.lang3.ObjectUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.common.cache.RedisCache;
import top.hyzhu.share.app.common.cache.RedisKeys;
import top.hyzhu.share.app.common.cache.TokenStoreCache;
import top.hyzhu.share.app.common.exception.ErrorCode;
import top.hyzhu.share.app.common.exception.ServerException;
import top.hyzhu.share.app.enums.AccountstatusEnum;
import top.hyzhu.share.app.mapper.UserMapper;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserLoginV0;
import top.hyzhu.share.app.service.AuthService;
import top.hyzhu.share.app.utils.JwtUtil;
import static java.awt.image.ImageObserver.ERROR;
import static javax.swing.text.html.HTML.Tag.A;
import static javax.swing.text.html.HTML.Tag.CODE;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @Author: zhy
 * @Description: 实现自定义AuthService接口
 * @Date: 2024-05-07 10:14
 **/
@Slf4j
@Service
@AllArgsConstructor
//继承 Mybatis Plus 提供的ServiceImpl 泛型类（泛型为    UserMapper 以及    User 实体），实现自己定义的 AuthService 接口
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements AuthService {
    private final RedisCache redisCache;
    private final TokenStoreCache tokenStoreCache;
    @Override
    public UserLoginV0 loginByPhone(String phone, String code) {
        //获取验证码cacheKey
        String smsCacheKey = RedisKeys.getSmsKey(phone);
        //从redis中获取验证码
        Integer redisCode =(Integer)redisCache.get(smsCacheKey);
        // 校验验证码合法性
        if(ObjectUtils.isEmpty(redisCode)|| !redisCode.toString().equals(code)){
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        //删除用过的验证码
        redisCache.delete(smsCacheKey);
        //根据手机号获取用户
        User user = baseMapper.getByPhone(phone);
        //判断用户是否注册过，如果user为空代表未注册，进行注册。否则开启登录流程
        if(ObjectUtils.isEmpty(user)){
            log.info("用户不存在，创建用户，phone:{}" ,phone);
            user = new User();
            user.setNickname(phone);
            user.setPhone(phone);
            user.setAvatar("默认头像的url");
            user.setEnabled(AccountstatusEnum.ENABLED.getValue());
            user.setBonus(0);
            user.setRemark("这个人很懒，什么都没有写");
            baseMapper.insert(user);
        }
        //用户被禁用
        if(!user.getEnabled().equals(AccountstatusEnum.ENABLED.getValue())){
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        //构造token
        String accessToken = JwtUtil.createToken(user.getPkId());
        // 构造登陆返回vo
        UserLoginV0 userLoginV0 = new UserLoginV0();
        userLoginV0.setPkId(user.getPkId());
        userLoginV0.setPhone(user.getPhone());
        userLoginV0.setWxOpenId(user.getWxOpenId());
        userLoginV0.setAccessToken(accessToken);
        tokenStoreCache.saveUser(accessToken,userLoginV0);
        return userLoginV0;
    }
}
