package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.model.entity.BonusLog;
import top.hyzhu.share.app.model.query.Query;
import top.hyzhu.share.app.model.vo.BonusLogVO;

/**
 * @Author: zhy
 * @Description: 创建新增积分日志的方法
 * @Date: 2024-05-08 17:42
 **/
public interface BonusLogService extends IService<BonusLog> {
    PageResult<BonusLogVO> page(Query query);
}
