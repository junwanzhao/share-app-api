package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.common.cache.RequestContext;
import top.hyzhu.share.app.enums.UserActionEnum;
import top.hyzhu.share.app.mapper.UserActionMapper;
import top.hyzhu.share.app.model.entity.UserAction;
import top.hyzhu.share.app.service.UserActionService;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 16:16
 **/
@Slf4j
@Service
@AllArgsConstructor
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {
    @Override
    public void insertUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setResourceId(resourceId);
        userAction.setType(userActionEnum.getCode());
        save(userAction);
    }
    @Override
    public void collectResource(Integer resourceId) {
        actionResource(RequestContext.getUserId(), resourceId, UserActionEnum.COLLECT);
    }
    @Override
    public void likeResource(Integer resourceId) {
        actionResource(RequestContext.getUserId(), resourceId, UserActionEnum.LIKE); }

    private void actionResource(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        LambdaQueryWrapper<UserAction> queryWrapper = new LambdaQueryWrapper<>
                ();
        // 根据资源id和⾏为枚举值来查询⽤户⾏为
        queryWrapper.eq(UserAction::getResourceId, resourceId)
                .eq(UserAction::getUserId, userId)
                .eq(UserAction::getType, userActionEnum.getCode());
        // 存在就删除（如取消点赞、取消收藏）
        if (baseMapper.selectCount(queryWrapper) > 0) {
            // TODO 兑换资源处理
            baseMapper.delete(queryWrapper);
        } else {
            // 如新增点赞、收藏操作
            insertUserAction(userId, resourceId, userActionEnum);
        }
    }

}
