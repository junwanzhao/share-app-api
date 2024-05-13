package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.common.cache.RequestContext;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.enums.UserActionEnum;
import top.hyzhu.share.app.mapper.ResourceMapper;
import top.hyzhu.share.app.mapper.UserMapper;
import top.hyzhu.share.app.model.entity.Resource;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.query.UserActionResourceQuery;
import top.hyzhu.share.app.model.vo.ResourceItemVO;
import top.hyzhu.share.app.model.vo.UserActionListInfo;
import top.hyzhu.share.app.service.CategoryService;
import top.hyzhu.share.app.service.ResourceService;
import top.hyzhu.share.app.service.TagService;
import top.hyzhu.share.app.service.UserActionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 17:15
 **/

@Slf4j
@Service
@AllArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    private final UserActionService userActionService;
    private final UserMapper userMapper;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    public PageResult<ResourceItemVO> getUserActionResourcePage(UserActionResourceQuery query) {
        Integer userId = RequestContext.getUserId();
        UserActionListInfo userActionListInfo = userActionService
                .selectResourceListByUserIdAndType(userId, UserActionEnum.
                        getByCode(query.getType()), new Page<>(query.getPage(), query.getLimit())) ;
        // 查询结果为空，直接返回空
        if (userActionListInfo.getTotal() == 0) {
            return new PageResult<>(Collections.emptyList(), 0);
        }
        List<Resource> records = baseMapper.selectBatchIds(userActionListInfo.getResourceIdList());
        List<ResourceItemVO> voList = records.stream().map(resource -> {
            ResourceItemVO vo = new ResourceItemVO();
            vo.setPkId(resource.getPkId());
            vo.setTitle(resource.getTitle());
            vo.setPrice(resource.getPrice());
            vo.setIsTop(resource.getIsTop());
            vo.setDetail(resource.getDetail());
            vo.setStatus(resource.getStatus());
            vo.setCreateTime(resource.getCreateTime());
            User author = userMapper.selectById(resource.getAuthor());
            vo.setAuthor(author.getNickname());
            vo.setAuthorAvatar(author.getAvatar());
            vo.setDiskType(categoryService.getById(resource.getDiskType()) .getTitle());
            vo.setResType(categoryService.queryCategoryNameList(resource.getResType()));
            vo.setTags(tagService.queryTagNamesByIds(resource.getTags()));
            vo.setLikeNum(userActionService.selectCountByResourceIdAndType
                    (resource.getPkId(), UserActionEnum.LIKE));
            vo.setDownloadNum(userActionService.selectCountByResourceIdAndType(resource.getPkId(), UserActionEnum.EXCHANGE));
            vo.setCollectNum(userActionService.selectCountByResourceIdAndType(resource.getPkId(), UserActionEnum.COLLECT));
            return vo;
        }).collect(Collectors.toList());
        return new PageResult<>(voList, userActionListInfo.getTotal());
    }
}

