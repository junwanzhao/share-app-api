package top.hyzhu.share.app.common.constant;

/**
 * @Author: zhy
 * @Description: 数据库中公共字段
 * @Date: 2024-04-28 20:22
 **/
public interface Constant {
    //    创建时间
    String CREATE_TIME = "createTime";
    //    更新时间
    String UPDATE_TIME = "updateTime";
    //   逻辑删除
    String DELETE_FLAG = "deleteFlag";
    //用户id
    String USER_ID = "userId";
    // 微信小程序 appId
    String APP_ID = "wxbf95c96878b50fdd";
    // appsecret
    String APP_SECRET ="755738d115094b4c1f59e1e60e578db6";
    //微信返回参数中的属性名
    String WX_ERR_CODE ="errCode";
    //返回参数中的属性名
    String WX_OPENID = "openid";
    // 返回参数中的属性名
    String WX_SESSION_KEY ="session_key";
    // 前端没有登录的时候会携带的token，后续会用到
    String NO_TOKEN ="no-token";
}
