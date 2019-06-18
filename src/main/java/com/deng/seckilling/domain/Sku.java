package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——Sku实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:30
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Sku {

    public Sku(Long id) {
        this.id = id;
    }

    public Sku(String skuNo) {
        this.skuNo = skuNo;
    }

    private Long id;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 单价
     */
    private Double price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 是否在架
     */
    private Integer isSale;

    /**
     * 所属商家Id
     */
    private Long shopId;

    /**
     * 所属spuId
     */
    private Long spuId;
}
