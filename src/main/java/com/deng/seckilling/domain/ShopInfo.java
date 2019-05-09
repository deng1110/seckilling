package com.deng.seckilling.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 商品子系统——店铺信息实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 12:52
 */
@Data
public class ShopInfo {

    private Long id;

    /**
     * 店铺名称
     */
    private Long shopName;

    private Timestamp createTime;

    private Timestamp updateTime;
}
