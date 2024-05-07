package top.hyzhu.share.app.common.cache;



import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.hyzhu.share.app.model.vo.UserLoginVO;

import java.util.ArrayList;
import java.util.List;

import static top.hyzhu.share.app.common.cache.RedisCache.HOUR_SIX_EXPIRE;
/**
 * @Author: zhy
 * @Description: 用于方便操作用户缓存
 * @Date: 2024-05-07 10:29
 **/
@Component
@AllArgsConstructor
public class TokenStoreCache {
    private final RedisCache redisCache;
    public void saveUser(String accessToken, UserLoginVO user) {
        String accessTokenKey = RedisKeys.getAccessTokenKey(accessToken);
        String userIdKey = RedisKeys.getUserIdKey(user.getPkId());
        if(redisCache.get(userIdKey)!= null) {
            redisCache.delete(String.valueOf(redisCache.get(userIdKey)));
        }
        System.out.println("[TokenStoreCache] accessToken =" + accessToken);
        redisCache.set(userIdKey, accessToken, HOUR_SIX_EXPIRE);
        redisCache.set(accessTokenKey,user,HOUR_SIX_EXPIRE);
    }
    public UserLoginVO getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return JSON.to(UserLoginVO.class, redisCache.get(key));
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }

    public void deleteUserById(Integer id) {
        String userId = RedisKeys.getUserIdKey(id);
        String key =String.valueOf(redisCache.get(userId));
        redisCache.delete(key);
    }

    public void deleteUserByIds(List<Integer> ids) {
        List<String> keys = new ArrayList<>();
        for(Integer id : ids) {
            String userId = RedisKeys.getUserIdKey(id);
            String key = String.valueOf(redisCache.get(userId));
            keys.add(key);
        }
        redisCache.delete(keys);
    }
}
