package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.model.entity.Resource;
import top.hyzhu.share.app.model.query.UserActionResourceQuery;
import top.hyzhu.share.app.model.vo.ResourceItemVO;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 17:14
 **/
public interface ResourceService extends IService<Resource> {
    PageResult<ResourceItemVO> getUserActionResourcePage(UserActionResourceQuery query); }
