package com.deng.seckilling.po;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 商品子系统——Sku实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:30
 */
@Data
public class Sku {

    private Long id;

    //商品编号
    private Long skuNo;

    //商品名称
    private String skuName;

    //单价
    private double price;

    //库存
    private int stock;

    //所属商家Id
    private Long shopId;

    //所属spuId
    private Long spuId;

    private Timestamp createTime;

    private Timestamp updateTime;

}
