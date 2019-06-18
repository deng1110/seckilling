package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——店铺信息实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 12:52
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ShopInfo {

    private Long id;

    /**
     * 店铺名称
     */
    private String shopName;

    private Long userId;

    public ShopInfo(String shopName, Long userId) {
        this.shopName = shopName;
        this.userId = userId;
    }

    public ShopInfo(String shopName) {
        this.shopName = shopName;
    }

    public ShopInfo(Long id) {
        this.id = id;
    }
}
