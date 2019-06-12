package com.deng.seckilling.domain;

import lombok.*;

/**
 * 订单子系统——订单实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 20:12
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Order {

    private Long id;

    /**
     * 订单唯一标识
     */
    private String orderSecret;

    private String goodsName;

    /**
     * 秒杀价格
     */
    private Double miaoshaoPrice;

    /**
     * 购买数量
     */
    private Integer number;

    private Long userId;

    /**
     * 订单创建时间
     */
    private String orderTime;

    /**
     * 收货地址
     */
    private String shippingAddress;

    /**
     * 订单状态
     */
    private Integer status;
}
