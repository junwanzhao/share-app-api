package top.hyzhu.share.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hyzhu.share.app.model.entity.Notice;
import top.hyzhu.share.app.model.vo.NoticeVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description: 定义获取⾸⻚置顶通知列表⽅法
 * @Date: 2024-05-09 18:46
 **/
public interface NoticeMapper extends BaseMapper<Notice> {
    List<NoticeVO> indexPageNotice(); }
