package com.deng.seckilling.dto;

import lombok.*;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/4 18:42
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpuDTO {

    private String spuNo;

    private String goodsName;

    private Double lowPrice;

    private String categoryName;

    private String brandName;

}
