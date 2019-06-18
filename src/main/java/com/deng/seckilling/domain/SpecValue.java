package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——规格值实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:55
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class SpecValue {

    public SpecValue(Long id) {
        this.id = id;
    }

    public SpecValue(String specValue) {
        this.specValue = specValue;
    }

    public SpecValue(Long specId, String specValue) {
        this.specId = specId;
        this.specValue = specValue;
    }

    private Long id;

    /**
     * 规格Id
     */
    private Long specId;

    /**
     * 规格值
     */
    private String specValue;
}
