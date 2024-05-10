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
 * @Description:
 * @Date: 2024-05-09 18:46
 **/
public interface NoticeMapper extends BaseMapper<Notice> {

    //获取首页置顶公告列表⽅法
    List<NoticeVO> indexPageNotice();
    //分⻚查询公告⽅法
    List<NoticeVO> getNoticePage(Page<NoticeVO> page, @Param("query") NoticeQuery query);
    //分⻚查询公告详情⽅法
    NoticeVO getNoticeDetail(Integer id);
    //获取轮播图的⽅法
    List<NoticeVO> swiperNotice();
}
