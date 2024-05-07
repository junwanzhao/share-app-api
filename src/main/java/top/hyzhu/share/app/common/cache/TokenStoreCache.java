package top.hyzhu.share.app.common.cache;
import static top.hyzhu.share.app.common.cache.RedisCache.HOUR_SIX_EXPIRE;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.hyzhu.share.app.model.vo.UserLoginV0;

/**
 * @Author: zhy
 * @Description: 用于方便操作用户缓存
 * @Date: 2024-05-07 10:29
 **/
@Component
@AllArgsConstructor
public class TokenStoreCache {

    public void saveUser(String accessToken, UserLoginV0 userLoginV0) {
    }
}
