package com.deng.seckilling.vo;

import lombok.*;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/5 21:16
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpuVO {

    private Long id;

    private String SpuNo;

    private String goodsName;

    private Double lowPrice;

    private String categoryName;

    private String brandName;
}
