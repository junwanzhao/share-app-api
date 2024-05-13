package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.common.cache.RequestContext;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.enums.ResourceStatusEnum;
import top.hyzhu.share.app.enums.UserActionEnum;
import top.hyzhu.share.app.mapper.ResourceMapper;
import top.hyzhu.share.app.mapper.UserMapper;
import top.hyzhu.share.app.model.entity.Resource;
import top.hyzhu.share.app.model.entity.User;
import top.hyzhu.share.app.model.query.ResourceQuery;
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

    @Override
    public PageResult<ResourceItemVO> getResourcePage(ResourceQuery query) {
        // 构造查询条件
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getStatus, ResourceStatusEnum.AUDITED.getCode())
                .like(StringUtils.isNotBlank(query.getKeyword()), Resource::getTitle, "%" + query.getKeyword() + "%")
                .like(StringUtils.isNotBlank(query.getKeyword()), Resource::getDetail, "%" + query.getKeyword() + "%")
                .eq(query.getDiskType() != null && query.getDiskType() > 0, Resource::getDiskType, query.getDiskType())
                .apply(query.getResType() != null && query.getResType() > 0, "JSON_CONTAINS(res_type,JSON_ARRAY({0}))=1", query.getResType())
                .apply(query.getTagId() != null && query.getTagId() > 0, "JSON_CONTAINS(tags,JSON_ARRAY({0}))=1", query.getTagId())
                .orderByDesc(Resource::getIsTop)
                .orderByDesc(Resource::getCreateTime);
        // 分⻚查询
        Page<Resource> page = page(new Page<>(query.getPage(), query.getLimit( )), wrapper);
        List<Resource> records = page.getRecords();
        // 构造vo返回集合
        List<ResourceItemVO> voList = records.stream().map(resource -> {
            ResourceItemVO vo = new ResourceItemVO();
            vo.setPkId(resource.getPkId());
            vo.setTitle(resource.getTitle());
            vo.setPrice(resource.getPrice());
            vo.setIsTop(resource.getIsTop());
            vo.setDetail(resource.getDetail());
            vo.setCreateTime(resource.getCreateTime());
            // 调⽤⽤户服务，获取⽤户信息
            User author = userMapper.selectById(resource.getAuthor());
            vo.setAuthor(author.getNickname());
            vo.setAuthorAvatar(author.getAvatar());
            // 调⽤其他服务，构造参数
            vo.setDiskType(categoryService.getById(resource.getDiskType()).getTitle());
            vo.setResType(categoryService.queryCategoryNameList(resource.getResType()));
            vo.setTags(tagService.queryTagNamesByIds(resource.getTags()));
            vo.setLikeNum(userActionService.selectCountByResourceIdAndType(resource.getPkId(), UserActionEnum.LIKE));
            vo.setDownloadNum(userActionService.selectCountByResourceIdAndType
                    (resource.getPkId(), UserActionEnum.EXCHANGE));
            vo.setCollectNum(userActionService.selectCountByResourceIdAndType(
                    resource.getPkId(), UserActionEnum.COLLECT));
            return vo;
        }).collect(Collectors.toList());
        return new PageResult<>(voList, page.getTotal()); }
}

