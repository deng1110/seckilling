package com.deng.seckilling.po;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 商品子系统——品牌实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 12:39
 */
@Data
public class Brand {

    private Long id;

    //品牌名称
    private String brandName;

    private Timestamp createTime;

    private Timestamp updateTime;
}
