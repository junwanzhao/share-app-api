package top.hyzhu.share.app.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.hyzhu.share.app.model.entity.Notice;
import top.hyzhu.share.app.model.query.NoticeQuery;
import top.hyzhu.share.app.model.vo.NoticeVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description: 定义获取⾸⻚置顶通知列表⽅法
 * @Date: 2024-05-09 18:46
 **/
public interface NoticeMapper extends BaseMapper<Notice> {
    List<NoticeVO> indexPageNotice();
    List<NoticeVO> getNoticePage(Page<NoticeVO> page, @Param("query") NoticeQuery query);
    //分⻚查询通知详情⽅法
    NoticeVO getNoticeDetail(Integer id);
}
