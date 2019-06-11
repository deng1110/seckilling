package com.deng.seckilling.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/9 16:56
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class SpuSpecVO {

    private Long spuSpecId;

    private Long spuId;

    private String spuNo;

    private String goodsName;

    /**
     * 最低价格
     */
    private Double lowPrice;

    private Long categoryId;

    private Long brandId;

    /**
     * 规格Id
     */
    private Long specId;

    /**
     * 规格编号
     */
    private String specNo;

    /**
     * 规格名称
     */
    private String specName;
}
