package com.deng.seckilling.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/10 16:36
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SkuVO {
    private Long skuId;

    private String skuNo;

    private String skuName;

    private Double price;

    private Double lowPrice;

    private Double miaoshaPrice;

    private String startTime;

    private String endTime;

    private Integer stock;

    private Integer isSale;

    private Long shopId;

    private String shopName;

    private Long spuId;

    private String spuNo;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    private Long userId;

}
