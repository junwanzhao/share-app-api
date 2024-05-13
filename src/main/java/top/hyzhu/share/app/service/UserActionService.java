package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.enums.UserActionEnum;
import top.hyzhu.share.app.model.entity.UserAction;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 13:35
 **/
public interface UserActionService extends IService<UserAction> {
    void insertUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum);
    void collectResource(Integer resourceId);
    void likeResource(Integer resourceId);
    void exchangeResource(Integer resourceId);
}
