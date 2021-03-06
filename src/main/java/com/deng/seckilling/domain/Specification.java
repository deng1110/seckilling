package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——规格实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 12:49
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Specification {

    public Specification(Long id) {
        this.id = id;
    }

    public Specification(String specName) {
        this.specName = specName;
    }

    public Specification(String specNo, String specName) {
        this.specNo = specNo;
        this.specName = specName;
    }

    private Long id;

    /**
     * 规格编号
     */
    private String specNo;

    /**
     * 规格名称
     */
    private String specName;
}
