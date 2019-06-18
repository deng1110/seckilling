package com.deng.seckilling.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/10 23:00
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class SkuRef {

    private Long id;

    private Long skuId;

    private String skuNo;

    private Double miaoshaPrice;

    private String startTime;

    private String endTime;

}
