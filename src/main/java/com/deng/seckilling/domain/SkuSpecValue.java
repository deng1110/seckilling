package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——Sku-规格值关联实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 16:05
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class SkuSpecValue {

    private Long id;

    private Long skuId;

    /**
     * 规格值Id
     */
    private Long specValueId;
}
