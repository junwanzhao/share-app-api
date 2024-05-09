package top.hyzhu.share.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.hyzhu.share.app.mapper.NoticeMapper;
import top.hyzhu.share.app.model.entity.Notice;
import top.hyzhu.share.app.model.vo.NoticeVO;
import top.hyzhu.share.app.service.NoticeService;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-09 18:51
 **/
@Slf4j
@Service
@AllArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Override
    public List<NoticeVO> indexPageNotice() {
        return baseMapper.indexPageNotice();
    } }
