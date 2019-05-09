package com.deng.seckilling.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 商品子系统——Spu实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:25
 */
@Data
public class Spu {

    private Long id;

    private Long spuNo;

    private String goodsName;

    /**
     * 最低价格
     */
    private double lowPrice;

    private Long categoryId;

    private Long brandId;

    private Timestamp createTime;

    private Timestamp updateTime;
}
