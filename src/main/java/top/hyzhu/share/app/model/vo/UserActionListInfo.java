package top.hyzhu.share.app.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

/**
 * @Author: zhy
 * @Description:
 * @Date: 2024-05-13 17:04
 **/
@Data
@AllArgsConstructor
public class UserActionListInfo {
    private long total;
    private List<Integer> resourceIdList; }
