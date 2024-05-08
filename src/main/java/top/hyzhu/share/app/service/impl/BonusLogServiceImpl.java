package top.hyzhu.share.app.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.common.cache.RequestContext;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.convert.BonusLogConvert;
import top.hyzhu.share.app.mapper.BonusLogMapper;
import top.hyzhu.share.app.model.entity.BonusLog;
import top.hyzhu.share.app.model.query.Query;
import top.hyzhu.share.app.model.vo.BonusLogVO;
import top.hyzhu.share.app.service.BonusLogService;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-08 17:44
 **/
@Slf4j
@Service
@AllArgsConstructor
public class BonusLogServiceImpl extends ServiceImpl<BonusLogMapper, BonusLog> implements BonusLogService {
    @Override
    public PageResult<BonusLogVO> page(Query query) {
        Integer userId = RequestContext.getUserId();
        Page<BonusLog> bonusLogPage = baseMapper.selectPage(new Page<>(query.getPage(), query.getLimit()), new LambdaQueryWrapper<BonusLog>().eq(BonusLog::getUserId, userId));
        List<BonusLogVO> voList = BonusLogConvert.INSTANCE.convert(bonusLogPage.getRecords());
        return new PageResult<>(voList, bonusLogPage.getTotal());
    }
}
