package com.deng.seckilling.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 商品子系统——规格值实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:55
 */
@Data
public class SpecValue {

    private Long id;

    /**
     * 规格Id
     */
    private Long specId;

    /**
     * 规格值
     */
    private String specValue;

    private Timestamp createTime;

    private Timestamp updateTime;

}
