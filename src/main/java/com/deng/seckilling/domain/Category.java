package com.deng.seckilling.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 商品子系统——分类实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 12:45
 */
@Data
public class Category {

    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    private Timestamp createTime;

    private Timestamp updateTime;
}
