package top.hyzhu.share.app.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;


import static top.hyzhu.share.app.common.constant.Constant.*;
import top.hyzhu.share.app.common.cache.RedisCache;
import top.hyzhu.share.app.common.cache.RedisKeys;
import top.hyzhu.share.app.common.cache.RequestContext;
import top.hyzhu.share.app.common.cache.TokenStoreCache;
import top.hyzhu.share.app.common.exception.ErrorCode;
import top.hyzhu.share.app.common.exception.ServerException;
import top.hyzhu.share.app.enums.AccountStatusEnum;
import top.hyzhu.share.app.mapper.UserMapper;
import top.hyzhu.share.app.model.dto.WxLoginDTO;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.vo.UserLoginVO;
import top.hyzhu.share.app.service.AuthService;
import top.hyzhu.share.app.utils.AESUtil;
import top.hyzhu.share.app.utils.CommonUtils;
import top.hyzhu.share.app.utils.JwtUtil;



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
    public UserLoginVO loginByPhone(String phone, String code) {
        //获取验证码cacheKey
        String smsCacheKey = RedisKeys.getSmsKey(phone);
        //从redis中获取验证码
        Integer redisCode = (Integer) redisCache.get(smsCacheKey);
        // 校验验证码合法性
        if (ObjectUtils.isEmpty(redisCode) || !redisCode.toString().equals(code)) {
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        //删除用过的验证码
        redisCache.delete(smsCacheKey);
        //根据手机号获取用户
        User user = baseMapper.getByPhone(phone);
        //判断用户是否注册过，如果user为空代表未注册，进行注册。否则开启登录流程
        if (ObjectUtils.isEmpty(user)) {
            log.info("用户不存在，创建用户，phone:{}", phone);
            user = new User();
            user.setNickname(phone);
            user.setPhone(phone);
            user.setAvatar("默认头像的url");
            user.setEnabled(AccountStatusEnum.ENABLED.getValue());
            user.setBonus(0);
            user.setRemark("这个人很懒，什么都没有写");
            baseMapper.insert(user);
//            throw new ServerException("账户不存在，请先微信注册");
        }
        //用户被禁用
        if (!user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue())) {
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        //构造token
        String accessToken = JwtUtil.createToken(user.getPkId());
        // 构造登陆返回vo
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setPkId(user.getPkId());
        userLoginVO.setPhone(user.getPhone());
        userLoginVO.setWxOpenId(user.getWxOpenId());
        userLoginVO.setAccessToken(accessToken);
        tokenStoreCache.saveUser(accessToken, userLoginVO);
        return userLoginVO;
    }

    @Override
    public UserLoginVO weChatLogin(WxLoginDTO loginDTO) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + APP_ID +
                "&secret=" + APP_SECRET + "&js_code=" + loginDTO.getCode() +
                "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(url,
                String.class);
        if (StringUtils.contains(jsonData, WX_ERR_CODE)) {
            // 出错了
            throw new ServerException("openId获取失败," + jsonData);
        }
        //解析返回数据
        JSONObject jsonObject = JSON.parseObject(jsonData);
        log.info("wxData:{}", jsonData);
        String openid = Objects.requireNonNull(jsonObject).getString(WX_OPENID);
        String sessionKey = jsonObject.getString(WX_SESSION_KEY);
        // 对用户加密数据解密
        String jsonUserData = AESUtil.decrypt(loginDTO.getEncryptedData(), sessionKey, loginDTO.getIv());
        log.info("wxUserInfo:{}", jsonUserData);

        JSONObject wxUserData = JSON.parseObject(jsonUserData);

        User user = baseMapper.getByWx0penId(openid);
        if (ObjectUtils.isEmpty(user)) {
            log.info("用户不存在，创建用户，openId:{}", openid);
            user = new User();
            user.setWxOpenId(openid);
            user.setNickname(wxUserData.getString("nickName"));
            user.setAvatar(wxUserData.getString("avatarUrl"));
            user.setGender(wxUserData.getInteger("gender"));
            user.setEnabled(AccountStatusEnum.ENABLED.getValue());
            user.setBonus(0);
            user.setRemark("这个人很懒，什么都没有写");
            baseMapper.insert(user);
            // 用户被禁用
            if (!user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue())) {
                throw new ServerException(ErrorCode.ACCOUNT_DISABLED);
            }
            String accessToken = JwtUtil.createToken(user.getPkId());
            UserLoginVO userLoginVO = new UserLoginVO();
            userLoginVO.setPkId(user.getPkId());
            if (StringUtils.isNotBlank(user.getPhone())) {
                userLoginVO.setPhone(user.getPhone());
            }
            userLoginVO.setWxOpenId(user.getWxOpenId());
            userLoginVO.setAccessToken(accessToken);
            tokenStoreCache.saveUser(accessToken, userLoginVO);
            return userLoginVO;
        }
        return null;
    }

    @Override
    public boolean checkUserEnabled(Integer userId){
        User user = baseMapper.selectById(userId);
        if(ObjectUtils.isEmpty(user)) {
            return false;
        }
        return user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue());
    }
    @Override
    public void logout(){
        // 从上下文中获取userId，然后获取redisKey
        String cacheKey = RedisKeys.getUserIdKey(RequestContext.getUserId());
        // 通过userId，获取redis中的 accessToken
        String accessToken =(String)redisCache.get(cacheKey);
        // 删除缓存中的 token
        redisCache.delete(cacheKey);
        // 删除缓存中的用户信息
        tokenStoreCache.deleteUser(accessToken);
    }
    @Override
    public void bindPhone(String phone, String code,String accessToken) {
        //简单校验手机号合法性
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        //获取手机验证码，校验验证码正确性
        String redisCode = redisCache.get(RedisKeys.getSmsKey(phone)).toString();
        if (ObjectUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        // 删除验证码缓存
        redisCache.delete(RedisKeys.getSmsKey(phone));
        // 获取当前用户信息
        User userByPhone = baseMapper.getByPhone(phone);
        // 获取当前登录的用户信息
        UserLoginVO userLogin = tokenStoreCache.getUser(accessToken);
        // 判断新手机号是否存在用户
        if (ObjectUtils.isEmpty(userByPhone)) {
            // 存在用户，并且不是当前用户，抛出异常
            if (!userLogin.getPkId().equals(userByPhone.getPkId())) {
                throw new ServerException(ErrorCode.PHONE_IS_EXIST);
            }
            //存在用户，并且是当前用户，提示用户手机号相同
            if (userLogin.getPhone().equals(phone)) {
                throw new ServerException(ErrorCode.THE_SAME_PHONE);
            }
        }
        // 重新设置手机号
        User user = baseMapper.selectById(userLogin.getPkId());
        user.setPhone(phone);
        if(baseMapper.updateById(user)< 1) {
            throw new ServerException(ErrorCode.OPERATION_FAIL);
        }
    }
}

