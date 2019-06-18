package com.deng.seckilling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/10 11:27
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SkuDTO {

    private String skuNo;

    private String spuNo;

    private Double price;

    private Integer stock;

    private Integer isSale;

    private String shopName;

    private Double miaoshaPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "GMT+8")
    private Date endTime;
}
