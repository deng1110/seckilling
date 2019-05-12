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

    private Long id;

    /**
     * 规格编号
     */
    private Long specNo;

    /**
     * 规格名称
     */
    private String specName;
}
