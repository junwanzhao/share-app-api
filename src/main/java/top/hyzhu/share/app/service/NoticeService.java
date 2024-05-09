package top.hyzhu.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyzhu.share.app.common.result.PageResult;
import top.hyzhu.share.app.model.entity.Notice;
import top.hyzhu.share.app.model.query.NoticeQuery;
import top.hyzhu.share.app.model.vo.NoticeVO;

import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-09 18:49
 **/
public interface NoticeService extends IService<Notice> {
    //定义获取⾸⻚置顶公告⽅法
    List<NoticeVO> indexPageNotice();
    //分⻚查询公告⽅法
    PageResult<NoticeVO> getNoticeList(NoticeQuery query);
}
