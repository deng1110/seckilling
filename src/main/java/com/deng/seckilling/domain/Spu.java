package com.deng.seckilling.domain;

import lombok.*;

/**
 * 商品子系统——Spu实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:25
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Spu {

    public Spu(String spuNo) {
        this.spuNo = spuNo;
    }

    public Spu(Long id, Double lowPrice) {
        this.id = id;
        this.lowPrice = lowPrice;
    }

    public Spu(Long categoryId, Long brandId) {
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public Spu(Long id) {
        this.id = id;
    }

    private Long id;

    private String spuNo;

    private String goodsName;

    /**
     * 最低价格
     */
    private Double lowPrice;

    private Long categoryId;

    private Long brandId;
}
