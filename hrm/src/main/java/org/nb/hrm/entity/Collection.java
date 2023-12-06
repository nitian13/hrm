package org.nb.hrm.entity;

import lombok.Data;

//收藏
@Data
public class Collection {
    //收藏id
    private Long id;

    //用户id
    private Long userId;

    //招聘id
    private Long recruitmentId;

    //收藏时间
    private Long time;
}
