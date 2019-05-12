package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——Spu-规格关联实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 15:58
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class SpuSpec {

    private Long id;

    private Long spuId;

    /**
     * 规格Id
     */
    private Long specId;
}
